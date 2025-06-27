package org.example.chainofresponsibility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Объект отпровляемый клиентом.
 * @author  Nikita Bochkov
 */
public class ServletRequest {

    private final InetAddress ipAddress;
    private Session session;

    /**
     * @param hostName имя удаленного хоста
     */
    public ServletRequest(String hostName) throws UnknownHostException {
        this.ipAddress = InetAddress.getByName(hostName);
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = Objects.requireNonNull(session);
    }

}
