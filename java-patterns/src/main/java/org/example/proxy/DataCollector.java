package org.example.proxy;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface DataCollector<T> {

    boolean acceptData(T data, Consumer<String> extractData);

}
