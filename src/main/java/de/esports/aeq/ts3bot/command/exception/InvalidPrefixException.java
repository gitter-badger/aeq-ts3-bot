package de.esports.aeq.ts3bot.command.exception;

/**
 * Created by Lukas on 27.07.2017.
 */
public class InvalidPrefixException extends CommandParsingException {

    public InvalidPrefixException() {

    }

    public InvalidPrefixException(String message) {
        super(message);
    }

    public InvalidPrefixException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPrefixException(Throwable cause) {
        super(cause);
    }

    public InvalidPrefixException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
