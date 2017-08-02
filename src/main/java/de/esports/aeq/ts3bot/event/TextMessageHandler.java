package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

/**
 * Handles a given message that was send by a client.
 *
 * @author Lukas Kannenberg
 * @since 01.08.2017
 */
public interface TextMessageHandler extends EventHandler<TextMessageEvent> {

}
