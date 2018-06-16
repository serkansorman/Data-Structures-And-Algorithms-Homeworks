package Q2;

public class Main {

    public static void main(String[] args){
        BTree<Integer> firstTree = new BTree<>(3);
        BTree<Integer> secondTree = new BTree<>(4);

        System.out.println("\t----------------------  FIRST TREE ORDER 3 ----------------------\n");

        firstTree.add(5);
        firstTree.add(4);
        firstTree.add(9);
        firstTree.add(3);
        firstTree.add(6);
        firstTree.add(12);
        firstTree.add(8);
        System.out.println(firstTree);

        System.out.println("\t----------------------   SECOND TREE ORDER 4    ----------------------\n");

        secondTree.add(14);
        secondTree.add(12);
        secondTree.add(21);
        secondTree.add(24);
        secondTree.add(19);
        secondTree.add(7);
        secondTree.add(4);
        secondTree.add(6);

        System.out.println(secondTree);


    }
}