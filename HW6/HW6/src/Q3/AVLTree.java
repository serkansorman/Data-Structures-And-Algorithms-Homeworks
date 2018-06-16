package Q3;

import Q1.*;
import java.security.InvalidParameterException;

/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    // Insert nested class AVLNode<E> here.

    /** Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;
    /** Flag to indicate that height of tree has decreased. */
    private boolean decrease;


    AVLTree(){
        /*
         * Empty Body
         */
    }

    /**
     * Check whether binaryTree is AVL tree
     * @param binaryTree
     * @throws InvalidParameterException if binaryTree is not AVL tree
     */
    AVLTree(BinaryTree<E> binaryTree) throws InvalidParameterException{

        root = new Node<>(binaryTree.getData());
        if (binaryTree.getLeftSubtree() != null)
            root.left = binaryTree.getLeftSubtree().getRoot();
        else
            root.left = null;

        if (binaryTree.getRightSubtree() != null)
            root.right = binaryTree.getRightSubtree().getRoot();
        else
            root.right = null;


        if(!isBalanced(root))
            throw new InvalidParameterException("This tree is not AVL tree");
    }

    /**
     * add starter method.
     * @pre the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     * @post addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        } else { // item > data

            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;

        }
    }

    /**
     * Method to rebalance left.
     * @pre localRoot is the root of an AVL subtree that is
     *      critically left-heavy.
     * @post Balance is restored.
     * @param localRoot Root of the AVL subtree
     *        that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // After rotation
            increase = false;
            decrease = true;

            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            // In this case the leftChild (the new root)
            // and the root (new right child) will both be balanced
            // after the rotation.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;

            // After rotation
            increase = false;
            decrease = true;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /**
     * Method to rebalance right.
     * @param localRoot is the root of an AVL subtree that is
     * critically right-heavy.
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain reference to right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether right-left heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (rightLeftChild.balance < AVLNode.BALANCED) { // if balance is left heavy
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (rightLeftChild.balance > AVLNode.BALANCED) { //if balance is right heavy
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else { // if node is balanced
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // After rotation
            increase = false;
            decrease = true;
            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        } else { //Right-Right case
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;

            // After rotation
            increase = false;
            decrease = true;
        }
        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }


    /**
     * Method to decrement the balance field and to reset the value of
     * increase and decrease.
     * @pre The balance field was correct prior to an insertion [or
     *      removal,] and an item is either been added to the left[
     *      or removed from the right].
     * @post The balance is decremented and the increase flags is set
     *       to false if the overall height of this subtree has not
     *       changed.
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED)
            // If now balanced, overall height has not increased.
            increase = false;
        //if node is left heavy overall height has not decreased.
        else if (node.balance == AVLNode.LEFT_HEAVY)
            decrease = false;
    }

    /**
     * Method to incremented the balance field and to reset the value of
     * decrease or increase
     * @param node The AVL node whose balance is to be decremented
     */
    private void incrementBalance(AVLNode<E> node){
        node.balance++;
        if (node.balance > AVLNode.BALANCED) { // if node is right heavy, overal height will increase
            increase = true;
            decrease = false;
        }
        else {// if node is left heavy or balanced, overal height will decrease
            increase = false;
            decrease = true;
        }

    }


    /**
     * Wrapper method of remove
     * @param item item to be deleted
     * @return data to be deleted
     */
    public E remove(E item){
        decrease = false;
        root = remove((AVLNode<E>) root,item);
        return deleteReturn; // data to be deleted
    }

    /**
     * Remove element from AVL tree and rotates tree for to be balanced after deletion
     * @param node current node in tree
     * @param item item to be deleted in tree
     * @return last visited AVLNode
     */
    private AVLNode<E> remove(AVLNode<E> node,E item){

        if(node == null)
            return null;

        if (item.compareTo(node.data) < 0) { // item < data
            node.left = remove((AVLNode<E>) node.left, item);
            if (decrease) {
                incrementBalance(node);
                if (node.balance > AVLNode.RIGHT_HEAVY)
                    return rebalanceRight(node);
            }
            return node;
        }
        else if (item.compareTo(node.data) > 0) {// item > data
            node.right = remove((AVLNode<E>) node.right, item);
            if (decrease) {
                decrementBalance(node);
                if (node.balance < AVLNode.LEFT_HEAVY)
                    return rebalanceLeft(node);
            }
            return node;
        }
        else{ // if item is in current root

            decrease = true; // removing node causes decreasing height
            deleteReturn = node.data; // records node to be deleted data

            if(node.left == null) // if node has not left child
                return (AVLNode<E>)node.right;
            else if(node.right == null)  // if node has not right child
                return (AVLNode<E>) node.left;
            else { // if node has 2 children

                if(node.left.right == null){// if has not left-right child

                    //Change data with lef child data
                    node.data = node.left.data;
                    //Change left child with left-left
                    node.left = node.left.left;

                    incrementBalance(node);
                    if (node.balance > AVLNode.RIGHT_HEAVY) // balance is right heavy
                        return rebalanceRight(node);
                    return node;
                }
                else {
                    node.data = findMax((AVLNode<E>) node.left); // find inorder predecessor data
                    decrementBalance(node);
                    if (node.balance < AVLNode.LEFT_HEAVY) // balance is left heavy
                        return rebalanceLeft(node);
                    return node;
                }
            }
        }
    }

    /**
     * Find the node that is inorder predecessor and replace it
     * with its left child.
     * @param node The parent of possible inorder predecessor (ip).
     * @return  the data in the ip
     */
    private E findMax(AVLNode<E> node){
        if(node.right.right == null){
            E tempValue = node.right.data;
            node.right = node.right.left;
            return tempValue;
        }
        else
            return findMax((AVLNode<E>) node.right);
    }

    /**
     * Checks whether binary tree is AVL or not recursively
     * @param node  root of binary tree
     * @return true if tree is AVL
     */
    private boolean isBalanced(Node<E> node) {

        if (node == null)
            return true;

        int leftSubHeight = height(node.left);
        int rightSubHeight = height(node.right);

        int diff = leftSubHeight - rightSubHeight;

        return diff <= AVLNode.RIGHT_HEAVY && diff >= AVLNode.LEFT_HEAVY &&
                isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * Finds maximum height of the given node to leaf
     * @param node current node
     * @return height of tree
     */
    private int height(Node<E> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

}
