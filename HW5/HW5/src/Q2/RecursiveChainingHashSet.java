package Q2;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * chaining hash tablosu recursive olarak implement edilir.
 * @param <E> key
 */
public class RecursiveChainingHashSet<E> implements HashSet<E> {

    private Entry<E,E>[] table;
    private int numKeys;
    private int CAPACITY;
    /**Table ın rehash yapılacağı maksimum doluluk oranını belirtir.*/
    private static final double LOAD_THRESHOLD = 3.0;
    /**Sürekli değişen table sizeları için tutulan asal sayıları içerir.*/
    private Queue<Integer> primeNumbers = new LinkedList<>();

    /** Hash tablosunu oluşturan elemanları key value olarak tutar
     * ve eğer varsa bir sonraki hash tablosunu gösterir*/
    private class Entry<K, V>{

        private K key;
        private V value;
        /** Collision durumunda oluşan bir sonraki hash tablosu*/
        private Entry<E,E>[] nextTable;
        private boolean isDeleted = false;



        public Entry(K key, V value,int nextTableCapacity) {
            this.key = key;
            this.value = value;
            nextTable = new Entry[nextTableCapacity];
        }



        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Silinmiş bir entrye bağlı olan hash tablosunu entrye bağlı tutmak için kullanılır
         * @param nextTable collision durumunda oluşturulmuş olan hash tablosu
         */
        public void setNextTable(Entry<E, E>[] nextTable) {
            this.nextTable = nextTable.clone();
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        private void setKey(K key) {
           this.key = key;
        }
    }


    /**
     * Hash tablosu ve table sizeları için kullanılmak üzere prime number queusu oluşturulur.
     * @param capacity
     */
    RecursiveChainingHashSet(int capacity)throws InvalidParameterException{
        if(capacity >= 5)
            CAPACITY = capacity;
        else
            throw new InvalidParameterException("Invalid capacity");
        table = new Entry[CAPACITY];
        addPrimeNumbers();
    }


    /**
     * Tabloda bulunmayan yeni bir key ekleyen wrapper metod
     * @param key
     * @return eklencek key tabloda varsa false yoksa true return edilir.
     */
    @Override
    public boolean add(E key) {
        if(contains(key))
            return false;
        return add(key,table);
    }

    /**
     * Recursive olarak key ekleme işlemini yapar.Collusion durumlarında
     * queudan bir prime number pool edilerek bu sizea sahip ve entrye bağlı yeni bir
     * hash tablosu oluşturulur.
     * @param key
     * @param newTable üzerinde bulunulan güncel hash tablosu
     * @return eleman eklenir ve true return edilir.
     */
    private boolean add(E key,Entry []newTable) {

        int index = key.hashCode() % newTable.length;

        if (index < 0)
            index += newTable.length;
        if (newTable[index] == null) {
            newTable[index] = new Entry(key,key);
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length))
                rehash();
            return true;

        } else if(newTable[index].nextTable == null){
            //Queueda sırada bulunan prime number alınarak bu sizeda bir table oluşturulur.
            newTable[index].nextTable = new Entry[primeNumbers.poll()];
            add(key, newTable[index].nextTable);
        }
        else
            add(key, newTable[index].nextTable);

        return true;

    }

    /**
     * 7 den büyük prime numberları, oluşturulacak
     * tabloların sizelarında kullanılmak üzere queuya ekler
     */
    private void addPrimeNumbers(){
        for (int i = 7; i<1000; ++i)
            if(isPrime(i) && i != CAPACITY)
                primeNumbers.add(i);
    }

    /**
     * Sayının prime olup olmadığını check eden helper metod
     * @param n check edilen sayı
     * @return sayı prime ise true return edilir
     */
    private boolean isPrime(int n){
        for (int i = 2; i<n/2; ++i)
            if(n % i == 0)
                return false;
        return true;
    }

    /**
     * Hash tablosundan eleman silen(DELETED referansı atanır) wrapper metod
     * @param key
     * @return silinecek key tabloda yoksa false,var ise silindiğinde true return edilir.
     */
    @Override
    public boolean remove(Object key) {
        return contains(key) && remove(key, table);
    }

    /**
     * Silinecek entry recursive tablolar içinde aranır ve deleted referansı atanır.
     * Eğer kendisine bağlı bir tablo var ise kaybolmaması için bu referansa aktarılır.
     * @param key
     * @param table
     * @return
     */
    private boolean remove(Object key, Entry[] table){

        int index = key.hashCode() % table.length;
        if(table[index].key.equals(key)){
            table[index].isDeleted = true;
            table[index].setValue(null);
            table[index].setKey(null);
            --numKeys;
            return true;
        }

        return remove(key,table[index].nextTable);

    }

    /**
     * Keyin tabloda olup olmadığı check edilen wrapper metod
     * @param key
     * @return key tablodaysa true yoksa false return edilir.
     */
    @Override
    public boolean contains(Object key) {
        return contains(key,table);
    }

    /**
     * Recursive olarak keyin tablolar içinde  arandığı metod
     * @param key
     * @param table üzerinde gezinilen güncel tablo
     * @return key tablodaysa true yoksa false return edilir.
     */
    private boolean contains(Object key,Entry []table){
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;

        if (table[index] == null)
            return false;
        else if( ! table[index].isDeleted && table[index].key.equals(key))
            return true;
        else if(table[index].nextTable == null)
            return false;

        return contains(key,table[index].nextTable);
    }


    /**
     * Tablodaki entry sayısını verir
     * @return entry sayısı
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * Tablonun boş olup olmadığını check eder
     * @return boş ise true return edilir.
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /**
     * Tablonun doluluk miktarı sınırı aşıldığında tabloyu büyütür.
     */
    private void rehash() {
        Entry<E,E>[] oldTable = table;
        table = new Entry[2 * oldTable.length + 1];
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++)
            if ((oldTable[i] != null) && ( ! oldTable[i].isDeleted))
               table[i] = oldTable[i];
    }


    /**
     * Hash tablolarını print eder
     */
    public void  showTable(){
        System.out.println("\nINDEX ==> KEYS >>>> NEXT_TABLES");
        System.out.println("-----------------------------");
        for(int i=0; i< table.length; ++i) {
            if (table[i] == null)
                System.out.println(i + " ==> null");
            else if (table[i].isDeleted) {
                System.out.print(i + " ==> DELETED");
                showNextTable(table[i].nextTable);
                System.out.println();
            }
            else {
                System.out.print(i + " ==> [" + table[i].key+"]");
                showNextTable(table[i].nextTable);
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Chain olan hash tablolarını recursive bir şekilde print eder
     * @param nextTable
     */
    private void showNextTable(Entry[] nextTable){

        if (nextTable != null) {
            System.out.print(" >>>>> ");
            for (int j = 0; j < nextTable.length; ++j) {
                if (nextTable[j] == null)
                    System.out.print(j + "==>" + "null ");
                else if (nextTable[j].isDeleted) {
                    System.out.print(j + "==>DELETED ");
                    if(nextTable[j].nextTable != null)
                        showNextTable(nextTable[j].nextTable);
                }
                else {
                    System.out.print(j + "==>[" + nextTable[j].key + "] ");
                    if(nextTable[j].nextTable != null)
                        showNextTable(nextTable[j].nextTable);
                }
            }
            System.out.print("## END ## ");
        }

    }
}
