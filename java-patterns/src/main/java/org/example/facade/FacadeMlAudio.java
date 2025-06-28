package org.example.facade;

import org.example.facade.subsystem.AudioSignalCollector;
import org.example.facade.subsystem.ModelTrainer;
import org.example.facade.subsystem.NormalizerInputBit;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FacadeMlAudio {

    private final AudioSignalCollector audioSignalCollector;
    private final ModelTrainer modelTrainer;
    private final NormalizerInputBit normalizerInputBit;
    private static final Logger LOGGER = Logger.getLogger(FacadeMlAudio.class.getName());
    private HashMap<Integer, List<Double>> resultWeights = null;

    public FacadeMlAudio() {
        this.audioSignalCollector = new AudioSignalCollector();
        this.modelTrainer = new ModelTrainer();
        this.normalizerInputBit = new NormalizerInputBit();
    }

    private IntStream getCollectedBits() {
        return IntStream.concat(audioSignalCollector.collectFromDatabase(), audioSignalCollector.collectFromFlashDrive());
    }

    public void startTrain() {
        IntStream bits = getCollectedBits();
        DoubleStream normalizeBits = normalizerInputBit.normalize(bits);
        // can be more logic
        this.resultWeights = modelTrainer.train(bits);
    }

    public String generateHtmlTable() {
        if (Objects.nonNull(resultWeights)) {
            return FormatTable.createTable(resultWeights);
        } // can be created html document
        LOGGER.info("weights is null");
        return "";
    }

    public void reset() {
        this.resultWeights = null;
    }

}
