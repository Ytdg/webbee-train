package org.example.builder;

public class Cookie {

    private final boolean authenticated; // required
    private final String activePage;
    private final long idSession;
    private final String language;
    private final String domain;

    public long getIdSession() {
        return idSession;
    }

    public String getActivePage() {
        return activePage;
    }

    public String getDomain() {
        return domain;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Cookie(Builder builder) {
        this.domain = builder.domain;
        this.idSession = builder.idSession;
        this.language = builder.language;
        this.authenticated = builder.authenticated;
        this.activePage = builder.activePage;
    }

    @Override
    public String toString() {
        return "Authenticated:" + authenticated
                + "\nActive Page:" + activePage
                + "\nId session:" + idSession
                + "\nLanguage:" + language +
                "\nDomain" + domain;
    }

    public static class Builder {

        private final boolean authenticated; // required
        private String activePage = "none";
        private long idSession = -1L;
        private String language = "none";
        private String domain = "none";

        public Builder(boolean authenticated) {
            this.authenticated = authenticated;
        }

        public Builder activePage(String activePage) {
            this.activePage = activePage;
            return this;
        }

        public Builder idSession(long idSession) {
            this.idSession = idSession;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

    }

}
