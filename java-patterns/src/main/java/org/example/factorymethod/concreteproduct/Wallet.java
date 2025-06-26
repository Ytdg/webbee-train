package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.UUID;

public abstract class Wallet {

    private final UUID id = UUID.randomUUID();
    private final DetailWalletOwner detailWalletOwner;
    private boolean verify;

    protected Wallet(DetailWalletOwner detailWalletOwner) {
        this.detailWalletOwner = detailWalletOwner;
    }

    public UUID getId() {
        return id;
    }

    public boolean isVerify() {
        return verify;
    }

    protected void setVerify(boolean value) {
        if (value) {
            this.verify = true;
            System.out.println("Verification is successful");
            return;
        }
        System.out.println("Verification is not successful");
    }

    public DetailWalletOwner getDetailWalletOwner() {
        return detailWalletOwner;
    }

    public abstract void verify();

}
