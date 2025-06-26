package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.Session;
import org.example.chainofresponsibility.StatusSession;
import java.util.Objects;

public class SessionTimeOutHandler extends AbstractHandlerRequest {

    public SessionTimeOutHandler() {

    }

    @Override
    public void handleServletRequest(ServletRequest servletRequest) {

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
