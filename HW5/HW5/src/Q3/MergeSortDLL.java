package Q3;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Merge Sort algoritmasının Double Linked List
 * ile kullanıldığı class.
 */
public class MergeSortDLL
{


    /**
     * Ayrlmış olan liste elemanlarını birleştirir.
     * @param temp  Ana liste
     * @param list1 Ana listenin sol yarısı
     * @param list2 Ana listenin sağ yarısı
     * @param <T>
     */
    private static <T extends Comparable<T>> void merge(LinkedList<T> temp, LinkedList<T> list1, LinkedList<T> list2) {

        while (!list1.isEmpty() && !list2.isEmpty()) {
            if (list1.getFirst().compareTo(list2.getFirst()) < 0)
                temp.add(list1.remove());
            else
                temp.add(list2.remove());
        }

        while (!list1.isEmpty())
            temp.add(list1.remove());

        while (!list2.isEmpty())
            temp.add(list2.remove());
    }

    /**
     * Liste elemanlarını iterator kullanarak bir eleman kalana dek ayırır
     * ve bunları sıralayıp birleştirir.
     * @param list Ana liste
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(LinkedList<T> list){

        if (list.size() > 1) {

            LinkedList<T> leftList = new LinkedList<>();
            LinkedList<T> rightList = new LinkedList<>();
            ListIterator<T> itr = list.listIterator();

            int halfSize = list.size() / 2;
            for(int i=0; i<halfSize; ++i)
                leftList.add(itr.next());

            while(itr.hasNext())
                rightList.add(itr.next());

            list.clear();

            sort(leftList);
            sort(rightList);
            merge(list,leftList,rightList);

        }
    }
}

