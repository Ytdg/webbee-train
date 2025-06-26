package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import java.util.Objects;

public class AbstractHandlerRequest {

    private AbstractHandlerRequest requestHandlerStep;

    protected AbstractHandlerRequest() {

    }

    public void handleServletRequest(ServletRequest servletRequest) {

        if (Objects.nonNull(requestHandlerStep)) {
            requestHandlerStep.handleServletRequest(servletRequest);
        }

    }

    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        this.requestHandlerStep = Objects.requireNonNull(servletRequestHandlerStep);

    }

}
