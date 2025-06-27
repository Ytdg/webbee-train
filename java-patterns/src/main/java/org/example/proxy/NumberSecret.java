package org.example.proxy;

import java.util.Objects;
import java.util.UUID;

public class NumberSecret {

    private final String encryptedNumber;
    private final UUID uuid;

    public NumberSecret(String encryptedNumber) {
        this.uuid = UUID.randomUUID();
        this.encryptedNumber = Objects.requireNonNull(encryptedNumber);
    }

    public String getEncryptedNumber() {
        return this.encryptedNumber;
    }

    public UUID getUuid() {
        return this.uuid;
    }


    @Override
    public String toString() {
        return "id:" + uuid.toString() + "|" + "encrypt:" + encryptedNumber;
    }
}
