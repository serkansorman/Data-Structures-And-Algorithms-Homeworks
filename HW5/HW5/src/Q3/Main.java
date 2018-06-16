package Q3;

import java.util.LinkedList;

public class Main {

    public static void main(String args[]) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(38);
        list.add(27);
        list.add(43);
        list.add(3);
        list.add(9);
        list.add(82);
        list.add(10);
        list.add(22);

        System.out.println("Given list\n"+list);
        MergeSortDLL.sort(list);
        System.out.println("\nSorted list\n"+list);



    }
}
