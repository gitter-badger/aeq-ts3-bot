package de.esports.aeq.ts3bot.command.exceptions;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CommandParsingException extends Exception {

    public CommandParsingException() {

    }

    public CommandParsingException(String message) {
        super(message);
    }

    public CommandParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParsingException(Throwable cause) {
        super(cause);
    }

    public CommandParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
