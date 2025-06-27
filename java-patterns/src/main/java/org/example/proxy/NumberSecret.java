package org.example.proxy;

import java.util.Objects;

public record NumberSecret(String encryptedNumber) {

    public NumberSecret(String encryptedNumber) {
        this.encryptedNumber = Objects.requireNonNull(encryptedNumber);
    }

}
