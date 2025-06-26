package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.Objects;

public class SberWallet extends Wallet {

    public SberWallet(DetailWalletOwner detailWalletOwner) {
        super(Objects.requireNonNull(detailWalletOwner));
    }

    @Override
    public void verify() {
        this.setVerify(getDetailWalletOwner().age() > 18); //can be to super class
    }

}
