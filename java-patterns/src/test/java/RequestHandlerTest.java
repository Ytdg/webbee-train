import org.example.chainofresponsibility.handlers.AbstractHandlerRequest;
import org.example.chainofresponsibility.handlers.RemoteAddressHandler;
import org.example.chainofresponsibility.handlers.SessionDestroyHandler;
import org.example.chainofresponsibility.handlers.SessionTimeOutHandler;
import org.junit.Test;

import java.util.function.Consumer;

public class RequestHandlerTest {
    @Test
    public void testChainHandlerRequestInOrder() {
        AbstractHandlerRequest sessionTimeOutHandler = createSessionTimeOutHandler(s -> {
        });
        AbstractHandlerRequest sessionDestroyHandler = createSessionDestroyHandler(s -> {
            s.setNextHandler(sessionTimeOutHandler);
        });
        AbstractHandlerRequest remoteAddressHandler = createRemoteAddressHandler(s -> {
            s.setNextHandler(sessionDestroyHandler);
        });
    }

    RemoteAddressHandler createRemoteAddressHandler(Consumer<AbstractHandlerRequest> changeHandler) {
        RemoteAddressHandler remoteAddressHandler = new RemoteAddressHandler();
        changeHandler.accept(remoteAddressHandler);
        return remoteAddressHandler;
    }

    SessionDestroyHandler createSessionDestroyHandler(Consumer<AbstractHandlerRequest> changeHandler) {
        SessionDestroyHandler sessionDestroyHandler = new SessionDestroyHandler();
        changeHandler.accept(sessionDestroyHandler);
        return sessionDestroyHandler;
    }

    SessionTimeOutHandler createSessionTimeOutHandler(Consumer<AbstractHandlerRequest> changeHandler) {
        SessionTimeOutHandler sessionTimeOutHandler = new SessionTimeOutHandler();
        changeHandler.accept(sessionTimeOutHandler);
        return sessionTimeOutHandler;

    }
}
