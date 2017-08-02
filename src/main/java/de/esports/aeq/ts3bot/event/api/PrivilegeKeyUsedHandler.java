package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;

public abstract class PrivilegeKeyUsedHandler extends TS3EventHandler<PrivilegeKeyUsedEvent> {

    public PrivilegeKeyUsedHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }
}
