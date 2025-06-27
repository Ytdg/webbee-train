package org.example.proxy;

import java.util.Objects;
import java.util.UUID;


/**
 * Объект от клиента.
 * @param partialData пакет данных, который должен быть размером 10.
 * @param uuid уникальный id, формируемый клюентом
 * @author  Nikita Bochkov
 * */
public record Channel<T>(T[] partialData, UUID uuid) {

    public Channel {
        Objects.requireNonNull(partialData);
        if (partialData.length != 10) {
            throw new IllegalArgumentException("[] length is not 10");
        }
        uuid = UUID.randomUUID();
    }

}
