package org.example.proxy;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CodeCollectorProxy implements DataCollector<Channel<NumberSecret>> {

    private final CodeCollector codeCollector = new CodeCollector();
    private HashMap<UUID, String> cacheCode = new HashMap<>(); //UUID implementation equals
    private static final Logger LOGGER = Logger.getLogger(CodeCollectorProxy.class.getName());

    @Override
    public boolean acceptData(Channel<NumberSecret> data, Consumer<String> transferResulData) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(transferResulData);
        boolean isSuccessfully = true;
        for (NumberSecret num : data.getPartialData()) {
            if (!cacheCode.containsKey(num.getUuid())) {
                if (!codeCollector.acceptData(data, s -> cacheCode.put(num.getUuid(), s))) {
                    isSuccessfully = false;
                    LOGGER.info("Proxy error for id:" + num.getUuid().toString());
                }
            }
            if (cacheCode.containsKey(num.getUuid())) {
                transferResulData.accept(num.getEncryptedNumber());
                LOGGER.info("Proxy for id:" + num.getUuid().toString());
            }
        }
        return isSuccessfully;
    }


    @Override
    public Stream<String> getStreamResult() {
        if (Objects.nonNull(cacheCode)) {
            return cacheCode.values().stream();
        }
        return codeCollector.getStreamResult();
    }


    @Override
    public Optional<String> getDataById(String id) {
        if (Objects.nonNull(cacheCode)) {
            return cacheCode.get(UUID.fromString(id)).describeConstable();
        }
        return codeCollector.getDataById(id);
    }

    public void clearCache() {
        cacheCode = null; // method can be clear cache with schedule as example
    }
}
