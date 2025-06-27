package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;

import java.util.List;
import java.util.logging.Logger;

/**
 * ConcreteHandler
 * <p>Роль фильтра. Работает с удаленным адресом.</p>
 * @author  Nikita Bochkov
 * */
public class RemoteAddressHandler extends AbstractHandlerRequest {

    private static final List<String> BLOCKED_HOST_NAMES = List.of("test.com", "google.com", "smtp.gmail.com"); //field it's static, because can be more 1 instance

    private static final Logger LOGGER = Logger.getLogger(RemoteAddressHandler.class.getName());

    public RemoteAddressHandler() {

    }

    /**
     * Если request имеет разрешенный hostName, запрос передается следующему обработчику.
     */
    @Override
    public void handleServletRequest(ServletRequest servletRequest) {
        LOGGER.info("handler:" + RemoteAddressHandler.class.getName());
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
