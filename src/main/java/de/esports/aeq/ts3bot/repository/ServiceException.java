package de.esports.aeq.ts3bot.repository;

/**
 * Exception thrown for any errors that occurred while communicating with any aeq-repository or the database.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
