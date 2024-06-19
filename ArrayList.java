import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Generic class to implement an array-based list
 * @author  Yinglong Lin
 * @version Java 11 / VSCode
 * @since   2024-6-18 (date of last revision) 
 */
public class ArrayList<E> implements List<E> {
    // data member: array for the list elements
    private E[] elements;
    // data member: size of the list
    private int size;

    /**
        Default constructor creates the array with a default length of 10 and sets size to 0
        Time complexity: O(1)
    */
    public ArrayList() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    /**
        Constructor with one parameter creates the array with length equal to capacity and sets size to 0
        @param capacity length of the array elements
        Time complexity: O(1)
    */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
        Get the size of the list
        @return the number of elements in the list
        Time complexity: O(1)
     */
    public int size() {
        return size; 
    }

    /**
        Clear the list by setting size to 0
        Time complexity: O(1)
     */
    public void clear() {
        size = 0; 
    }

    /**
        Predicate to check if the list is empty
        @return true if the list is empty, false otherwise
        Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
        Method to add a new item at the end of the list
        @param item the value of the item to be added
        @return true if item was added successfully, false otherwise
        Time complexity: O(1) or O(n) if the array capacity needs to grow
    */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /**
        Method to add a new item at a given position index
        @param index the position where item should be added
        @param item the value of the element to be added
        @return true if item was added successfully, false otherwise
        @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
        Time complexity: O(n)
    */
    public void add(int index, E item) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = item;
        size++;
    }

    /**
     * Linear search method
     * @param o the object being searched
     * @return true if o was found in this list, false otherwise
     * Time complexity: O(n)
     */ 
    public boolean contains(Object o) {
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            E element = iter.next();
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
        Get the value of the element at index
        @param index of the element being accessed
        @return the value of the element at index
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        Time complexity: O(1)
     */
    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
        Set the value of the element at index
        @param index of the element being modified
        @param newValue new value of the element at index
        @return the old value of the element at index
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        Time complexity: O(1)
     */
    public E set(int index, E newValue) {
        checkIndex(index);
        E oldValue = elements[index];
        elements[index] = newValue;
        return oldValue;
    }

    /**
     * Remove an object from the list
     * @param o the object to remove from the list
     * @return true if o was found and removed or false if o was not found
     * Time complexity: O(n)
     */
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
        Remove the element at a given index
        @param index the position of the element to be removed
        @return the value of the element that was removed
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        Time complexity: O(n)
     */
    public E remove(int index) {
        checkIndex(index);
        E val = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return val;
    }

    /**
        Resize the length of the array 'elements' to the size of the list
        Time complexity: O(n) if trimming needed
     */
    public void trimToSize() {
        if (size != elements.length) {
            E[] newElements = (E[]) new Object[size];
            for (int i = 0; i < size; i++)
                newElements[i] = elements[i];
            elements = newElements;
        }
    }

    /**
        Grow the length of the array 'elements' by 1.5 if it is full
        Time complexity: O(n) if the size reaches the capacity
    */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    /**
        Check if the index is valid
        @param index to be checked
        @throws ArrayIndexOutOfBoundsException if index is out of bounds
        Time complexity: O(1)
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(
                "Index out of bounds. Must be between 0 and " + (size - 1));
    }

    /**
        @override iterator() from the interface Collection
        @return iterator object pointing to the first element the list
        Time complexity: O(1)
     */
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    /**
        Inner class to implement the interface Iterator<E>
     */
    private class ArrayIterator implements Iterator<E> {
        // data member current: the index of the element at which the iterator is pointing
        private int current = 0;

        /**
            @return true if current did not reach the end of the list, false otherwise
            Time complexity: O(1)
         */
        public boolean hasNext() {
            return current < size;
        }

        /**
            @return the value of the current element and moves the index current to the next element
            @throws ArrayIndexOutOfBoundsException if current is out of bounds
            Time complexity: O(1)
         */
        public E next() {
            if (current < 0 || current >= size)
                throw new ArrayIndexOutOfBoundsException("No more elements");
            return elements[current++];
        }
    }

    /**
        @override toString() from class Object
        @return a formatted string containing the elements of the list
        Time complexity: O(n)
     */
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
            output.append(elements[i]).append(" ");
        if (size > 0)
            output.append(elements[size - 1]);
        output.append("]");
        return output.toString();
    }

    /**
        Adds all elements from the specified collection to this list
        @param c the collection containing elements to be added
        @return true if this list changed as a result of the call
        Time complexity: O(n)
     */
    public boolean addAll(Collection<E> c) {
        for (Iterator<E> iterator = c.iterator(); iterator.hasNext();) {
            E element = iterator.next();
            add(element);
        }
        return true;
    }

    /**
        Compares the specified object with this list for equality
        @param o the object to be compared for equality with this list
        @return true if the specified object is equal to this list
        Time complexity: O(n)
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;
        List<?> list = (List<?>) o;
        if (list.size() != size)
            return false;
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(list.get(i)))
                return false;
        }
        return true;
    }

    /**
        Returns the index of the first occurrence of the specified element in this list
        @param o the element to search for
        @return the index of the first occurrence of the specified element, or -1 if this list does not contain the element
        Time complexity: O(n)
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o))
                return i;
        }
        return -1;
    }

    /**
        Returns the index of the last occurrence of the specified element in this list
        @param o the element to search for
        @return the index of the last occurrence of the specified element, or -1 if this list does not contain the element
        Time complexity: O(n)
     */
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(o))
                return i;
        }
        return -1;
    }

    /**
        Returns an array containing all of the elements in this list in proper sequence (from first to last element)
        @return an array containing all of the elements in this list in proper sequence
        Time complexity: O(n)
     */
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
        Sorts this list according to the order induced by the specified Comparator
        @param c the Comparator used to compare list elements
        Time complexity: O(n^2)
     */
    public void sort(Comparator<E> c) {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (c.compare(elements[j], elements[minIndex]) < 0)
                    minIndex = j;
            }
            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
}