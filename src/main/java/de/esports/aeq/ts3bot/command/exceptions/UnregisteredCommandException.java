package de.esports.aeq.ts3bot.command.exceptions;

/**
 * Created by Lukas on 27.07.2017.
 */
public class UnregisteredCommandException extends CommandParsingException {

    public UnregisteredCommandException() {
    }

    public UnregisteredCommandException(String message) {
        super(message);
    }

    public UnregisteredCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnregisteredCommandException(Throwable cause) {
        super(cause);
    }

    public UnregisteredCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
