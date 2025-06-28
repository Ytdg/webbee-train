package org.example.facade.subsystem;

import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * SubSystem.
 * <p>
 * Класс реализует предобработку входящих битов.
 * </p>
 * @author Nikita Bochkov
 * */
public class NormalizerInputBit {

    private final Random random = new Random();

    public DoubleStream normalize(IntStream intStream) {
        return intStream.filter(this::removeNoise).mapToDouble(this::reduce);
    }

    /**
     * предикат убирает шум.
     * @param value если значение == 0, то это шум.
     * */
    private boolean removeNoise(int value) {
        return value != 0;
    }

    /**
     * mapper уменьшает входящий бит
     * */
    private double reduce(int value) {
        double randomNumber = (random.nextDouble() * 0.5) + 0.1;
        return value - randomNumber;
    }

}
