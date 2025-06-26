package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.Objects;

public class AlphaWallet extends Wallet {

    public AlphaWallet(DetailWalletOwner detailWalletOwner) {
        super(Objects.requireNonNull(detailWalletOwner));
    }

    @Override
    public void verify() {
        this.setVerify(!getDetailWalletOwner().name().isBlank());
    }

}
