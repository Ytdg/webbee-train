package org.example.factorymethod.factory;

import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.AlphaWallet;
import org.example.factorymethod.concreteproduct.Wallet;
import org.example.factorymethod.concreteproduct.TWallet;

/**
 * ConcreteCreator:
 * Класс реализует логику создания эксземпляров государственных кошельков
 * */
public class PublicWalletFactory extends AbstractWalletFactory {

    @Override
    protected Wallet createWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner) {

        return switch (typeWallet) {
            case YOURSELF -> new AlphaWallet(detailWalletOwner);
            case BUSINESS -> new TWallet(detailWalletOwner);
        };

    }

}
