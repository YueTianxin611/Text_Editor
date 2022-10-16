package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class mySet<T> implements Set<T> {
    LinkedList<T> set;

    /**
     * Create a new set using LinkedList
     */
    public mySet() {
        set = new LinkedList<>();
    }

    int size;

    /**
     * add new object to the set
     *
     * @param o element whose presence in this collection is to be ensured
     * @return true if added
     */
    @Override
    public boolean add(Object o) {
        if (set.contains(o)) {
            return true;
        } else {
            set.add(o);
            size++;
            return true;
        }
    }

    /**
     * get size of a set
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * whether set is empty or not
     * @return true if size is zero, false if not
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }


    /**
     * I don't implement the rest methods in Set since they won't be used ih this project
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        set = new LinkedList<>();
    }
}
