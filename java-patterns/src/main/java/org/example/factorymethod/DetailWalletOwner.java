package org.example.factorymethod;
import java.util.Objects;

public record DetailWalletOwner(String name, String lastName) {

    public DetailWalletOwner(String name, String lastName) {
        this.name = Objects.requireNonNull(name);
        this.lastName = Objects.requireNonNull(lastName);
    }

}
