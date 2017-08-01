package de.esports.aeq.ts3bot.core;

public class AeqBotException extends RuntimeException {

    public AeqBotException() {

    }

    public AeqBotException(String message) {
        super(message);
    }

    public AeqBotException(String message, Throwable cause) {
        super(message, cause);
    }

    public AeqBotException(Throwable cause) {
        super(cause);
    }

    public AeqBotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
