package de.esports.aeq.ts3bot.message;

public class Message {

    private String context;

    private String id;

    private String locale;

    private String message;

    public Message(String context, String id, String locale, String message) {
        this.context = context;
        this.id = id;
        this.locale = locale;
        this.message = message;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
