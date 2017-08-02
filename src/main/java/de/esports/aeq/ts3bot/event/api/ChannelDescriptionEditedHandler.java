package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.ChannelDescriptionEditedEvent;

public abstract class ChannelDescriptionEditedHandler extends TS3EventHandler<ChannelDescriptionEditedEvent> {

    public ChannelDescriptionEditedHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
