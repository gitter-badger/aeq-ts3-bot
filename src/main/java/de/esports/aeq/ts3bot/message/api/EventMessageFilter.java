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

package de.esports.aeq.ts3bot.message.api;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import de.esports.aeq.ts3bot.message.Message;

import java.util.List;

public interface EventMessageFilter extends MessageFilter {

    /**
     * Applies the given filter to a list of messages.
     *
     * @param filter   the filter to be applied
     * @param messages the {@link List} of messages
     * @param event    the event context
     * @return the filtered {@link List} of messages
     */
    static List<Message> filter(EventMessageFilter filter, List<Message> messages, BaseEvent event) {
        messages.removeIf(i -> filter.apply(i, event));
        return messages;
    }

    /**
     * Applies the given filters to a single message.
     *
     * @param filters the filters to be applied
     * @param message the {@link Message}
     * @param event   the event context
     * @return true if the message matches all the filters, otherwise false
     */
    static boolean filter(List<EventMessageFilter> filters, Message message, BaseEvent event) {
        for (EventMessageFilter filter : filters)
            if (filter.apply(message, event)) return false;
        return true;
    }

    /**
     * Applies the given filter the message.
     *
     * @param message the message
     * @param event   the event context
     * @return true if the message passed the filter, otherwise false
     */
    boolean apply(Message message, BaseEvent event);
}
