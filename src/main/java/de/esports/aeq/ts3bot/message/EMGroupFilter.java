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

package de.esports.aeq.ts3bot.message;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import de.esports.aeq.ts3bot.message.api.EventMessageFilter;
import de.esports.aeq.ts3bot.message.api.MessageFilter;

import java.util.stream.IntStream;

/**
 * A {@link MessageFilter} that is able to filter specific messages depending on the server groups of the invoker.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 21.08.2017
 */
public class EMGroupFilter implements EventMessageFilter {

    /**
     * Applies the white- and blacklists to the given server groups and returns if they passed the filter.
     * <p>
     * Passing a null reference for either the white- or blacklist will ignore it.
     *
     * @param serverGroups the server groups
     * @param whitelist    the whitelisted server groups
     * @param blacklist    the blacklisted server groups
     * @return true if the server groups passed the filter, otherwise false
     */
    private static boolean isValidServerGroup(int[] serverGroups, int[] whitelist, int[] blacklist) {
        if (serverGroups == null) return false;
        if (whitelist != null) {
            boolean whitelisted = false;
            for (int i : serverGroups) {
                boolean contains = IntStream.of(whitelist).anyMatch(p -> p == i);
                if (!contains) return false;
            }
        }
        if (blacklist != null)
            for (int i : blacklist) {
                boolean contains = IntStream.of(serverGroups).anyMatch(p -> p == i);
                if (contains) return false;
            }
        return true;
    }

    private final AeqTS3Bot ts3Bot;
    private int[] whitelist;
    private int[] blacklist;

    /**
     * Creates a new {@link EventMessageFilter} that is able to filter specific server groups with a white- and
     * blacklist.
     *
     * @param ts3Bot    the {@link AeqTS3Bot} instance
     * @param whitelist a whitelist, one of the server groups must be owned to pass the filter
     * @param blacklist a blacklist, none of the server groups must be owned to pass the filter
     */
    public EMGroupFilter(AeqTS3Bot ts3Bot, int[] whitelist, int[] blacklist) {
        this.ts3Bot = ts3Bot;
        this.whitelist = whitelist;
        this.blacklist = blacklist;
    }

    @Override
    public boolean apply(Message message, BaseEvent event) {
        ClientInfo info = ts3Bot.getApi().getClientInfo(event.getInvokerId());
        return isValidServerGroup(info.getServerGroups(), whitelist, blacklist);
    }
}
