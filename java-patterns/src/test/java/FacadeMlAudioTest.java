import org.example.facade.FacadeMlAudio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты FacadeMlAudio
 * Исключений не выбрасывают.
 * @author  Nikita Bochkov
 */

class FacadeMlAudioTest {

    FacadeMlAudio getInstanceFacadeMlAudio() {
        return new FacadeMlAudio();
    }

    @Test
    void testTrainMlDoesNotThrow() {
        FacadeMlAudio facadeMlAudio = getInstanceFacadeMlAudio();
        Assertions.assertDoesNotThrow(facadeMlAudio::startTrain);
        Assertions.assertDoesNotThrow(()-> System.out.println(facadeMlAudio.generateHtmlTable()));
        Assertions.assertDoesNotThrow(facadeMlAudio::reset);
        Assertions.assertEquals("", facadeMlAudio.generateHtmlTable());
    }

}
