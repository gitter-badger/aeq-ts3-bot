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

package de.esports.aeq.ts3.bot.messages;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3.bot.dataprovider.api.MessageRepository;
import de.esports.aeq.ts3.bot.messages.api.MessageFormatter;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.model.message.Message;
import de.esports.aeq.ts3.bot.model.message.MessageFilter;
import de.esports.aeq.ts3.bot.util.NotImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Provides a common implementation of the {@link Messaging} interface.
 * <p>
 * This implementation uses the primary {@link MessageFormatter} for formatting.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 05.09.2017
 */
@Component
@Primary
@NotImplemented
public class MessagingBean implements Messaging {

    /**
     * Utility method to merge an array of {@link Map} objects.
     *
     * @param maps an array of maps
     * @param <K>  the key type the of the array
     * @param <V>  the value type of the array
     * @return the merged {@link Map} or an empty {@link Map} if the initial array is <code>null</code> or empty
     */
    private static <K, V> Map<K, V> mergeMaps(Map<K, V>[] maps) {
        if (maps == null || maps.length == 0) {
            return Collections.emptyMap();
        } else if (maps.length == 1) {
            return maps[0];
        } else {
            Map<K, V> result = maps[0];
            for (int i = 1; i < maps.length; i++) {
                result.putAll(maps[i]);
            }
            return result;
        }
    }

    private TS3Bot ts3Bot;
    private MessageRepository repository;
    private MessageFormatter formatter;

    @Autowired
    public MessagingBean(TS3Bot ts3Bot, MessageRepository repository, MessageFormatter formatter) {
        this.ts3Bot = ts3Bot;
        this.repository = repository;
        this.formatter = formatter;
    }

    @Override
    public void sendMessage(int clientId, Message message) throws MessagingException {
        sendMessage(clientId, message, null);
    }

    @Override
    public final void sendMessage(int clientId, Message message, Map<String, String>... properties) throws
            MessagingException {
        Map<String, String> mergedMap = null;
        if (properties != null) {
            mergedMap = mergeMaps(properties);
        }
        String[] output = formatter.format(message.getText(), mergedMap);
        sendMessage(clientId, output);
    }

    @Override
    public void fetchAndSendMessage(int clientId, String context) throws MessagingException {
        fetchAndSendMessage(clientId, context, null);
    }

    @Override
    public void fetchAndSendMessage(int clientId, String context, Map<String, String>... properties) throws
            MessagingException {
        List<Message> messages = repository.findByContextAndLocale(context, Messages.DEFAULT_LOCALE);
        if (messages != null && !messages.isEmpty()) {
            // remove messages that do not match their filters
            messages = applyMessageFilters(messages, clientId);
            Message message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
            sendMessage(clientId, message, properties);
        } else {
            throw new MessagingException("unable to find a matching message for context " + context + " and default " +
                    "locale");
        }
    }

    /**
     * Sends a formatted message to the client with the specified id.
     *
     * @param clientId         the id of the client
     * @param formattedMessage the formatted message
     * @throws MessagingException if the message can not be send
     */
    private void sendMessage(int clientId, String[] formattedMessage) throws MessagingException {
        for (String message : formattedMessage) {
            if (!ts3Bot.getTs3Api().sendPrivateMessage(clientId, message)) {
                throw new MessagingException("unable to send message to client " + clientId);
            }
        }
    }

    /**
     * Filters a given {@link List} of {@link Message} objects.
     * <p>
     * The process involves applying each filter of a message to the message. This is repeated for all messages in
     * the list.
     *
     * @param messages the {@link List} of {@link Message} objects to be filted
     * @param clientId the id of the client, used to apply {@link MessageFilter} objects
     * @return the filtered {@link List} of {@link Message} objects
     * @throws MessagingException if the client information cannot be found
     */
    private List<Message> applyMessageFilters(List<Message> messages, int clientId) throws MessagingException {
        ClientInfo info = ts3Bot.getTs3Api().getClientInfo(clientId);
        if (info == null) {
            throw new MessagingException("cannot apply filters as the client information is missing");
        }
        return messages.stream().filter(message -> {
            return message.getFilters().stream().allMatch(filter -> filter.apply(message, info));
        }).collect(Collectors.toList());
    }

}
