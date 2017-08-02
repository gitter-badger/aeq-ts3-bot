package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.CommandFuture;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3bot.core.ClientHelpers;
import de.esports.aeq.ts3bot.core.ServerGroups;
import de.esports.aeq.ts3bot.event.api.ClientJoinHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class GuestsClientJoinHandler extends ClientJoinHandler {

    private static final Logger log = LoggerFactory.getLogger(GuestsClientJoinHandler.class);

    private GuestsClientJoinHandlerConfiguration configuration;

    public GuestsClientJoinHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }

    public GuestsClientJoinHandler(TS3Api api, TS3ApiAsync apiAsync, GuestsClientJoinHandlerConfiguration
            configuration) {
        super(api, apiAsync);
        this.configuration = configuration;
    }

    @Override
    public void handle(ClientJoinEvent event) {
        // Check if the user is a guest
        ClientInfo client = api.getClientByUId(event.getInvokerUniqueId());
        if (ClientHelpers.clientContainsServerGroup(client, ServerGroups.GUEST)) {
            if (configuration == null)
                configuration = new GuestsClientJoinHandlerConfiguration();
            if (!shouldSendMessage(event)) return;
            String message = ""; // TODO(glains): get the actual message
            CommandFuture<Boolean> result = apiAsync.sendPrivateMessage(event.getClientId(), message);
            result.onSuccess(e -> log.debug("Send message to client: {}, {}", client.getId(), message));
            result.onFailure(e -> log.warn("Could not send message to client: {}", client.getId()));
        }
    }

    private boolean shouldSendMessage(ClientJoinEvent event) {
        ClientInfo client = api.getClientByUId(event.getInvokerUniqueId());
        int repeatAmount = configuration.getRepeatAmount();
        if (repeatAmount > 0) {
            // TODO(glains): get the actual value from the database
            int current = 0;
            if (current > configuration.getRepeatAmount()) return false;
        }
        Date now = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(client.getLastConnectedDate());
        calendar.add(Calendar.SECOND, configuration.getRepeatTimeout());
        if (!now.after(calendar.getTime()))
            return false;
        return true;
    }
}
