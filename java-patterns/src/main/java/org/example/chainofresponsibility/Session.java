package org.example.chainofresponsibility;

import java.util.Objects;

/**
 * @param id идендификатор сессии
 * @param payload полезная нагрузка сессии (nullable)
 * @param  statusSession статус сессии (not null)
 * @author  Nikita Bochkov
 * */
public record Session(long id, String payload, StatusSession statusSession) {

    public Session(long id, String payload, StatusSession statusSession) { //can be field "timeSession"
        this.id = id;
        this.payload = payload;
        this.statusSession = Objects.requireNonNull(statusSession);
    }

}
