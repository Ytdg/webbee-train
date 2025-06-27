package org.example.proxy;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CodeCollectorProxy implements DataCollector<Channel<NumberSecret>> {

    private final CodeCollector codeCollector = new CodeCollector();
    private HashSet<UUID> cacheCode = new HashSet<>(); //UUID implementation equals
    private static final Logger LOGGER = Logger.getLogger(CodeCollectorProxy.class.getName());

    @Override
    public boolean acceptData(Channel<NumberSecret> data, Consumer<String> extractData) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(extractData);
        boolean isSuccessfully;

        if (Objects.isNull(cacheCode)) {
            cacheCode = new HashSet<>();
        }
        isSuccessfully = writeToCache(data, extractData);

        return isSuccessfully;
    }

    private boolean writeToCache(Channel<NumberSecret> data, Consumer<String> extractData) {
        if (!cacheCode.contains(data.uuid())) {
             cacheCode.add(data.uuid());
            if (!codeCollector.acceptData(data, extractData)) {
                LOGGER.info("Proxy error extract for id channel:" + data.uuid());
                return false;
            }
        } else {
            // extract data from other resource (redis as example)
        }
        return true;
    }


    public void clearCache() {
        cacheCode = null; // method can be clear cache with schedule as example
    }
}
