package de.esports.aeq.ts3bot.message;

import de.esports.aeq.ts3bot.util.MathUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Lukas on 24.07.2017.
 */
public class Messages {

    public static final String WAITING_02 = "waiting_02";
    public static final String WAITING_03 = "waiting_03";

    public static final String BUNDLE_GENERAL_MESSAGES = "general";

    public static final String UNKNOWN_COMMAND = "unknown_command";
    public static final String ERROR_NOT_IMPLEMENTED = "error_not_implemented";
    public static final String ERROR_INVALID_PERMISSIONS = "error_invalid_permissions";
    public static final String ERROR_COMMAND_EXCEPTION = "error_command_exception";
    public static final String ACCEPT_APPLICATION_ON_ERROR = "accept_application_on_error";
    public static final String ACCEPT_APPLICATION_ON_SUCCESS = "accept_application_on_error";

    public static final String WAITING_01 = "waiting_01";
    public static final String WELCOME_01 = "welcome_01";
    public static final String WELCOME_02 = "welcome_02";
    private static final String[] waitingMessages = {WAITING_01, WAITING_02, WAITING_03};
    public static final String WELCOME_03 = "welcome_03";
    public static final String WELCOME_NEW_01 = "welcome_new_01";
    public static final String WELCOME_NEW_02 = "welcome_new_02";
    public static final String WELCOME_NEW_03 = "welcome_new_03";
    private static final Logger log = LoggerFactory.getLogger(Messages.class);
    private static final String DELIMITER = "\\$\\$";

    public static Locale locale = Locale.GERMAN;

    /**
     * Returns a translated string of the BUNDLE_GENERAL_MESSAGES according to the selected {@link Locale}
     *
     * @param message the message identifier
     * @param args    any additional arguments
     * @return the translated string
     */
    public static @NotNull String[] getTranslatedString(String message, Object... args) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES, locale);
            if (bundle.containsKey(message)) {
                return formatString(bundle, message, args);
            } else {
                log.debug("default message bundle does not contain key {}", message);
            }
        } catch (MissingResourceException e) {
            log.warn("could not find localized message bundle: {}", e);
        }
        log.debug("attempting to load default message bundle");
        try {
            bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES);
            return formatString(bundle, message, args);
        } catch (MissingResourceException e) {
            log.error("could not load default message bundle", e);
            return new String[0];
        }
    }

    private static @NotNull String[] formatString(ResourceBundle bundle, String message, Object[] args) {
        if (args != null) {
            String temp = MessageFormat.format(bundle.getString(message), args);
            return temp.split(DELIMITER);
        }
        return bundle.getString(message).split(DELIMITER);
    }

    public static @Nullable String[] getRandomTranslatedMessageOfType(MessageType type, Object... args) {
        switch (type) {
            case WAITING:
                if (waitingMessages.length == 0) return null;
                String message = waitingMessages[MathUtil.getRandomIntBetween(0, waitingMessages.length - 1)];
                return getTranslatedString(message, args);
        }
        return new String[0];
    }

}
