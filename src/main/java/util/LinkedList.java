package util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class LinkedList<T> implements Collection<T> {

    /**
     * Create node class for linked list
     */
    public class Node {
        Node next;
        T value;

        public Node(T value, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    int size;
    Node head;
    Node tail;

    /**
     * Create a new LinkedList
     * Set the head to null
     * tail is head at initial
     */
    public LinkedList() {
        head = null;
        tail = head;
    }

    /**
     * Get the size of LinkedList
     *
     * @return size of LinkedList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Whether a LinkedList is empty or not
     *
     * @return True if the size is zero
     * False if the size is not zero
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Whether an Object is in the LinkedList
     *
     * @param o element whose presence in this collection is to be tested
     * @return true if o is in, false if not
     */
    @Override
    public boolean contains(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o))
                return true;

            current = current.next;
        }
        return false;
    }

    /**
     * Create an iterator for LinkedList
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        if (isEmpty()) {
            return Collections.<T>emptyList().iterator();
        }
        return new Iterator<T>() {
            private Node currentNode = null;

            @Override
            public boolean hasNext() {
                return currentNode != tail;
            }

            @Override
            public T next() {
                if (currentNode == null) {
                    currentNode = head;
                    return currentNode.value;
                }
                currentNode = currentNode.next;
                return currentNode.value;
            }
        };
    }

    /**
     * convert linkedlist to object array
     * @return The object array
     */
    @Override
    public Object[] toArray() {
        Object[] toarray = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            toarray[i] = current.value;
            current = current.next;
        }
        return toarray;
    }

    /**
     * add the given object to the tail of LinkedList
     * @param o element whose presence in this collection is to be ensured
     * @return true if added
     */
    @Override
    public boolean add(Object o) {
        Node current = new Node((T) o, null);
        if (head == null) {
            head = tail = current;
        } else {
            tail.next = current;
            tail = tail.next;
        }
        size++;
        return current == tail;
    }

    /**
     * remove the given object in LinkedList
     * @param o element to be removed from this collection, if present
     * @return true if o is removed, false if o not find
     */
    @Override
    public boolean remove(Object o) {
        Node n = head;
        Node p = null;
        while (n != null && !o.equals(n.value)) {
            p = n;
            n = n.next;
        }
        if (n == null) return false;
        size--;
        if (p == null) head = n.next;
        else p.next = n.next;
        return true;
    }

    /**
     * add all objects in collection c
     * @param c collection containing elements to be added to this collection
     * @return true
     */
    @Override
    public boolean addAll(Collection c) {
        for (Object n : c) {
            add(n);
        }
        return true;
    }

    /**
     * remove all objects in LinkedList
     */
    @Override
    public void clear() {
        head = null;
        tail = head;
    }

    /**
     * I don't implement this since the method won't be used
     * @param c collection containing elements to be retained in this collection
     * @return false
     */
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    /**
     * remove all the object in the collection
     * @param c collection containing elements to be removed from this collection
     * @return true if removed all
     */
    @Override
    public boolean removeAll(Collection c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    /**
     * Whether LinkedList contains all the object in Collection
     * @param c collection to be checked for containment in this collection
     * @return true if all contained, false if not
     */
    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    /**
     * convert linkedlist to object array
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return The object array
     */
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    /**
     * remove the first node in LinkedList
     * @return the object removed
     */
    public T removeFirst() {
        Node current = head;
        head = head.next;
        size--;
        return current.value;
    }
}
