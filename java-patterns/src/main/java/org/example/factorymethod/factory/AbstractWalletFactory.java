package org.example.factorymethod.factory;

import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.Wallet;

/**
 * Creator:
 * Класс делегирует создание кошелька дочерникм классам (фабрикам)
 *<p>
 * Фабричный метод позволяет совершить некоторую цепочку действий по пути к дочерним классам. Каждый класс реализиует только свою логику.
 *</p>
 * */
public abstract class AbstractWalletFactory {


    public Wallet orderWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner) {
        Wallet wallet = createWallet(typeWallet, detailWalletOwner);
        wallet.verify(); //verification wallet
        return wallet;
    }

    /**
     * Фабричный метод создания кошелька.
     *
     * @param typeWallet - тип кошелька под цели. Значение может быть любым.
     * @param detailWalletOwner - информация пользователя.
     * */
    protected abstract Wallet createWallet(TypeWallet typeWallet, DetailWalletOwner detailWalletOwner);

}
