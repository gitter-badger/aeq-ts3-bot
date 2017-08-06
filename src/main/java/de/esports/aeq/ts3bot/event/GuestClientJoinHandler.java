package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import de.esports.aeq.ts3bot.core.ClientHelpers;
import de.esports.aeq.ts3bot.core.ServerGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Handles a given message that was send by a client.
 *
 * @author Lukas Kannenberg
 * @since 01.08.2017
 */
@Component
@Scope("prototype")
public class GuestClientJoinHandler extends TS3EventAdapter {

    private static final Logger log = LoggerFactory.getLogger(GuestClientJoinHandler.class);

    private AeqTS3Bot ts3Bot;
    private GuestsClientJoinHandlerConfiguration configuration;

    @Autowired
    public GuestClientJoinHandler(AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        super.onClientJoin(e);
        // Check if the user is a guest
        ClientInfo client = ts3Bot.getApi().getClientByUId(e.getInvokerUniqueId());
        if (ClientHelpers.clientContainsServerGroup(client, ServerGroups.GUEST)) {
            if (configuration == null)
                configuration = new GuestsClientJoinHandlerConfiguration();
            if (!shouldSendMessage(e)) return;
            String message = ""; // TODO(glains): get the actual message
            if (!ts3Bot.getApi().sendPrivateMessage(e.getClientId(), message)) {
                log.debug("Send message to client: {}, {}", client.getId(), message);
            }
            log.warn("Could not send message to client: {}", client.getId());
        }
    }

    private boolean shouldSendMessage(ClientJoinEvent event) {
        ClientInfo client = ts3Bot.getApi().getClientByUId(event.getInvokerUniqueId());
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
        return now.after(calendar.getTime());
    }
}
