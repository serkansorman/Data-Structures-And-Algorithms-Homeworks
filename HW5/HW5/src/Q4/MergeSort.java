package Q4;

public class MergeSort {

    public static void sort(int[] list) {
        if (list.length > 1) {

            // Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

            sort(firstHalf);
            sort(secondHalf);

            merge(firstHalf, secondHalf, list);

        }
    }

    /** Merge two sorted lists */
    private static void merge(int[] list1, int[] list2, int[] temp) {
        int i = 0,j = 0,k = 0; // Current index in list1

        while (i < list1.length && j < list2.length) {
            if (list1[i] < list2[j])
                temp[k++] = list1[i++];
            else
                temp[k++] = list2[j++];
        }
        while (i < list1.length)
            temp[k++] = list1[i++];
        while (j < list2.length)
            temp[k++] = list2[j++];
    }
}
