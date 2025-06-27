import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.Session;
import org.example.chainofresponsibility.StatusSession;
import org.example.chainofresponsibility.handlers.AbstractHandlerRequest;
import org.example.chainofresponsibility.handlers.RemoteAddressHandler;
import org.example.chainofresponsibility.handlers.SessionDestroyHandler;
import org.example.chainofresponsibility.handlers.SessionTimeOutHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestHandlerTest {
    @Test
     void testChainHandlerRequestInOrder() {
        AbstractHandlerRequest sessionTimeOutHandler = createSessionTimeOutHandler(s -> {
        });
        AbstractHandlerRequest sessionDestroyHandler = createSessionDestroyHandler(s -> {
            s.setNextHandler(sessionTimeOutHandler);
        });
        AbstractHandlerRequest remoteAddressHandler = createRemoteAddressHandler(s -> {
            s.setNextHandler(sessionDestroyHandler);
        });
        //remoteAddressHandler->sessionDestroy
        assertDoesNotThrow(() -> {
            remoteAddressHandler.handleServletRequest(createValidRequestWithSessionCompleted());
        });

        //message "Request not allowed"
        assertDoesNotThrow(() -> {
            remoteAddressHandler.handleServletRequest(createNotAccessRequestWithSessionCompleted());
        });
        //remoteAddress->sessionDestroy(skip)->sessionTimeOut
        assertDoesNotThrow(() -> {
            remoteAddressHandler.handleServletRequest(createValidRequestWithSessionTemporary());
        });
        //for state session "Temporary" always should be payload
        assertThrows(NullPointerException.class,()->{
            remoteAddressHandler.handleServletRequest(createNotValidPayloadRequestWithSessionTemporary());
        });
    }

    ServletRequest createValidRequestWithSessionCompleted() throws UnknownHostException {
        ServletRequest servletRequest = new ServletRequest("localhost");
        servletRequest.setSession(new Session(1245L, null, StatusSession.COMPLETED));
        return servletRequest;
    }

    ServletRequest createNotAccessRequestWithSessionCompleted() throws UnknownHostException {
        ServletRequest servletRequest = new ServletRequest("google.com");
        servletRequest.setSession(new Session(1245L, null, StatusSession.COMPLETED));
        return servletRequest;
    }

    ServletRequest createValidRequestWithSessionTemporary() throws UnknownHostException {
        ServletRequest servletRequest = new ServletRequest("localhost");
        servletRequest.setSession(new Session(1245L, "payload", StatusSession.TEMPORARY));
        return servletRequest;
    }
    ServletRequest createNotValidPayloadRequestWithSessionTemporary() throws UnknownHostException {
        ServletRequest servletRequest = new ServletRequest("localhost");
        servletRequest.setSession(new Session(1245L, null, StatusSession.TEMPORARY));
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
