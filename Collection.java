import java.util.Iterator;

/**
 * A generic collection interface that defines common methods for manipulating collections of elements.
 * @author  Yinglong Lin
 * @version Java 11 / VSCode
 * @since   2024-6-18 (date of last revision) 
 * @param <E> the type of elements in this collection
 */
public interface Collection<E> {

    /**
     * Adds an element to this collection.
     *
     * @param element the element to be added
     * @return true if the element was added successfully, false otherwise
     */
    public abstract boolean add(E element);

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return an Iterator over the elements in this collection
     */
    public abstract Iterator<E> iterator();

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     *
     * @param o the element to be removed
     * @return true if an element was removed as a result of this call, false otherwise
     */
    public abstract boolean remove(Object o);

    /**
     * Removes all elements from this collection.
     */
    public abstract void clear();

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    public abstract int size();

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param o the object to be compared for equality with this collection
     * @return true if the specified object is equal to this collection
     */
    public abstract boolean equals(Object o);

    /**
     * Adds all of the elements in the specified collection to this collection.
     *
     * @param c the collection containing elements to be added to this collection
     * @return true if this collection changed as a result of the call
     */
    public abstract boolean addAll(Collection<E> c);

    /**
     * Returns true if this collection contains the specified element.
     *
     * @param o the element whose presence in this collection is to be tested
     * @return true if this collection contains the specified element
     */
    public abstract boolean contains(Object o);

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if this collection contains no elements
     */
    public abstract boolean isEmpty();
}
