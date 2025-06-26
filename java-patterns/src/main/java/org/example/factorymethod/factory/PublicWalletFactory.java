package org.example.factorymethod.factory;

import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.*;

public class PublicWalletFactory extends AbstractWalletFactory {
    @Override
    protected Wallet createWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner) {
        return switch (typeWallet) {
            case YOURSELF -> new AlphaWallet(detailWalletOwner);
            case BUSINESS -> new TWallet(detailWalletOwner);
        };
    }
}
