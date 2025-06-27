package org.example.proxy;

import java.util.UUID;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Class - Proxy.
 * <p>
 *     Паттерн позволяет перехватить объет и провести с ним операции до фактического прехода к RealSubject
 *     Данный класс видит клиент. В примере используется для оптимизации работы с дорогостоящими операциями (запись в файл и т.д)
 * </p>
 * @author Nikita Bochkov
 * */
public class CodeCollectorProxy implements DataCollector<Channel<NumberSecret>> {

    private final CodeCollector codeCollector = new CodeCollector();
    private HashSet<UUID> cacheCode = new HashSet<>(); //UUID implementation equals
    private static final Logger LOGGER = Logger.getLogger(CodeCollectorProxy.class.getName());

    /**
     * @return boolean значение: если true - все значения успешно извлечены из канала, false - иначе
     * */
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

    /**
     * Кэширует Уникальный id посредством записи в HashSet. Если id такой уже был, обращаться к RealSubjeсt не нужно для обработки
     * данных. Можно обратиться к другому (например, менее дорогостощему)
     *
     * @return  булевое значение, успешно ли извлечены данные или нет
     * */
    private boolean writeToCache(Channel<NumberSecret> data, Consumer<String> extractData) {
        if (!cacheCode.contains(data.uuid())) {
             cacheCode.add(data.uuid());
            if (!codeCollector.acceptData(data, extractData)) {
                LOGGER.info("Proxy error extract for id channel:" + data.uuid());
                return false;
            }
        }
        // extract data from other resource (redis as example)
        return true;
    }

    public void clearCache() {
        cacheCode = null; // method can be clear cache with schedule as example
    }

}
