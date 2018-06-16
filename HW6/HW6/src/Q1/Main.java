package Q1;


import java.util.Random;

public class Main {


    private static void generateWorstTree(RedBlackTree<Integer> tree,String order){
        Random num = new Random();
        Integer number = num.nextInt(100);
        for(int i=0; i<22; ++i) {
            tree.add(number);
            System.out.println("\t\t##########   INSERT "+number+"   ###########");
            System.out.println(tree);
            if(order.equals("ASCENDING"))
                number += num.nextInt(10)+1;
            else
                number -= num.nextInt(10)+1;
        }
    }

    public static void main(String[] args){

        RedBlackTree<Integer> firstTree = new RedBlackTree<>();
        RedBlackTree<Integer> secondTree = new RedBlackTree<>();

        System.out.println("\t----------------------  FIRST TREE  ----------------------\n");
        generateWorstTree(firstTree,"ASCENDING");

        System.out.println("\t----------------------   SECOND TREE   ----------------------\n");
        generateWorstTree(secondTree,"DESCENDING");


    }
}
