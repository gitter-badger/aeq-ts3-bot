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

package de.aeq.esports.ts3.bot.advertiser;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.aeq.esports.ts3.bot.advertiser.api.Advertiser;
import de.esports.aeq.ts3.bot.messages.DefaultMessageProvider;
import de.esports.aeq.ts3.bot.messages.EventMessageFormatter;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.MessageFormatter;
import de.esports.aeq.ts3.bot.messages.api.MessageProvider;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.model.message.Message;
import de.esports.aeq.ts3.bot.util.ClientHelperBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @author Lukas Kannenberg
 */
@Component
public class AdvertiserBean implements Advertiser {

    /**
     * Timeout used for clients to not receive any further advertisements.
     */
    private static final int CLIENT_TIMEOUT_MILLIS = 60 * 30;

    private ApplicationContext context;
    private TS3Bot ts3Bot;

    /**
     * Keep track of the client ids to prevent sending multiple messages in a short time frame.
     */
    private HashMap<Integer, Timestamp> clientTimeouts = new HashMap<>();

    @Autowired
    public AdvertiserBean(ApplicationContext context, TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void advertiseMembership(int clientId) {
        // don`t advertise clients who have been recently messaged
        if (hasTimeout(clientId)) {
            return;
        }
        MessageProvider provider = context.getBean(DefaultMessageProvider.class);
        Message message = provider.getMessage(Messages.ADVERTISE_MEMBERSHIP, Messages.DEFAULT_LOCALE, null);
        if (message != null) {
            MessageFormatter formatter = context.getBean(EventMessageFormatter.class);
            ClientHelperBean helper = context.getBean(ClientHelperBean.class);
            ClientInfo clientInfo = ts3Bot.getTs3Api().getClientInfo(clientId);
            helper.sendMessage(clientId, formatter.format(message.getText(), clientInfo.getMap()));
            // reset timeout
            clientTimeouts.put(clientId, new Timestamp(System.currentTimeMillis() + CLIENT_TIMEOUT_MILLIS));
        }
    }

    private boolean hasTimeout(int clientId) {
        Timestamp timestamp = clientTimeouts.get(clientId);
        return timestamp == null || new Timestamp(System.currentTimeMillis() - CLIENT_TIMEOUT_MILLIS).compareTo
                (timestamp) >= 0;
    }
}
