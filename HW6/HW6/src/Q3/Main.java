package Q3;

import Q1.BinarySearchTree;
import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args){

        
        AVLTree<Integer> tree = new AVLTree<>();

        if(tree.add(9))
            System.out.println("9 added");
        if(tree.add(5))
            System.out.println("5 added");
        if(tree.add(3))
            System.out.println("3 added");
        if(tree.add(20))
            System.out.println("20 added");
        if(tree.add(8))
            System.out.println("8 added");
        if(tree.add(6))
            System.out.println("6 added");
        if(tree.add(7))
            System.out.println("7 added");


        System.out.println("\t--------------- AVL TREE ----------------");
        System.out.println(tree);

        if(tree.remove(99) == null)
            System.out.println("Element 99 can not found");

        if(tree.remove(20).equals(20)) {
            System.out.println("Element 20 is removed!");
            System.out.println("\t--------------- AVL TREE AFTER REMOVE 20 ----------------");
            System.out.println(tree);
        }

        if(tree.remove(8).equals(8)) {
            System.out.println("Element 8 is removed!");
            System.out.println("\t--------------- AVL TREE AFTER REMOVE 8 ----------------");
            System.out.println(tree);
        }


        System.out.println("\t--------------- CHECK BST IS AVL ----------------");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(7);

        try{//checks whether this tree is AVL tree or not
            AVLTree<Integer> treeOne = new AVLTree<>(bst); // AVL tree
            bst.add(8);  // After insertion tree is not balanced
            AVLTree<Integer> treeTwo = new AVLTree<>(bst);  // Unbalanced BST tree, exception is expected
        }
        catch (InvalidParameterException e){
            System.out.println(bst);
            System.out.println(e.getMessage());
        }


    }
}