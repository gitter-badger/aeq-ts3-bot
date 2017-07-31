package de.esports.aeq.ts3bot.messages;

import de.esports.aeq.ts3bot.util.MathUtil;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Lukas on 24.07.2017.
 */
public class Messages {

    public static final String UNKNOWN_COMMAND = "unknown_command";

    public static final String BUNDLE_GENERAL_MESSAGES = "GeneralMessages";
    public static final String ERROR_INVALID_PERMISSIONS = "error_invalid_permissions";
    public static final String ERROR_COMMAND_EXCEPTION = "error_command_exception";
    public static final String ACCEPT_APPLICATION_ON_ERROR = "accept_application_on_error";
    public static final String ACCEPT_APPLICATION_ON_SUCCESS = "accept_application_on_error";
    public static final String WAITING_01 = "waiting_01";
    public static final String WAITING_02 = "waiting_01";
    public static final String WAITING_03 = "waiting_01";
    private static final String[] waitingMessages = {WAITING_01, WAITING_02, WAITING_03};

    public static Locale locale = Locale.ENGLISH;

    /**
     * Returns a translated string of the BUNDLE_GENERAL_MESSAGES according to the selected {@link Locale}
     *
     * @param message the message identifier
     * @param args    any additional arguments
     * @return the translated string
     */
    public static @Nullable String getTranslatedString(String message, Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES, locale);
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE_GENERAL_MESSAGES, Locale.ENGLISH);
            if (bundle == null) return null;
        }
        if (args != null) {
            return MessageFormat.format(bundle.getString(message), args);
        }
        return bundle.getString(message);
    }

    public static @Nullable String getRandomTranslatedMessageOfType(MessageType type, Object... args) {
        switch (type) {
            case WAITING:
                if (waitingMessages.length == 0) return null;
                String message = waitingMessages[MathUtil.getRandomIntBetween(0, waitingMessages.length - 1)];
                return getTranslatedString(message, args);
        }
        return "";
    }

}
