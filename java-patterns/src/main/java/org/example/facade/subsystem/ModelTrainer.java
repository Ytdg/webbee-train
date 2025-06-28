package org.example.facade.subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ModelTrainer {

    private HashMap<Integer, List<Double>> weights = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(ModelTrainer.class.getName());
    private final Random random = new Random();

    public HashMap<Integer, List<Double>> train(IntStream bits) {
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

    private List<Double> createWeightsVector() {
        List<Double> vectorWeights = new ArrayList<>();
        for (int i = 0; i < random.nextInt(50) + 1; i++) {
            vectorWeights.add(random.nextDouble());
        }
        LOGGER.info("Create vector weights");
        return vectorWeights;
    }

    private HashMap<Integer, List<Double>> copy() {
        HashMap<Integer, List<Double>> copyMap = new HashMap<>();
        for (Integer key : weights.keySet()) {
            List<Double> originalList = weights.get(key);
            List<Double> copiedList = new ArrayList<>(originalList);
            copyMap.put(key, copiedList);
        }
        return copyMap;
    }
}
