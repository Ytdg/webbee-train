package org.example.proxy;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CodeCollector implements DataCollector<Channel<NumberSecret>> {

    private static final Logger LOGGER = Logger.getLogger(CodeCollector.class.getName());

    @Override
    public boolean acceptData(Channel<NumberSecret> data, Consumer<String> transferResultData) {
        Objects.requireNonNull(data);
        boolean isSuccessfully = true;
        for (NumberSecret num : data.getPartialData()
        ) {
            Optional<Long> decodeNum = decode(num.getEncryptedNumber());
            if (decodeNum.isEmpty()) {
                isSuccessfully = false;
            } else {
                transferResultData.accept(decodeNum.get() + "ID:" + num.getUuid().toString());
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

    @Override
    public Optional<String> getDataById(String id) {
        Objects.requireNonNull(id);
        return getStreamResult()
                .filter(s -> {
                    String[] parts = s.split(" ID:");
                    if (parts.length > 1) {
                        return parts[1].equals(id);
                    }
                    return false;
                })
                .findFirst();

    }

    @Override
    public Stream<String> getStreamResult() {
        return Stream.empty(); // it's implementation for read file or other api where data (Thread.sleep example)
    }

}
