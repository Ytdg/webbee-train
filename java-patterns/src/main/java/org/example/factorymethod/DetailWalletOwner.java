package org.example.factorymethod;

import java.util.Objects;
/**
 * Класс содержащий информацию пользователя любого кошелька
 * @param name - имя владелца кошелька
 * @param lastName - фамилия владелца кошелька
 * @param age - возраст владелца кошелька
 * */

public record DetailWalletOwner(String name, String lastName, int age) {

    public DetailWalletOwner {

        Objects.requireNonNull(name);
        Objects.requireNonNull(lastName);

        if (age <= 0) {
            throw new IllegalArgumentException("Incorrect age: " + age);
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        if (name.equals(lastName)) {
            throw new IllegalArgumentException("Name and last name cannot be the same");
        }
    }

}
