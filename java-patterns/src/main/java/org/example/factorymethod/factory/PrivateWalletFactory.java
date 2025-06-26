package org.example.factorymethod.factory;

import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.SberWallet;
import org.example.factorymethod.concreteproduct.VtbWallet;
import org.example.factorymethod.concreteproduct.Wallet;

public class PrivateWalletFactory extends AbstractWalletFactory {

    @Override
    protected Wallet createWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner) {
        return switch (typeWallet) {
            case YOURSELF -> new SberWallet(detailWalletOwner);
            case BUSINESS -> new VtbWallet(detailWalletOwner);
        };
    }
}
