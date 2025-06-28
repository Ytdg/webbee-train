package org.example.facade.subsystem;

import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class NormalizerInputBit {
    private final Random random = new Random();

    public DoubleStream normalize(IntStream intStream) {
        return intStream.filter(this::removeNoise).mapToDouble(this::reduce);
    }

    private boolean removeNoise(int value) {
        return value != 0;
    }

    private double reduce(int value) {
        double randomNumber = (random.nextDouble() * 0.5) + 0.1;
        return value - randomNumber;
    }

}
