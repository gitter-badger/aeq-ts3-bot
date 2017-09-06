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

import de.aeq.esports.ts3.bot.advertiser.api.Advertiser;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.MessagingException;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @author Lukas Kannenberg
 */
@Component
public class AdvertiserBean implements Advertiser {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertiserBean.class);

    /**
     * Timeout used for clients to not receive any further advertisements.
     */
    private static final int CLIENT_TIMEOUT_MILLIS = 60 * 30;

    private Messaging messaging;

    /**
     * Keep track of the client ids to prevent sending multiple messages in a short time frame.
     */
    private HashMap<Integer, Timestamp> clientTimeouts = new HashMap<>();

    @Autowired
    public AdvertiserBean(Messaging messaging) {
        this.messaging = messaging;
    }

    @Override
    public void advertiseMembership(int clientId) {
        // don`t advertise clients who have been recently messaged
        if (hasTimeout(clientId)) {
            return;
        }
        try {
            messaging.fetchAndSendMessage(clientId, Messages.ADVERTISE_MEMBERSHIP, null);
            // reset timeout if successful
            clientTimeouts.put(clientId, new Timestamp(System.currentTimeMillis() + CLIENT_TIMEOUT_MILLIS));
        } catch (MessagingException e) {
            LOG.error("cannot send fetch or send message to client " + clientId, e);
        }
    }

    private boolean hasTimeout(int clientId) {
        Timestamp timestamp = clientTimeouts.get(clientId);
        return timestamp == null || new Timestamp(System.currentTimeMillis() - CLIENT_TIMEOUT_MILLIS).compareTo
                (timestamp) >= 0;
    }
}
