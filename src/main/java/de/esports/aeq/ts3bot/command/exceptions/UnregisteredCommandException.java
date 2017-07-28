package de.esports.aeq.ts3bot.command.exceptions;

/**
 * Created by Lukas on 27.07.2017.
 */
public class UnregisteredCommandException extends CommandParsingException {

    private String prefix;

    public UnregisteredCommandException() {

    }

    public UnregisteredCommandException(String prefix) {
        super(generateMessage(prefix));
        this.prefix = prefix;
    }

    public UnregisteredCommandException(String prefix, Throwable cause) {
        super(generateMessage(prefix), cause);
    }

    public UnregisteredCommandException(Throwable cause) {
        super(cause);
    }

    public UnregisteredCommandException(String prefix, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(generateMessage(prefix), cause, enableSuppression, writableStackTrace);
    }

    private static String generateMessage(String prefix) {
        return "unrecognized prefix: " + prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


}
