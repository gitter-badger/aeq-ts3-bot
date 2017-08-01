package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Handles a given message that was send by a client.
 *
 * @author Lukas Kannenberg
 * @since 01.08.2017
 */
@FunctionalInterface
public interface TextMessageHandler {

    /**
     * Handles a text message event.
     *
     * @param messageEvent the {@link TextMessageEvent}
     */
    void handleTextMessage(@NotNull TextMessageEvent messageEvent);
}
