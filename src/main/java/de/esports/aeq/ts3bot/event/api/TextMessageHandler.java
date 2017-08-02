package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

/**
 * Handles a given message that was send by a client.
 *
 * @author Lukas Kannenberg
 * @since 01.08.2017
 */
public abstract class TextMessageHandler extends TS3EventHandler<TextMessageEvent> {

    public TextMessageHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
