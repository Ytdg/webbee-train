import org.example.builder.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * Тест демонстрирует создание Cookie.
 * @author  Nikita Bochkov
 * */
public class CookieBuilderTest {


    @Test
    void createInstance_withSetProperty() {
        Cookie cookie = new Cookie.Builder(false).domain("test.org")
                .activePage("num 1")
                .language("en")
                .idSession(10).build();

        Assertions.assertEquals(
                """
                        Authenticated:false
                        Active Page:num 1
                        Id session:10
                        Language:en
                        Domain:test.org""",cookie.toString()
        );

        cookie =  new Cookie.Builder(false).domain("test.org")
                .activePage(null)
                .language(null)
                .idSession(10).build();

        Assertions.assertEquals(
                """
                        Authenticated:false
                        Active Page:null
                        Id session:10
                        Language:null
                        Domain:test.org""",cookie.toString()
        );
    }

}
