package de.esports.aeq.ts3bot.config;

public class ConfigurationBuildException extends Exception {

    public ConfigurationBuildException() {
    }

    public ConfigurationBuildException(String message) {
        super(message);
    }

    public ConfigurationBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationBuildException(Throwable cause) {
        super(cause);
    }

    public ConfigurationBuildException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
