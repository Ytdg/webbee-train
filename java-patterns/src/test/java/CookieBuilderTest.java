import org.example.builder.Cookie;
import org.junit.jupiter.api.Test;

public class CookieBuilderTest {

    @Test
    void createInstance_withSetProperty() {
        Cookie cookie = new Cookie.Builder(false).domain("test.org")
                .activePage("num 1")
                .language("en")
                .idSession(10).build();

        System.out.println(cookie+"\n");

        cookie =  new Cookie.Builder(false).domain("test.org")
                .activePage(null)
                .language(null)
                .idSession(10).build();
        System.out.println(cookie);
    }

}
