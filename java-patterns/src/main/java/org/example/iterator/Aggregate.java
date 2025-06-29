package org.example.iterator;

import java.util.Iterator;

/**
 * Aggregate.
 * Содержит один метод для создания итератора.
 * @author  Nikita Bochkov
 * */
interface Aggregate<T> {

    /**
     * @return объявленный итератор
     * */
    Iterator<T> createIterator(); // define iterator

}
