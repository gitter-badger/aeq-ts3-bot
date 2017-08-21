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

package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import de.esports.aeq.ts3bot.core.ClientHelpers;
import de.esports.aeq.ts3bot.message.DefaultMessageProvider;
import de.esports.aeq.ts3bot.message.EventMessageFormatter;
import de.esports.aeq.ts3bot.message.api.MessageProvider;
import de.esports.aeq.ts3bot.message.Messages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 06.08.2017
 */
@Component
@Scope("prototype")
public class WelcomeClientJoinHandler extends TS3EventAdapter {

    private static final Logger log = LoggerFactory.getLogger(WelcomeClientJoinHandler.class);

    private AeqTS3Bot ts3Bot;
    private ApplicationContext context;
    private EventMessageFormatter formatter;

    @Autowired
    public WelcomeClientJoinHandler(AeqTS3Bot ts3Bot, ApplicationContext context, EventMessageFormatter formatter) {
        this.ts3Bot = ts3Bot;
        this.context = context;
        this.formatter = formatter;
    }

    @Override
    public void onClientJoin(ClientJoinEvent e) {
        super.onClientJoin(e);
        Client client = ts3Bot.getApi().getClientByUId(e.getUniqueClientIdentifier());
        int amountConnected = 0; // TODO(glains): get connection amount from database
        WelcomeClientJoinConfig config = null; // TODO(glains): get config from database
        if (!WelcomeClientJoinConfig.isWithinRange(config, amountConnected)) {
            return;
        }
        // TODO: check if client did not mute the bot
        ClientHelpers.sendMessage(ts3Bot, client.getId(), getWelcomeMessage(e, amountConnected));
    }

    /**
     * Returns the appropriate welcome message, depending on if the client is a new member or connected to the server
     * once before.
     *
     * @param event
     * @return
     */
    private String[] getWelcomeMessage(ClientJoinEvent event, int amountConnected) {
        Date lastOnline = getLastTimeOnline(event);
        MessageProvider provider = context.getBean(DefaultMessageProvider.class);
        // TODO: we need a way to store the config with a message
        String message = provider.getMessage(Messages.WELCOME, Messages.locale);
        return formatter.format(message, event);
    }

    /**
     * @param e
     * @return
     */
    private @Nullable Date getLastTimeOnline(@NotNull ClientJoinEvent e) {
        ClientInfo info = ts3Bot.getApi().getClientByUId(e.getUniqueClientIdentifier());
        if (info != null) {
            return info.getLastConnectedDate();
        }
        return null;
    }
}
