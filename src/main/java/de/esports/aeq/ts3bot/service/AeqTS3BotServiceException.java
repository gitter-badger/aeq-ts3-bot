package de.esports.aeq.ts3bot.service;

/**
 * Exception thrown for any errors that occurred while communicating with any aeq-service or the database.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public class AeqTS3BotServiceException extends Exception {

    public AeqTS3BotServiceException() {
        super();
    }

    public AeqTS3BotServiceException(String message) {
        super(message);
    }

    public AeqTS3BotServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AeqTS3BotServiceException(Throwable cause) {
        super(cause);
    }

    protected AeqTS3BotServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
