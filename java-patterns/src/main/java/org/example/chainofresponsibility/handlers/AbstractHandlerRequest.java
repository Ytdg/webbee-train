package org.example.chainofresponsibility.handlers;

import org.example.chainofresponsibility.ServletRequest;
import java.util.Objects;

/**
 * Handler
 * <p>Родитель для дочерних Handler. Работу паттерна можно сравнить с фильтрами</p>
 * */
public class AbstractHandlerRequest { //an abstract class is used to avoid code duplication

    private AbstractHandlerRequest requestHandlerStep;

    protected AbstractHandlerRequest() {

    }

    /**
     * Реальзует переход к следующему handler .
     * @param servletRequest объект, полученный от класса - насследника.
     */
    public void handleServletRequest(ServletRequest servletRequest) {

        if (Objects.nonNull(requestHandlerStep)) {
            requestHandlerStep.handleServletRequest(servletRequest);
        }

    }

    /**
     * Устанавливает новый обработчик .
     * @param servletRequestHandlerStep объект: класс - насследник (handler).
     */
    public void setNextHandler(AbstractHandlerRequest servletRequestHandlerStep) {

        this.requestHandlerStep = Objects.requireNonNull(servletRequestHandlerStep);

    }

}
