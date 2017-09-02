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
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 04.08.2017
 */
public class PrivilegedHandler extends TS3EventAdapter {

    private Logger log = LoggerFactory.getLogger(PrivilegedHandler.class);

    private TS3Listener ts3Listener;
    private List<String> whitelistedIds = new ArrayList<>();

    public PrivilegedHandler(TS3Listener ts3Listener) {
        this.ts3Listener = ts3Listener;
        initWhitelist();
    }

    private void initWhitelist() {
        whitelistedIds.add("1lwtSCSAMm5D1wIbViYa7G0Ho2I=");
        whitelistedIds.add("h54PGmeQlaOaGabuYVAyfqlUCWI=");
        whitelistedIds.add("ZK9L/wcXbdxJGaVCu1o6sRJ9UPs=");
    }

    @Override
    public void onTextMessage(TextMessageEvent textMessageEvent) {
        log.debug("text message from {} requires permission check for handle, validating...", textMessageEvent
                .getInvokerName
                        ());
        if (isWhitelistedInvokerId(textMessageEvent.getInvokerUniqueId())) {
            log.debug("passed permission check, delegating message to default handler");
            ts3Listener.onTextMessage(textMessageEvent);
        } else {
            log.debug("permission check failed, client is not whitelisted");
        }
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        log.debug("client join {} | {} requires permissions check for handle, validating...", clientJoinEvent
                .getClientNickname(), clientJoinEvent.getUniqueClientIdentifier());
        if (isWhitelistedInvokerId(clientJoinEvent.getUniqueClientIdentifier())) {
            log.debug("passed permission check, delegating message to default handler");
            ts3Listener.onClientJoin(clientJoinEvent);
        } else {
            log.debug("permission check failed, client is not whitelisted");
        }
    }

    /**
     * Validates if a given client unique invoker id has been whitelisted.
     *
     * @param invokerId the client unique invoker id
     * @return true if the client is whitelisted, otherwise false
     */
    private boolean isWhitelistedInvokerId(String invokerId) {
        for (String s : whitelistedIds)
            if (invokerId.equals(s))
                return true;
        return false;
    }
}
