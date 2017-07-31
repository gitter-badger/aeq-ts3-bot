package de.esports.aeq.ts3bot.command.exception;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CHandleException extends Exception {

    public CHandleException() {
    }

    public CHandleException(String message) {
        super(message);
    }

    public CHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CHandleException(Throwable cause) {
        super(cause);
    }

    public CHandleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
