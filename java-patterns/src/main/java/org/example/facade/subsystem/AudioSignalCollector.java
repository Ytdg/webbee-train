package org.example.facade.subsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * SubSystem.
 * <p>
 * Реализует получение битов с разных источников
 * </p>
 *
 * @author Nikita Bochkov
 */
public class AudioSignalCollector {

    private final Driver driverFlash = new Driver();
    private static final Logger LOGGER = Logger.getLogger(AudioSignalCollector.class.getName());

    private final Driver driverDatabase = new Driver();

    public AudioSignalCollector() {
        // driver flash
        long idFlashDrive = 1234141L;
        driverFlash.connect(idFlashDrive); // of course, need closing connect for descriptors
        // driver database
        long idDatabaseDrive = 3245235L;
        driverDatabase.connect(idDatabaseDrive);
    }

    /**
     * чтение битов из источников
     *
     * @param reader имеет реализацию Driver
     * @return возврощает поток битов
     */
    private IntStream read(Reader reader) {
        return reader.readBits().stream().flatMapToInt(Arrays::stream);
    }

    public IntStream collectFromDatabase() {
        LOGGER.info("Reading from the database");
        return read(driverDatabase);
    }

    public IntStream collectFromFlashDrive() {
        LOGGER.info("Reading from the Flash");
        return read(driverFlash);
    }

}

interface Reader {

    /**
     * метод описывает чтение битов
     * @return список из массивов битов
     */
    List<int[]> readBits();

}

class Driver implements Reader {

    @Override
    public List<int[]> readBits() {

        Random random = new Random();

        int numArrays = 10;   // 10 bits each in list
        int arrayLength = 8;  // 8 bit 1 array
        List<int[]> bits = new ArrayList<>();
        for (int i = 0; i < numArrays; i++) {
            int[] bitArray = new int[arrayLength];
            for (int j = 0; j < arrayLength; j++) {
                bitArray[j] = random.nextInt(2); // 0  is noise
            }
            bits.add(bitArray);

        }
        return bits;
    }

    public void connect(long id) {
        if (id <= 0) {
            throw new IllegalStateException("Connect with id:" + id + " error");
        }
    }

    public void close() {

    }

}
