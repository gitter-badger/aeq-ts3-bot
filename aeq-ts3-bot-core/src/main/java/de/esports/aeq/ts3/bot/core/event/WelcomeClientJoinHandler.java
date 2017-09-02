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
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.esports.aeq.ts3.bot.messages.DefaultMessageProvider;
import de.esports.aeq.ts3.bot.messages.EventMessageFormatter;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.MessageProvider;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.model.message.Message;
import de.esports.aeq.ts3.bot.util.ClientHelperBean;
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

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeClientJoinHandler.class);

    private TS3Bot ts3Bot;
    private ApplicationContext context;
    private EventMessageFormatter formatter;
    private ClientHelperBean clientHelperBean;

    @Autowired
    public WelcomeClientJoinHandler(TS3Bot ts3Bot, ApplicationContext context, ClientHelperBean clientHelperBean,
                                    EventMessageFormatter formatter) {
        this.ts3Bot = ts3Bot;
        this.context = context;
        this.clientHelperBean = clientHelperBean;
        this.formatter = formatter;
    }

    @Override
    public void onClientJoin(ClientJoinEvent event) {
        super.onClientJoin(event);
        Client client = ts3Bot.getTs3Api().getClientByUId(event.getUniqueClientIdentifier());

        if (clientHelperBean.isBotMuted(event.getUniqueClientIdentifier())) return;
        Message message = getWelcomeMessage(event);
        if (message != null) {
            String[] formattedMessage = formatter.format(message.getText(), event);
            clientHelperBean.sendMessage(client.getId(), formattedMessage);
        }
    }

    /**
     * Returns the appropriate welcome message, depending on if the client is a new member or connected to the server
     * once before.
     *
     * @param event the {@link ClientJoinEvent}
     * @return the appropriate welcome message or <code>null</code> if no {@link Message} was found
     */
    private Message getWelcomeMessage(ClientJoinEvent event) {
        MessageProvider provider = context.getBean(DefaultMessageProvider.class);
        return provider.getMessage(Messages.WELCOME, Messages.locale, event);
    }


}
