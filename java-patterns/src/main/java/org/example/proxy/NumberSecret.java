package org.example.proxy;

import java.util.Objects;

/**
 * Data класс, содержайщий закодированное число в Base64
 * @author Nikita Bochkov
 * */
public record NumberSecret(String encryptedNumber) {

    public NumberSecret(String encryptedNumber) {
        this.encryptedNumber = Objects.requireNonNull(encryptedNumber);
    }

}
