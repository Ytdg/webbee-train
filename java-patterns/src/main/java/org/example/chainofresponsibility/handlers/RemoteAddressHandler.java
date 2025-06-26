package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import java.util.List;

public class RemoteAddressHandler extends AbstractHandlerRequest {

    private static final List<String> BLOCKED_HOST_NAMES = List.of("test.com", "google.com", "smtp.gmail.com");

    public RemoteAddressHandler() {

    }

    @Override
    public void handleServletRequest(ServletRequest servletRequest) {

        if (!BLOCKED_HOST_NAMES.contains(servletRequest.getIpAddress().getHostAddress())) {
            System.out.println("Request allowed");
            super.handleServletRequest(servletRequest);
        }

    }

    @Override
    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        super.setNextHandler(servletRequestHandlerStep);

    }

}
