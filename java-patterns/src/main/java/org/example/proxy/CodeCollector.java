package org.example.proxy;

import java.util.Objects;
import java.util.Optional;
import java.util.Base64;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Class - RealSubject.
 * <p>
 *     Данный класс обрабатывает канал (Channel)
 * </p>
 * @author Nikita Bochkov
 * */
public class CodeCollector implements DataCollector<Channel<NumberSecret>> {

    private static final Logger LOGGER = Logger.getLogger(CodeCollector.class.getName());

    /**
     * метод извлекает из канала данные и декодирует их в строковой формат в виде числа и последовательно поставляет потребителю
     * */
    @Override
    public boolean acceptData(Channel<NumberSecret> data, Consumer<String> extractData) {
        Objects.requireNonNull(data);
        boolean isSuccessfully = true;
        for (NumberSecret num : data.partialData()
        ) {
            Optional<Long> decodeNum = decode(num.encryptedNumber());
            if (decodeNum.isEmpty()) {
                isSuccessfully = false;
            } else {
                extractData.accept(decodeNum.get() + "|ID_CHANNEL:" + data.uuid().toString());
            }
        }
        // save to file example
        return isSuccessfully;
    }

    private Optional<Long> decode(String secretNum) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(secretNum); // essentially,can use any crypt for example Base64
            String value = new String(decodedBytes);
            return Optional.of(Long.parseLong(value));

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return Optional.empty();
        }

    }

}
