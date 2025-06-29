import org.example.builder.Cookie;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * Тест демонстрирует создание Cookie.
 * @author  Nikita Bochkov
 * */
public class CookieBuilderTest {

    private  static  final Logger logger=Logger.getLogger(CookieBuilderTest.class.getName());
    @Test
    void createInstance_withSetProperty() {
        Cookie cookie = new Cookie.Builder(false).domain("test.org")
                .activePage("num 1")
                .language("en")
                .idSession(10).build();

        logger.info(cookie+"\n");

        cookie =  new Cookie.Builder(false).domain("test.org")
                .activePage(null)
                .language(null)
                .idSession(10).build();

        logger.info(cookie.toString());
    }

}
