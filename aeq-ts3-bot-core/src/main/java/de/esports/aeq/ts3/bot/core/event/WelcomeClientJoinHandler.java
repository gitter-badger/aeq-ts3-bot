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
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.MessagingException;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Messaging messaging;

    @Autowired
    public WelcomeClientJoinHandler(Messaging messaging) {
        this.messaging = messaging;
    }

    @Override
    public void onClientJoin(ClientJoinEvent event) {
        try {
            messaging.fetchAndSendMessage(event.getClientId(), Messages.WELCOME, event.getMap());
        } catch (MessagingException e) {
            LOG.error("unable to send message to client " + event.getClientId(), e);
        }
    }

}
