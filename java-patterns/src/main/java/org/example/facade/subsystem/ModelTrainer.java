package org.example.facade.subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

/**
 * SubSystem
 * <p>
 * Класс реализует обучение модели на входящих битах
 * </p>
 * @author  Nikita Bochkov
 * */
public class ModelTrainer {

    private HashMap<Double, List<Double>> weights = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(ModelTrainer.class.getName());
    private final Random random = new Random();

    public HashMap<Double, List<Double>> train(DoubleStream bits) {
        // train...
        bits.forEach(s -> {
            if (!weights.containsKey(s)) { // values are expected to be unique to the weights
                weights.put(s, createWeightsVector());
            }
        });
        LOGGER.info("Train successful");
        return copy();
    }

    public void resetWeights() {
        weights = new HashMap<>();
        LOGGER.info("Reset weights");
    }

    /**
     * метод создает вектор весов
     * @return  вектор double
     * */
    private List<Double> createWeightsVector() {
        List<Double> vectorWeights = new ArrayList<>();
        for (int i = 0; i < random.nextInt(50) + 1; i++) {
            vectorWeights.add(random.nextDouble());
        }
        LOGGER.info("Create vector weights");
        return vectorWeights;
    }

    /**
     * метод реализует глубокое копирование полученных весов
     * @return  полная копия оригинального HashMap
     * */
    private HashMap<Double, List<Double>> copy() {
        HashMap<Double, List<Double>> copyMap = new HashMap<>();
        for (Double key : weights.keySet()) {
            List<Double> originalList = weights.get(key);
            List<Double> copiedList = new ArrayList<>(originalList);
            copyMap.put(key, copiedList);
        }
        return copyMap;
    }
}
