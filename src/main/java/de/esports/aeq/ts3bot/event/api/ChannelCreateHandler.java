package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;

public abstract class ChannelCreateHandler extends TS3EventHandler<ChannelCreateEvent> {

    public ChannelCreateHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
