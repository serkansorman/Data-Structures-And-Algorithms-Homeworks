package Q4;

import Q3.MergeSortDLL;
import java.util.LinkedList;
import java.util.Random;

public class TestSortsAverageCase {


    private static boolean verifySort(int[] test) {

        for(int i=0; i < test.length - 1; ++i)
            if(test[i] > test[i+1])
                return false;
        return true;
    }

    public static void main(String[] args) {

        Random rInt = new Random();
        int[] sizeArr = new int[10];
        int[][][] backUp = new int[10][10][];

        sizeArr[0] = 100;
        sizeArr[1] = 500;
        sizeArr[2] = 1000;
        sizeArr[3] = 2000;
        sizeArr[4] = 5000;
        sizeArr[5] = 10000;
        sizeArr[6] = 15000;
        sizeArr[7] = 25000;
        sizeArr[8] = 35000;
        sizeArr[9] = 50000;


        System.out.println("\n\n ########### Merge Sort ##############");

        for(int k = 0; k<10; ++k) {

            int[] arr = new int[sizeArr[k]]; // Array to sort.

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                for (int i = 0; i < arr.length; i++)
                    arr[i] = rInt.nextInt();

                backUp[k][j] = arr.clone();

                long startTime = System.nanoTime();
                MergeSort.sort(arr);
                long endTime = System.nanoTime();
                if(verifySort(arr))
                    System.out.print((endTime - startTime) / 1000000 + "ms ");

            }
            System.out.println();
        }



        System.out.println("\n\n\n ########### Insertion Sort ##############");

        for(int k = 0; k<10; ++k) {

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                int[] arr = backUp[k][j].clone();

                long startTime = System.nanoTime();
                InsertionSort.sort(arr);
                long endTime = System.nanoTime();
                if(verifySort(arr))
                    System.out.print((endTime - startTime) / 1000000 + "ms ");

            }
            System.out.println();
        }



        System.out.println("\n\n\n ########### Quick Sort ##############");

        for(int k = 0; k<10; ++k) {


            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                int[] arr = backUp[k][j].clone();

                long startTime = System.nanoTime();
                QuickSort.sort(arr,0,arr.length-1);
                long endTime = System.nanoTime();
                if(verifySort(arr))
                    System.out.print((endTime - startTime) / 1000000 + "ms ");

            }
            System.out.println();
        }


        System.out.println("\n\n\n ########### Heap Sort ##############");

        for(int k = 0; k<10; ++k) {

            System.out.print("Arrays Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                int[] arr = backUp[k][j].clone();

                long startTime = System.nanoTime();
                HeapSort.sort(arr);
                long endTime = System.nanoTime();
                if(verifySort(arr))
                    System.out.print((endTime - startTime) / 1000000 + "ms ");

            }
            System.out.println();
        }


        System.out.println("\n\n\n ########### Merge Sort DLL ##############");

        for(int k = 0; k<10; ++k) {

            System.out.print("DLL Size: " + sizeArr[k] + " #### Running Times: ");

            for (int j = 0; j < 10; ++j) {

                LinkedList<Integer> list = new LinkedList<>();

                for (int currentInt : backUp[k][j] )
                    list.add(currentInt);

                long startTime = System.nanoTime();
                MergeSortDLL.sort(list);
                long endTime = System.nanoTime();
                System.out.print((endTime - startTime) / 1000000 + "ms ");
            }
            System.out.println();
        }


    }
}



