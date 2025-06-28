package org.example.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * ConcreteIterator и Iterator (взят объявленный).
 * Класс реализует итерацию как immutable list
 * */
public class ObjectIterator<T> implements Iterator<T> {

    private final List<T> listObjects;
    private final int size;
    private int index;

    ObjectIterator(List<T> listObjects) {
        this.listObjects = listObjects; // immutable list in time iteration
        this.size = listObjects.size();
    }

    /**
     * Метод проверяет изменился размер списка (проверка на immutable list).
     * @throws IllegalArgumentException возникает если список поменял размер
     * */
    private void stateImmutableList() {
        if (listObjects.size() != this.size) {
            throw new IllegalStateException("immutable it time iteration");
        }
    }

    @Override
    public boolean hasNext() {
        stateImmutableList();
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
