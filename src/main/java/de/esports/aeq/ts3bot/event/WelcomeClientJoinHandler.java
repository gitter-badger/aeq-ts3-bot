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
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import de.esports.aeq.ts3bot.core.ClientHelpers;
import de.esports.aeq.ts3bot.message.DefaultEventMessageProvider;
import de.esports.aeq.ts3bot.message.EventMessageFormatter;
import de.esports.aeq.ts3bot.message.Message;
import de.esports.aeq.ts3bot.message.Messages;
import de.esports.aeq.ts3bot.message.api.EventMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
        // TODO: check if client did not mute the bot
        String[] message = getWelcomeMessage(e);
        if (message != null)
            ClientHelpers.sendMessage(ts3Bot.getApi(), client.getId(), message);
    }

    /**
     * Returns the appropriate welcome message, depending on if the client is a new member or connected to the server
     * once before.
     *
     * @param event
     * @return
     */
    private String[] getWelcomeMessage(ClientJoinEvent event) {
        EventMessageProvider provider = context.getBean(DefaultEventMessageProvider.class);
        // TODO: we need a way to store the config with a message
        Message message = provider.getMessage(Messages.WELCOME, Messages.locale, event);
        if (message != null)
            return formatter.format(message.getMessage(), event);
        return new String[0];
    }


}
