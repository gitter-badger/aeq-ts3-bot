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

package de.esports.aeq.ts3.bot.util;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3.bot.dataprovider.api.UserRepository;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.model.User;
import de.esports.aeq.ts3.bot.model.UserBotConfiguration;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
public class ClientHelperBean {

    private static final Logger LOG = LoggerFactory.getLogger(ClientHelperBean.class);

    public static boolean clientContainsServerGroup(ClientInfo client, int serverGroupId) {
        for (int i : client.getServerGroups())
            if (i == serverGroupId) return true;
        return false;
    }

    private ApplicationContext context;
    private TS3Bot ts3Bot;

    @Autowired
    public ClientHelperBean(TS3Bot ts3Bot, ApplicationContext context) {
        this.ts3Bot = ts3Bot;
        this.context = context;
    }

    /**
     * Returns that last time the invoker of the specified event was online.
     * <p>
     * If the invoker has never been online before <code>null</code> is returned.
     *
     * @param event the {@link BaseEvent}
     * @return the last time online or <code>null</code> is the invoker has never been online before
     */
    @Nullable
    private Date getLastTimeOnline(BaseEvent event) {
        ClientInfo info = ts3Bot.getTs3Api().getClientByUId(event.getInvokerUniqueId());
        if (info != null) {
            return info.getLastConnectedDate();
        }
        return null;
    }

    public boolean isBotMuted(String clientUId) {
        UserRepository repository = context.getBean(UserRepository.class);
        User user = repository.getFirstByTs3Id(clientUId);
        UserBotConfiguration configuration = user.getConfiguration();
        return configuration != null && configuration.isBotMuted();
    }

    public void sendMessage(int clientId, String[] messages) {
        for (String s : messages) {
            LOG.debug("sending private message to client {}: \"{}\"", clientId, s);
            ts3Bot.getTs3Api().sendPrivateMessage(clientId, s);
        }
    }
}
