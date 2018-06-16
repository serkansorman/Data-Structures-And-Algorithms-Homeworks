package Q1;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * HashMap interfacesi implement edilerek
 * Open adressing kullanılır ve double hashing yapılır.
 * @param <K> key
 * @param <V> value
 */
public class DoubleHashingMap<K,V> implements HashMap<K,V>{


    private Entry<K, V>[] table;
    private int CAPACITY;
    /**Table ın rehash yapılacağı maksimum doluluk oranını belirtir.*/
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    /** Silinen entryi belirten referans*/
    private final Entry<K, V> DELETED = new Entry<>(null, null);


    public DoubleHashingMap(int capacity) throws InvalidParameterException{
        if(capacity > 5)
            CAPACITY = capacity;
        else
            throw new InvalidParameterException("Invalid Capacity");
        table = new Entry[CAPACITY];
    }

    /** Hash tablosunu oluşturan elemanlar key value olarak tutulur */
    public static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }


        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
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
     * İlk hash methodu kullanılıp uygun index bulunamadığında kullanılan ikinci hash metodudur
     * @param index table indexi
     * @param key elemanın keyi
     * @return oluşturulan yeni index
     */
    private int secondHash(int index,Object key){
        index += (5 - (key.hashCode() % 5));
        return index % table.length;
    }


    /**
     * Hash tablosunda bulunan boş indexi ya da aranan keyin indexini verir.
     * @param key
     * @return hash tablosunda bulunan boş indexi ya da aranan keyin indexini verir.
     */
    private int find(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        while ((table[index] != null) && (!key.equals(table[index].key)))
           index = secondHash(index,key);
        return index;
    }

    /**
     * İstenen keyin valuesini verir.
     * @param key
     * @return verilen keye ait value varsa return edilir yoksa null return edilir.
     */
    @Override
    public V get(Object key) {
        int index = find(key);
        if (table[index] != null)
            return table[index].value;
        else
            return null;
    }


    /**
     * Hash tablosuna yeni bir entry ekler veya tabloda bulunan keye yeni bir value set eder.
     * @param key
     * @param value
     * @return Tabloda var olan keye yeni bir value set edildiğinde eski value return edilir.
     * Yeni bir entry set edildiğinde null return edilir.
     */
    @Override
    public V put(K key, V value) {
        int index = find(key);
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            double loadFactor = (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }

        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }

    /**
     * Tablodaki verilen keye DELETED referansı atanır.
     * @param key
     * @return DELETED referansı atanan entrynin valuesi return edilir.
     */
    @Override
    public V remove(Object key) {
        int index = find(key);
        if (table[index] == null)
            return null;
        V removedValue = table[index].value;
        table[index] = DELETED;
        ++numDeletes;
        --numKeys;

        return removedValue;
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
     * Tablonun doluluk miktarı sınırı aşıldığında tabloyu büyütür.
     */
    private void rehash() {
        Entry<K, V>[] temp = table;
        table = new Entry[2 * temp.length + 1];

        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < temp.length; i++)
            if ((temp[i] != null) && (temp[i] != DELETED))
                put(temp[i].key, temp[i].value);
    }

    /**
     * Hash tablosunu ekrana basar.
     */
    public void showTable(){

        System.out.println("\nINDEX KEYS VALUES");
        System.out.println("--------------------");
        for(int i=0; i< table.length; ++i) {
            if (table[i] == null)
                System.out.println(i + " ==> null");
            else if (table[i].equals(DELETED))
                System.out.println(i + " ==> DELETED");
            else
                System.out.println(i + " ==> [" + table[i].key+","+table[i].value+"]");
        }

        System.out.println();
    }
}
