package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.ChannelMovedEvent;

public abstract class ChannelMovedHandler extends TS3EventHandler<ChannelMovedEvent> {

    public ChannelMovedHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
