package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.Objects;

public class TWallet extends Wallet {

    public TWallet(DetailWalletOwner detailWalletOwner) {
        super(Objects.requireNonNull(detailWalletOwner));
    }

    @Override
    public void verify() {
        this.setVerify(!this.getDetailWalletOwner().lastName().isBlank());
    }

}
