package org.example.proxy;

import java.util.Objects;

public class Channel<T> {

    private final T[] partialData;

    public Channel(T[] partialData) {
        Objects.requireNonNull(partialData);
        if (partialData.length != 10) {
            throw new IllegalArgumentException("[] length is not 10");
        }
        this.partialData = partialData;
    }

    public T[] getPartialData() {
        return partialData;
    }

}
