package Q5;


import Q3.MergeSortDLL;
import Q4.*;

import java.util.LinkedList;



public class TestSortsWorstCase {

    public static void main(String[] args) {


        int[] sizeArr = new int[10];
        int[][][] backUp = new int[10][10][];

        sizeArr[0] = 100;
        sizeArr[1] = 1000;
        sizeArr[2] = 5000;
        sizeArr[3] = 10000;

        System.out.println("\n\n ########### Merge Sort ##############");
        for(int k = 0; k<4; ++k) {

            int[] arr = new int[sizeArr[k]]; // Array to sort.
            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                int n = sizeArr[k];
                for (int i = 0; i < sizeArr[k]; i++) {
                    arr[i] = n;
                    --n;
                }

                backUp[k][j] = arr.clone();

                long startTime = System.nanoTime();
                MergeSort.sort(arr);
                System.out.print((System.nanoTime() - startTime) / 1000000 + "ms ");
            }
            System.out.println();
        }



        System.out.println("\n\n\n ########### Insertion Sort ##############");
        for(int k = 0; k<4; ++k) {

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");
            for (int j = 0; j < 10; ++j) {

                int[] arr = backUp[k][j].clone();
                long startTime = System.nanoTime();
                InsertionSort.sort(arr);
                System.out.print((System.nanoTime() - startTime) / 1000000 + "ms ");
            }
            System.out.println();
        }



        System.out.println("\n\n\n ########### Quick Sort ##############");
        for(int k = 0; k<4; ++k) {

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");
            for (int j = 0; j < 10; ++j) {

                int[] arr = backUp[k][j].clone();

                long startTime = System.nanoTime();
                QuickSort.sort(arr,0,arr.length-1);
                System.out.print((System.nanoTime() - startTime) / 1000000 + "ms ");

            }
            System.out.println();
        }


        System.out.println("\n\n\n ########### Heap Sort ##############");
        for(int k = 0; k<4; ++k) {

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");
            for (int j = 0; j < 10; ++j) {
                int[] arr = backUp[k][j].clone();

                long startTime = System.nanoTime();
                HeapSort.sort(arr);
                System.out.print((System.nanoTime() - startTime) / 1000000 + "ms ");
            }
            System.out.println();
        }


        System.out.println("\n\n\n ########### Merge Sort DLL ##############");
        for(int k = 0; k<4; ++k) {

            System.out.print("DLL Size: " + sizeArr[k] + " #### Running Times: ");
            for (int j = 0; j < 10; ++j) {

                LinkedList<Integer> list = new LinkedList<>();
                for (int currentInt : backUp[k][j] )
                    list.add(currentInt);

                long startTime = System.nanoTime();
                MergeSortDLL.sort(list);
                System.out.print((System.nanoTime() - startTime) / 1000000 + "ms ");
            }
            System.out.println();
        }

    }
}



