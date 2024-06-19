import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * LinkedList Generic Class
 * @author  Yinglong Lin
 * @version Java 11 / VSCode
 * @since   2024-6-18 (date of last revision) 
 * @param <E> the type of elements in this list
 */
public class LinkedList<E> implements List<E> {
    // Data members
    private Node head, tail;
    private int size;

    /**
     * Inner class Node
     */
    private class Node {
        E value;
        Node next;
        Node prev;

        Node(E initialValue) {
            value = initialValue;
            next = null;
            prev = null;
        }
    }

    /**
     * Default Constructor
     * creates an empty linked list
     * Time complexity: O(1)
     */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Get the number of nodes in the list
     *
     * @return the number of nodes in the list
     * Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Clear the list by resetting size to 0 and head and tail to null
     * Time complexity: O(1)
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Check if the list is empty
     *
     * @return true if the list is empty
     * Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Adding a value at the tail of the list
     * Calls addLast
     *
     * @param item the value to be added
     * @return true if the operation was successful
     * Time complexity: O(1)
     */
    public boolean add(E item) {
        return addLast(item);
    }

    /**
     * Linear search method
     *
     * @param o the object being searched
     * @return true if o was found in this list, false otherwise
     * Time complexity: O(n)
     */
    public boolean contains(Object o) {
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            E element = iter.next();
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adding a value at the head of the list
     *
     * @param value the value to be added
     * @return true if the operation was successful
     * Time complexity: O(1)
     */
    public boolean addFirst(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        return true;
    }

    /**
     * Adding a value at the tail of the list
     *
     * @param item the value to be added
     * @return true if the operation was successful
     * Time complexity: O(1)
     */
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Method to add a new item at a given position index
     *
     * @param index the position where item should be added
     * @param item  the value of the element to be added
     * @return true if item was added successfully, false otherwise
     * @throws ArrayIndexOutOfBoundsException if index < 0 or index > size
     * Time complexity: O(n)
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node newNode = new Node(item);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    /**
     * Get the value of the node at the head of the list
     *
     * @return value of the node at the head
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(1)
     */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Get the value of the node at the tail of the list
     *
     * @return value of the node at the tail
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(1)
     */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes the node at the head of the list
     *
     * @return the value of the removed node
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(1)
     */
    public E removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        E value = head.value;
        head = head.next;
        if (head == null)
            tail = null;
        else
            head.prev = null;
        size--;
        return value;
    }

    /**
     * Removes the node at the tail of the list
     *
     * @return the value of the removed node
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(n)
     */
    public E removeLast() {
        if (tail == null)
            throw new NoSuchElementException();
        E value = tail.value;
        tail = tail.prev;
        if (tail == null)
            head = null;
        else
            tail.next = null;
        size--;
        return value;
    }

    /**
     * Remove an object o from the list
     *
     * @param o the object to be removed
     * @return true if o was found and removed, false if o not found
     * Time complexity: O(n)
     */
    public boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                if (current == head)
                    removeFirst();
                else if (current == tail)
                    removeLast();
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * toString method
     *
     * @return a formatted string that contains the values of all the nodes in the list
     * Time complexity: O(n)
     */
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        Node node = head;
        while (node.next != null) {
            output.append(node.value).append(" ");
            node = node.next;
        }
        output.append(node.value).append("]");
        return output.toString();
    }

    /**
     * iterator method
     *
     * @return an iterator object pointing to the first value in the list
     * Time complexity: O(1)
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner class that implements the interface Iterator
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * hasNext method
         *
         * @return true if the current is not null
         * Time complexity: O(1)
         */
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * next method
         *
         * @return the value of the node referenced by current and
         * modifies current to hold the reference of the next node
         * @throws NoSuchElementException if current is null
         * Time complexity: O(1)
         */
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }

    // New methods to be added

    /**
     * Adds all of the elements in the specified collection to this list
     *
     * @param c the collection containing elements to be added
     * @return true if this list changed as a result of the call
     * Time complexity: O(n)
     */
    public boolean addAll(Collection<E> c) {
        boolean modified = false;
        for (Iterator<E> iterator = c.iterator(); iterator.hasNext();) {
            E element = iterator.next();
            if (addLast(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Compares the specified object with this list for equality
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     * Time complexity: O(n)
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;
        List<?> list = (List<?>) o;
        if (list.size() != size)
            return false;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (!current.value.equals(list.get(i)))
                return false;
            current = current.next;
        }
        return true;
    }

    /**
     * Returns the value of the element at the specified position in this list
     *
     * @param index the position of the element to return
     * @return the value of the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * Time complexity: O(n)
     */
    public E get(int index) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Replaces the value of the element at the specified position in this list with the specified value
     *
     * @param index the position of the element to replace
     * @param newVal the value to be stored at the specified position
     * @return the value previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * Time complexity: O(n)
     */
    public E set(int index, E newVal) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldVal = current.value;
        current.value = newVal;
        return oldVal;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the specified element, or -1 if this list does not contain the element
     * Time complexity: O(n)
     */
    public int indexOf(Object o) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.value.equals(o))
                return i;
            current = current.next;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     *
     * @param o the element to search for
     * @return the index of the last occurrence of the specified element, or -1 if this list does not contain the element
     * Time complexity: O(n)
     */
    public int lastIndexOf(Object o) {
        Node current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.value.equals(o))
                return i;
            current = current.prev;
        }
        return -1;
    }

    /**
     * Removes the element at the specified position in this list
     *
     * @param index the position of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * Time complexity: O(n)
     */
    public E remove(int index) {
        checkIndex(index);
        if (index == 0)
            return removeFirst();
        if (index == size - 1)
            return removeLast();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E value = current.value;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return value;
    }

    /**
     * Sorts this list according to the order induced by the specified Comparator
     *
     * @param c the Comparator used to compare list elements
     * Time complexity: O(n log n)
     */
    public void sort(Comparator<E> c) {
        if (size <= 1)
            return;
        Object[] array = toArray();
        Arrays.sort((E[]) array, c);
        Node current = head;
        for (int i = 0; i < size; i++) {
            current.value = (E) array[i];
            current = current.next;
        }
    }

    /**
     * Check if the index is valid
     *
     * @param index to be checked
     * @throws IndexOutOfBoundsException if index is out of bounds
     * Time complexity: O(1)
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element)
     *
     * @return an array containing all of the elements in this list in proper sequence
     * Time complexity: O(n)
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node current = head;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }
}