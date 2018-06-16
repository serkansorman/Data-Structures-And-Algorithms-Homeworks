package Q4;

public class InsertionSort {

    public static void sort(int []list){

        for(int i = 1; i<list.length; ++i)
            insert(list,i);

    }

    private static void insert(int []list,int nextPos){

        int nextVal = list[nextPos];
        while(nextPos > 0 && nextVal < list[nextPos - 1]){
            list[nextPos] = list[nextPos - 1];
            --nextPos;
        }
        list[nextPos] = nextVal;
    }

}
