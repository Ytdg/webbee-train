package org.example.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * ConcreteAggregate.
 * Реализует базовую логику работы коллекции.
 * @author  Nikita Bochkov
 * */
public class ObjectCollection<T> implements Aggregate<T> {

    private final List<T> objects = new ArrayList<>();

    public void addObject(T object) {
        Objects.requireNonNull(object);
        objects.add(object);
    }

    public void remove(int index) {
        objects.remove(index);
    }

    public int size() {
        return objects.size();
    }

    @Override
    public Iterator<T> createIterator() {
        return new ObjectIterator<>(objects);
    }

}
