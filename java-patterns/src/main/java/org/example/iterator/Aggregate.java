package org.example.iterator;

import java.util.Iterator;
import java.util.Set;

interface Aggregate<T> {
    Iterator<T> createIterator(); // define iterator

}
