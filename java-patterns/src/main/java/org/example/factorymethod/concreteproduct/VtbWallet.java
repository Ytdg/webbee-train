package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.Objects;

public class VtbWallet extends Wallet {

    public VtbWallet(DetailWalletOwner detailWalletOwner) {
        super(Objects.requireNonNull(detailWalletOwner));
    }

    @Override
    public void verify() {
        this.setVerify(!this.getDetailWalletOwner().lastName().isBlank());
    }

}
