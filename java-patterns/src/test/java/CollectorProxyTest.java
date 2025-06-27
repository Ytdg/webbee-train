import org.example.proxy.Channel;
import org.example.proxy.CodeCollectorProxy;
import org.example.proxy.DataCollector;
import org.example.proxy.NumberSecret;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Тесты извличения данных из канала (channel).
 * Исключения не выбрасывает.
 * @author Nikita Bochkov
 * */
class CollectorProxyTest {

    DataCollector<Channel<NumberSecret>> createInstanceProxy() {
        return new CodeCollectorProxy();
    }

    @Test
    void testCreateInstanceChannelOnThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Channel<>(createValidNumberSecrets(110), UUID.randomUUID());
        });
        Assertions.assertDoesNotThrow(() -> {
            new Channel<>(createValidNumberSecrets(10), UUID.randomUUID()); //count should be 10
        });
    }

    @Test
    void testProxyExtractValidData() {
        DataCollector<Channel<NumberSecret>> proxy = createInstanceProxy();
        Channel<NumberSecret> channel = new Channel<>(createValidNumberSecrets(10), UUID.randomUUID());
        Assertions.assertTrue(proxy.acceptData(channel, System.out::println
        ));
    }

    @Test
    void testProxyExtractNotValidData() {
        DataCollector<Channel<NumberSecret>> proxy = createInstanceProxy();
        Channel<NumberSecret> channel = new Channel<>(createValidNumberSecrets(10), UUID.randomUUID());
        channel.partialData()[0] = new NumberSecret("not valid");
        Assertions.assertFalse(proxy.acceptData(channel, System.out::println
        ));
    }

    NumberSecret[] createValidNumberSecrets(int count) {
        ArrayList<NumberSecret> numberSecrets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numberSecrets.add(new NumberSecret(createEncryptNumber()));
        }
        return numberSecrets.toArray(new NumberSecret[]{});
    }

    String createEncryptNumber() {
        long val = new Random().nextLong();
        return Base64.getEncoder().encodeToString(String.valueOf(val).getBytes());
    }

}
