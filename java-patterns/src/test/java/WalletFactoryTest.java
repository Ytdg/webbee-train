import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.Wallet;
import org.example.factorymethod.factory.AbstractWalletFactory;
import org.example.factorymethod.factory.PublicWalletFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WalletFactoryTest {

    @Test
    public void createPublicWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory,TypeWallet.YOURSELF);
        createWallet(walletFactory,TypeWallet.BUSINESS);
    }

    @Test
    public void createPrivateWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory,TypeWallet.YOURSELF);
        createWallet(walletFactory,TypeWallet.BUSINESS);
    }


    void createWallet(AbstractWalletFactory creator, TypeWallet typeWallet) {
        Wallet wallet = creator.orderWallet(typeWallet,withValidDetailWalletOwner());
        Assert.assertTrue(wallet.isVerify());
        wallet=creator.orderWallet(typeWallet,withNotValidNameDetailWalletOwner());
        Assert.assertFalse(wallet.isVerify());
        wallet=creator.orderWallet(typeWallet,withNotValidDetailWalletOwner());
        Assert.assertFalse(wallet.isVerify());
        wallet=creator.orderWallet(typeWallet,withNotValidLastNameDetailWalletOwner());
        Assert.assertFalse(wallet.isVerify());
    }

    DetailWalletOwner withValidDetailWalletOwner() {
        return new DetailWalletOwner("Alexand", "Brunov");
    }

    DetailWalletOwner withNotValidNameDetailWalletOwner() {
        return new DetailWalletOwner("", "Brunov");
    }

    DetailWalletOwner withNotValidLastNameDetailWalletOwner() {
        return new DetailWalletOwner("Alexandr", "");
    }

    DetailWalletOwner withNotValidDetailWalletOwner() {
        return new DetailWalletOwner("", "");
    }

}
