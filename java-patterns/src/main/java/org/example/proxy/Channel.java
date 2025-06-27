package org.example.proxy;

import java.util.Objects;
import java.util.UUID;

public record Channel<T>(T[] partialData, UUID uuid) {

    public Channel {
        Objects.requireNonNull(partialData);
        if (partialData.length != 10) {
            throw new IllegalArgumentException("[] length is not 10");
        }
        uuid = UUID.randomUUID();
    }

}
