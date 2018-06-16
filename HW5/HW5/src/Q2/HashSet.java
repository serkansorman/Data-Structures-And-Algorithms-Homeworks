package Q2;


/**
 * Recursive chaining hashSet için kullanılan Set interfacesi
 * @param <E> key
 */
public interface HashSet<E> {

    boolean add(E e);
    boolean remove(Object o);
    boolean contains(Object o);
    int size();
    boolean isEmpty();
}
