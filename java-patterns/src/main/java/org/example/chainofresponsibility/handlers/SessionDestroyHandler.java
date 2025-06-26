package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.StatusSession;

public class SessionDestroyHandler extends AbstractHandlerRequest {

    public SessionDestroyHandler() {

    }

    @Override
    public void handleServletRequest(ServletRequest servletRequest) {

        if (servletRequest.getSession().statusSession().equals(StatusSession.COMPLETED)) {
            System.out.println("Session destroy");
            return;
        }
        super.handleServletRequest(servletRequest);

    }

    @Override
    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        super.setNextHandler(servletRequestHandlerStep);

    }

}
