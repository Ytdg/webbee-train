package org.example.proxy;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * Subject. Дженерик - any type.
 * @author Nikita Bochkov
 * */
public interface DataCollector<T> {

    /**
     * Метод принимает данные и последовательно передает результирующие потребителю.
     * @param data объект для обработки
     * @param  extractData потребитель, принимающий результирующие данные последовательно для дальнейшей возможной обработки
     * */
    boolean acceptData(T data, Consumer<String> extractData);

}
