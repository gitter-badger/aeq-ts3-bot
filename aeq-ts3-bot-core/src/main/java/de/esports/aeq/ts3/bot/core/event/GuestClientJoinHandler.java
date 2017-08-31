/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.core.event;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3.bot.core.ClientHelpers;
import de.esports.aeq.ts3.bot.core.ServerGroups;
import de.esports.aeq.ts3.bot.model.TS3Bot;
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

    private TS3Bot ts3Bot;
    private GuestsClientJoinHandlerConfiguration configuration;

    @Autowired
    public GuestClientJoinHandler(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        super.onClientJoin(e);
        // Check if the user is a guest
        ClientInfo client = ts3Bot.getTs3Api().getClientByUId(e.getInvokerUniqueId());
        if (ClientHelpers.clientContainsServerGroup(client, ServerGroups.GUEST)) {
            if (configuration == null)
                configuration = new GuestsClientJoinHandlerConfiguration();
            if (!shouldSendMessage(e)) return;
            String message = ""; // TODO(glains): get the actual message
            if (!ts3Bot.getTs3Api().sendPrivateMessage(e.getClientId(), message)) {
                log.debug("Send message to client: {}, {}", client.getId(), message);
            }
            log.warn("Could not send message to client: {}", client.getId());
        }
    }

    private boolean shouldSendMessage(ClientJoinEvent event) {
        ClientInfo client = ts3Bot.getTs3Api().getClientByUId(event.getInvokerUniqueId());
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
