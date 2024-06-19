import java.util.Comparator;

/**
 * A generic list interface that defines common methods for manipulating lists of elements.
 * @author  Yinglong Lin
 * @version Java 11 / VSCode
 * @since   2024-6-18 (date of last revision) 
 * @param <E> the type of elements in this list
 */
public interface List<E> extends Collection<E> {

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the position at which the specified element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    public abstract void add(int index, E element);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the position of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public abstract E get(int index);

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public abstract int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o the element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public abstract int lastIndexOf(Object o);

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the position of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public abstract E remove(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index  the position of the element to replace
     * @param newVal the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public abstract E set(int index, E newVal);

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     *
     * @param c the Comparator used to compare list elements
     */
    public abstract void sort(Comparator<E> c);

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public abstract Object[] toArray();
}