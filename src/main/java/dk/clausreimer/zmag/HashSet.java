package dk.clausreimer.zmag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HashSet<T> implements Set<T> {
    private Map<T, Object> values = new HashMap<T, Object>();

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean contains(Object object) {
        return values.containsKey(object);
    }

    public Iterator<T> iterator() {
        return values.keySet().iterator();

    }

    public Object[] toArray() {
        return values.keySet().toArray();
    }

    public <T> T[] toArray(T[] array) {
        return values.keySet().toArray(array);
    }

    public boolean add(T value) {
        boolean hasValue;

        hasValue = values.containsKey(value);
        if (!hasValue) {
            values.put(value, null);
        }

        return !hasValue;
    }

    public boolean remove(Object object) {
        boolean hasValue;

        hasValue = values.containsKey(object);
        if (hasValue) {
            values.remove(object);
        }

        return hasValue;
    }

    public boolean containsAll(Collection<?> collection) {
        return values.keySet().containsAll(collection);
    }

    public boolean addAll(Collection<? extends T> collection) {
        boolean changed;

        changed = false;

        for (T object : collection) {
            if (add(object)) {
                changed = true;
            }
        }

        return changed;
    }

    public boolean retainAll(Collection<?> collection) {
        return values.keySet().retainAll(collection);
    }

    public boolean removeAll(Collection<?> collection) {
        return values.keySet().removeAll(collection);
    }

    public void clear() {
        values.clear();
    }
}
