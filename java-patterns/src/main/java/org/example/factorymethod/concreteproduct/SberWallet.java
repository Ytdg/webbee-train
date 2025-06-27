package org.example.factorymethod.concreteproduct;

import org.example.factorymethod.DetailWalletOwner;
import java.util.Objects;

/**
 * ConcreteProduct
 * <p>
 *     Реальный объект, который создается
 * </p>
 * */
public class SberWallet extends Wallet {

    public SberWallet(DetailWalletOwner detailWalletOwner) {
        super(Objects.requireNonNull(detailWalletOwner));
    }

    /**
     * Если возраст > 18, значит верификация разрешена
     * */
    @Override
    public void verify() {
        this.setVerify(getDetailWalletOwner().age() > 18); //can be to super class
    }

}
