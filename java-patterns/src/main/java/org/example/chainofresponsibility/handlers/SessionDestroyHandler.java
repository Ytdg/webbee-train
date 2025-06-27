package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import org.example.chainofresponsibility.StatusSession;

import java.util.logging.Logger;

/**
 * ConcreteHandler
 * <p>Обработчик завершенной сессии</p>
 * */
public class SessionDestroyHandler extends AbstractHandlerRequest {

    public SessionDestroyHandler() {

    }

    private static final Logger LOGGER = Logger.getLogger(SessionDestroyHandler.class.getName());

    /**
     * "Очищает сессию", если статус сессии равен COMPLETED,
     */
    @Override
    public void handleServletRequest(ServletRequest servletRequest) {
        LOGGER.info("handler:" + SessionDestroyHandler.class.getName());
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
