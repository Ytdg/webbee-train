package org.example.builder;

/**
 * Объект, создаваемый Builder
 * @author  Nikita Bochkov
 * */
public final class Cookie {

    private final boolean authenticated; // required
    private final String activePage;
    private final long idSession;
    private final String language;
    private final String domain;

    private Cookie(Builder builder) {
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
                "\nDomain:" + domain;
    }

    /**
     * Builder паттерн можно сравнить с созданием Response (по цепочке устанавливать property).
     * */
    public static class Builder {

        private final boolean authenticated; // required
        private String activePage;
        private long idSession;
        private String language;
        private String domain;

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

        /**
         * метод заканчивать цепочку установки property
         */
        public Cookie build() {
            return new Cookie(this);
        }

    }

}
