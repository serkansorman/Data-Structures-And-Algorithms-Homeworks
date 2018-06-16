package part2;



import part1.BinaryTree;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Birden fazla boyuta sahip elemanların tutulduğu BST
 * @param <E> treeyi oluşturan elemanların tipi
 */
public class MultiDimensionalSearchTree<E extends Comparable>  extends BinaryTree implements SearchTree {

    /**
     * Treedeki elemanların sahip olduğu boyut sayısı
     */
    int dimension;
    /**
     * Tree üzerinde traverse yapılırken ,nodeların karşılaştırılacağı boyutu belirtir.
     */
    int dimensionSequence;

    /**
     * Default dimension 3 olarak belirlenir
     */
    public MultiDimensionalSearchTree(){
        super();
        dimension = 3;
        dimensionSequence = 0;
    }


    public MultiDimensionalSearchTree(int dimension){
        super();
        if(dimension <= 0) //Dimension 0  dan büyük olmalı
            throw new InvalidParameterException("Invalid Dimension");
        this.dimension = dimension;
        dimensionSequence = 0;
    }

    /**
     * Tree ye eleman ekleme işlemini gerçkleştirir.
     * @param item Tree ye eklenecek vector
     * @return Ekleme işlemi başarıyla gerçekleşirse true return eder.
     */
    @Override
    public boolean add(Object item) {
        if(contains(item))
            return false;
        return addReturn(item) != null;
    }

    /**
     * Treeye yeni içinde vektör tutan yeni bir node ekler.Bunu current dimensionSequence ye göre diğer
     * nodelar ile karşılaştırarak yapar.
     * @param data Tree ye eklenecek vector
     * @return Eklenen eleman return edilir.
     * @throws InvalidParameterException Dimension geçerli değilse
     */

    private Node addReturn(Object data){
        if(((Vector<E>)data).size() != dimension)  //Mevcut dimension dan farklı boyutlarda eleman eklenemez
            throw new InvalidParameterException("Invalid Tree element");
        if (root == null) {
            root = new Node(data);
            return root;
        }
        return addReturn(root,(Vector<E>)data);
    }

    /**
     * Treeye add işlemini recursive bir şekilde gerçekleştirir.
     * @param node üzerinde gezilmeye başlanan node
     * @param data treeye eklenecek vector
     * @return Eklenen eleman return edilir.
     */
    private Node addReturn(Node node,Vector<E> data) {

        if (node == null) {
            node = new Node(data);
            dimensionSequence=0;
            return node;
        }

        //Eklencek node treedeki diğer nodelar ile karşılaştırılır
        if (data.get(dimensionSequence).compareTo(((Vector<E>) node.data).get(dimensionSequence)) < 0) {
            ++dimensionSequence;                //Diğer dimensiona geçilir
            dimensionSequence %= dimension;    //Her seferinde dimension ı korumak için mod alınır
            node.left = addReturn(node.left, data);
        }
        else if (data.get(dimensionSequence).compareTo(((Vector<E>) node.data).get(dimensionSequence)) > 0) {
            ++dimensionSequence;                //Diğer dimensiona geçilir
            dimensionSequence %= dimension;   //Her seferinde dimension ı korumak için mod alınır
            node.right = addReturn(node.right, data);
        }
        else{// Boyuttaki eleman aynı ise sola eklenir
            ++dimensionSequence;
            dimensionSequence %= dimension;
            node.left = addReturn(node.left, data);
        }

        return node;
    }

    /**
     * Elemanın tree içinde olup olmadığını check eder.
     * @param target aranılan eleman
     * @return Eleman tree içindeyse eleman return edilir.
     */
    @Override
    public Object find(Object target) {
        if (root == null)
            return null;
        Vector<E> targetVector = (Vector<E>) target;
        return  find(root,targetVector);
    }

    /**
     * Elemanı recursive bir şekilde tree içinde arar
     * @param node
     * @param target aranılan eleman
     * @return Eleman tree içindeyse eleman return edilir.
     */
    private Object find(Node node,Vector<E> target) {

        if(node != null){
            if(node.data.equals(target))
                return node;
            Node tempNode = (Node)find(node.left,target);
            if (tempNode != null)
                return tempNode;
            return find(node.right,target);
        }
        return null;
    }


    /**
     * Elemanın tree içinde olup olmadığını check eder.
     * @param target aranılan eleman
     * @return Eleman tree içindeyse true return edilir.
     */
    @Override
    public boolean contains(Object target) {
        return find(target) != null;
    }


    /**
     * Tredden silinmek istenen eleman dışındaki elemanları geçici bir
     * treye alır ve eski tree silinip yeni tree eklenir.
     * @param target silinecek eleman
     * @return Eleman tree içindeyse silinen eleman return edilir.
     */
    @Override
    public Object delete(Object target){

        if(root == null)
            return null;
        else if(!contains((target)))
            return null;

        MultiDimensionalSearchTree tree = new MultiDimensionalSearchTree(dimension);
        Queue<Node> queue = new LinkedList<>();
        Node nodeToBeDeleted = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            if(!tempNode.data.equals(target))
                tree.add(tempNode.data);        //Silinecek node haricindeki elemanlar yeni noda eklenir
            else
                nodeToBeDeleted = tempNode;   //Silinecek node return edilmek üzere alınır

            if (tempNode.left != null)
                queue.add(tempNode.left);
            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
        root = tree.root;       //Delete old tree and add new tree that has not deleted element
        return nodeToBeDeleted;
    }

    /**
     * Tredden silinmek istenen eleman dışındaki elemanları geçici bir
     * treye alır ve eski tree silinip yeni tree eklenir.
     * @param target silinecek eleman
     * @return Eleman tree içindeyse silinir ve true return edilir..
     */
    @Override
    public boolean remove(Object target){
        return delete(target) != null;
    }

    @Override
    public boolean equals(Object obj) {
        MultiDimensionalSearchTree<E> tree = (MultiDimensionalSearchTree<E>) obj;
        return tree.toString().equals(this.toString());
    }
}
