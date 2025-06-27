import org.example.factorymethod.DetailWalletOwner;
import org.example.factorymethod.TypeWallet;
import org.example.factorymethod.concreteproduct.Wallet;
import org.example.factorymethod.factory.AbstractWalletFactory;
import org.example.factorymethod.factory.PublicWalletFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты не валидных данных на исключения IllegalArgumentException
 * <p>Тесты создания кошельков разных групп</p>
 * Исключения не выбрасывает.
 * */
public class WalletFactoryTest {

    @Test
     void createPublicWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory, TypeWallet.YOURSELF);
        createWallet(walletFactory, TypeWallet.BUSINESS);
    }

    @Test
     void createPrivateWalletFactory() {
        AbstractWalletFactory walletFactory = new PublicWalletFactory();
        createWallet(walletFactory, TypeWallet.YOURSELF);
        createWallet(walletFactory, TypeWallet.BUSINESS);
    }



    void createWallet(AbstractWalletFactory creator, TypeWallet typeWallet) {

        Wallet wallet = creator.orderWallet(typeWallet, withValidDetailWalletOwner());
        Assertions.assertTrue(wallet.isVerify());
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
