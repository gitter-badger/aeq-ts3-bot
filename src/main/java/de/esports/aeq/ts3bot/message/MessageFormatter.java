package de.esports.aeq.ts3bot.message;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;

/**
 * This interface is intended to be implemented for any class that defines the behaviour of how a message should be
 * formatted. Each formatter can apply additional formatting rules.
 *
 * @author Lukas Kannenberg
 * @since 0.1
 */
public interface MessageFormatter {

    /**
     * Formats the given message.
     * <p>
     * The exact formatting rules are specified in the implementation.
     *
     * @param message the message to be formatted
     * @param event   the related event
     * @return the formatted message
     */
    String[] format(String message, BaseEvent event);
}
