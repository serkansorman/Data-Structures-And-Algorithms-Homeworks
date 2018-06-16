package Q1;

/**
 * Double Hashing için kullanılan map interfacesi
 * @param <K> key
 * @param <V> value
 */
public interface HashMap<K,V> {

    V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    boolean isEmpty();
    int size();
}
