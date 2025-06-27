package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;

import java.util.List;
import java.util.logging.Logger;

public class RemoteAddressHandler extends AbstractHandlerRequest {

    private static final List<String> BLOCKED_HOST_NAMES = List.of("test.com", "google.com", "smtp.gmail.com");

    private static final Logger logger = Logger.getLogger(RemoteAddressHandler.class.getName());

    public RemoteAddressHandler() {

    }

    @Override
    public void handleServletRequest(ServletRequest servletRequest) {
        logger.info("handler:" + RemoteAddressHandler.class.getName());
        if (!BLOCKED_HOST_NAMES.contains(servletRequest.getIpAddress().getHostName())) {
            System.out.println("Request allowed");
            super.handleServletRequest(servletRequest);
            return;
        }
        System.out.println("Request not allowed");

    }

    @Override
    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        super.setNextHandler(servletRequestHandlerStep);

    }

}
