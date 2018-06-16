package part2;


public interface SearchTree<E>  {

    boolean contains(E target);
    boolean remove(E target);
    boolean add(E item);
    E find(E target);
    E delete(E target);
}
