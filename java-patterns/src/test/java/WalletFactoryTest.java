import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.Wallet;
import org.example.factorymethod.factory.AbstractWalletFactory;
import org.example.factorymethod.factory.PublicWalletFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class WalletFactoryTest {

    @Test
    public void createPublicWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory, TypeWallet.YOURSELF);
        createWallet(walletFactory, TypeWallet.BUSINESS);
    }

    @Test
    public void createPrivateWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory, TypeWallet.YOURSELF);
        createWallet(walletFactory, TypeWallet.BUSINESS);
    }



    void createWallet(AbstractWalletFactory creator, TypeWallet typeWallet) {

        Wallet wallet = creator.orderWallet(typeWallet, withValidDetailWalletOwner());
        Assert.assertTrue(wallet.isVerify());
        assertThrows(IllegalArgumentException.class, () -> {
            creator.orderWallet(typeWallet, withNotValidNameDetailWalletOwner());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            creator.orderWallet(typeWallet, withNotValidLastNameDetailWalletOwner());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            creator.orderWallet(typeWallet, withNotValidDetailWalletOwner());
        });
    }

    DetailWalletOwner withValidDetailWalletOwner() {
        return new DetailWalletOwner("Alexand", "Brunov", 19);
    }

    DetailWalletOwner withNotValidNameDetailWalletOwner() {
        return new DetailWalletOwner("", "Brunov", 19);
    }

    DetailWalletOwner withNotValidLastNameDetailWalletOwner() {
        return new DetailWalletOwner("Alexandr", "", 19);
    }

    DetailWalletOwner withNotValidDetailWalletOwner() {
        return new DetailWalletOwner("", "", 0);
    }

}
