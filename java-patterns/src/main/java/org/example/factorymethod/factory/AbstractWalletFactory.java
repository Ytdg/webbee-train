package org.example.factorymethod.factory;

import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.Wallet;

public abstract class AbstractWalletFactory {

    public Wallet orderWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner) {
        Wallet wallet = createWallet(typeWallet, detailWalletOwner);
        wallet.verify();
        return wallet;
    }

    protected abstract Wallet createWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner);

}
