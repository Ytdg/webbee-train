package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.Session;
import org.example.chainofresponsibility.StatusSession;

import java.util.Objects;
import java.util.logging.Logger;

public class SessionTimeOutHandler extends AbstractHandlerRequest {

    public SessionTimeOutHandler() {

    }

    private static final Logger logger = Logger.getLogger(SessionTimeOutHandler.class.getName());

    @Override
    public void handleServletRequest(ServletRequest servletRequest) {
        logger.info("handler:" + SessionTimeOutHandler.class.getName());
        Session session = servletRequest.getSession();
        if (session.statusSession().equals(StatusSession.TEMPORARY)) {
            System.out.println("Write to file payload:" + Objects.requireNonNull(session.payload()));
            return;
        }
        super.handleServletRequest(servletRequest);

    }

    @Override
    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        super.setNextHandler(servletRequestHandlerStep);

    }

}
