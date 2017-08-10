package de.esports.aeq.ts3bot.message;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The default {@link MessageFormatter} applies the following formatting rules:
 * <ul>
 * <li><b>"#"</b> is used for a new line (this will effectively split the message into multiple messages)</li>
 * <li><b>"${key}"</b> is used to access the message dependent event info</li>
 * </ul>
 *
 * @author Lukas Kannenberg
 * @see MessageFormatter
 * @since 0.1
 */
@Component
public class EventMessageFormatter implements MessageFormatter {

    private static final Logger log = LoggerFactory.getLogger(EventMessageFormatter.class);

    /**
     * Regex to capture key groups, int he format of "${key_name}".
     */
    private static final String VALUE_REGEX = "\\$\\{([a-z]|[0-9]|_)*}";

    /**
     * Regex to determine when a line break should happen.
     */
    private static final String SPLIT_REGEX = "#";

    /**
     * Message to be used when a key of a key group can not be mapped to an event key.
     */
    private static final String EMPTY = "?";

    private AeqTS3Bot ts3Bot;

    /**
     * Creates a new {@link MessageFormatter} that formats event based text messages before being forwarded to clients.
     *
     * @param ts3Bot the bot instance
     */
    @Autowired
    public EventMessageFormatter(@NotNull AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public String[] format(String message, BaseEvent event) {
        String replaced = message;
        Map<String, String> eventInfo = mergeEventInfo(event);
        if (eventInfo != null) {
            replaced = replaceKeys(message, eventInfo, EMPTY);
        }
        return replaced.split(SPLIT_REGEX);
    }

    /**
     * Takes a given message and replaces all keys with the appropriate event info.
     *
     * @param message   the message
     * @param eventInfo a map which contains the event information
     * @param empty     the replacement for a key that could not be found in the event map
     * @return a formatted string
     */
    private String replaceKeys(String message, @NotNull Map<String, String> eventInfo, String empty) {
        String formatted = message;
        Pattern pattern = Pattern.compile(VALUE_REGEX);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String key = extractKey(matcher.group());
            log.debug("match has been found: {}", key);
            String value = eventInfo.get(key);
            if (value != null) {
                formatted = formatted.replaceFirst(VALUE_REGEX, value);
                log.debug("successfully replaced key {} with value {}", key, value);
            } else {
                log.warn("match found but not able to find a value for key {}, using default value {}", key, EMPTY);
                formatted = formatted.replaceFirst(VALUE_REGEX, empty);
            }
        }
        return formatted;
    }

    /**
     * Extracts the given key from a group that matched the value regex.
     *
     * @param group the group which contains the key
     * @return the extracted key
     */
    private String extractKey(String group) {
        // should never happen due to the regex, but we still cover IndexOutOfBoundsException
        if (group.length() < 3) return group;
        return group.substring(2, group.length() - 1);
    }

    /**
     * Adds additional information to the event information.
     *
     * @param event the related event
     */
    private Map<String, String> mergeEventInfo(BaseEvent event) {
        Map<String, String> result = event.getMap();
        ClientInfo info = ts3Bot.getApi().getClientInfo(event.getInvokerId());
        if (info != null) {
            result.putAll(info.getMap());
        }
        return event.getMap();
    }
}
