package Part2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class GTULinkedList<E> extends LinkedList {

    private LinkedList<Pair> disabledElements;

    public GTULinkedList() {
        disabledElements = new LinkedList<>();
    }

    /**
     * Disable element on given index
     * @param index index of the element to be disabled
     * @return element disabled successfully
     * @throws IndexOutOfBoundsException if index size invalid
     */
    public boolean disable(int index) throws IndexOutOfBoundsException {
        if (index < super.size() && index >= 0) {
            for (Pair e : disabledElements)   //if element already disabled
                if (e.getKey().equals(index))
                    return  false;
            Pair<Integer, E> element = new Pair<>(index, (E) get(index));
            disabledElements.add(element);
            return true;
        }
        throw new IndexOutOfBoundsException("Out of Index");
    }

    /**
     * Disable given element
     * @param element element to be disabled
     * @return true if disabled successfully
     */
    public boolean disable(E element) {
        int index;
        for (Pair e : disabledElements)   //if element already disabled
            if (e.getValue().equals(element))
                return  false;
        if(find(element))
            index = super.indexOf(element);
        else
            throw new NoSuchElementException("This element is not in the list");
        Pair<Integer, E> e = new Pair<>(index,element);
        disabledElements.add(e);
        return true;
    }

    /**
     * Searchs element in the list
     * @param element element to be searched
     * @return true if finds element
     */
    private boolean find(E element){
        for(int i=0; i<super.size();++i)
            if(super.get(i).equals(element))
                return true;
        return false;
    }

    /**
     * Enable given element
     * @param element element to be enabled
     * @return true if enabled successfully
     */
    public boolean enable(E element){
        for (Pair e : disabledElements) {
            if (e.getValue().equals(element)) {
                disabledElements.remove(e);
                return true;
            }
        }
        return false;
    }

    /**
     * Disabled element on given index
     * @param index index of element to be enabled
     * @return true if enabled successfully
     */
    public boolean enable(int index){

        if (index < super.size() && index >= 0){
            for (Pair e : disabledElements) {
                if (e.getKey().equals(index)) {
                    disabledElements.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Shows all disabled elements
     */
    public void showDisabled() {
        for (Pair e : disabledElements)
            System.out.print(e.getValue());
    }

    /**
     *
     * This method override for prevent access to disable element
     * @throws InvalidParameterException if given index is disabled
     */
    @Override
    public Object get(int index) throws InvalidParameterException {
        for (Pair e : disabledElements)
            if (e.getKey().equals(index))
                throw new InvalidParameterException("Element on this index is disabled");
        return super.get(index);
    }

    /**
     *
     * This method override for prevent remove by index to disable element
     * @throws InvalidParameterException if given index is disabled
     */
    @Override
    public Object remove(int index) throws InvalidParameterException {
        for (Pair e : disabledElements)
            if (e.getKey().equals(index))
                throw new InvalidParameterException("Element on this index is disabled");
        return super.remove(index);
    }

    /**
     *
     * This method override for prevent remove to disable element
     * @throws InvalidParameterException if given element is disabled
     */
    @Override
    public boolean remove(Object o) {
        for (Pair e : disabledElements)
            if (e.getValue().equals((o)))
                throw new InvalidParameterException("This element is disabled");
        return super.remove(o);
    }

    /**
     *
     * This method override for prevent set element on disabled element
     * @throws InvalidParameterException if given index is disabled
     */
    @Override
    public Object set(int index, Object element) throws InvalidParameterException {
        for (Pair e : disabledElements)
            if (e.getKey().equals(index))
                throw new InvalidParameterException("Element on this index is disabled");
        return super.set(index, element);
    }

    /**
     *  Disable elements are not include to size
     * @return number of not disabled element in the list
     */
    @Override
    public int size() {
        return super.size() - disabledElements.size();
    }

    /**
     * This method override for prevent iterators start on disabled index.
     * @throws InvalidParameterException if given index of element is disable
     */
    @Override
    public ListIterator listIterator(int index) {
        for (Pair e : disabledElements)
            if (e.getKey().equals(index))
                    throw new InvalidParameterException("Element on this index is disabled");
        return super.listIterator(index);
    }

    /**
     * Override this method for not show disabled elements
     * @return
     */
    @Override
    public String toString() {
        boolean find = false;
        String result = "";
        for (int i = 0; i < super.size(); ++i) {
            for (int j = 0; j < disabledElements.size(); ++j)
                if (super.get(i).equals(disabledElements.get(j).getValue()))
                    find = true;
            if (find == false)
                result += super.get(i);
            find = false;
        }
        return result;
    }

}


