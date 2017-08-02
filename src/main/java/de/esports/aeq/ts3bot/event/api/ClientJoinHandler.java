package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;

public abstract class ClientJoinHandler extends TS3EventHandler<ClientJoinEvent> {

    public ClientJoinHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
