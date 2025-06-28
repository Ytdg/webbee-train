package org.example.iterator;


import java.util.Iterator;
import java.util.List;

public class ObjectIterator<T> implements Iterator<T> {

    private final List<T> listObjects;

    private int index;

    ObjectIterator(List<T> listObjects) {
        this.listObjects = listObjects;
    }

    @Override
    public boolean hasNext() {
        return index < listObjects.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        T object = listObjects.get(index);
        index++;
        return object;
    }

}
