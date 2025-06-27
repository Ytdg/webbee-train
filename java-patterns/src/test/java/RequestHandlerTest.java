import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.Session;
import org.example.chainofresponsibility.StatusSession;
import org.example.chainofresponsibility.handlers.AbstractHandlerRequest;
import org.example.chainofresponsibility.handlers.RemoteAddressHandler;
import org.example.chainofresponsibility.handlers.SessionDestroyHandler;
import org.example.chainofresponsibility.handlers.SessionTimeOutHandler;
import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;
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

        remoteAddressHandler.handleServletRequest(cre);
        assertDoesNotThrow()
    }

    ServletRequest createValidRequestWithSessionCompleted() throws UnknownHostException {
        ServletRequest servletRequest = new ServletRequest("localhost");
        servletRequest.setSession(new Session(1245L, null, StatusSession.COMPLETED));
        return servletRequest;
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
