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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Provides a common implementation of the {@link Messaging} interface.
 * <p>
 * This implementation uses the primary {@link MessageFormatter} for formatting.
 *
 * @author Lukas Kannenberg
 * @version 1.1
 * @since 05.09.2017
 */
@Component
@Primary
public class MessagingBean implements Messaging {

    private static final Logger LOG = LoggerFactory.getLogger(MessagingBean.class);

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
    public @Nullable Message getMessage(@NotNull String context, @NotNull Locale locale) throws MessagingException {
        List<Message> messages = getMessages(context, locale);
        if (messages.isEmpty()) return null;
        return getOne(messages);
    }

    @Override
    public @NotNull List<Message> getMessages(@NotNull String context, @NotNull Locale locale) throws
            MessagingException {
        Objects.requireNonNull(context, "context must be null");
        Objects.requireNonNull(locale, "locale must be null");
        return repository.findByContextAndLocale(context, locale);
    }

    @Override
    public @NotNull List<Message> filterMessages(@NotNull List<Message> messages, int clientId) throws
            MessagingException {
        Objects.requireNonNull(messages, "messages must be null");
        ClientInfo info = ts3Bot.getTs3Api().getClientInfo(clientId);
        if (info == null) {
            throw new MessagingException("cannot apply filters as the client information is missing");
        }
        return messages.stream().filter(message ->
                message.getFilters().stream().allMatch(filter -> filter.apply(message, info))
        ).collect(Collectors.toList());
    }

    @Override
    public void sendMessage(int clientId, @NotNull Message message) throws MessagingException {
        sendMessage(clientId, message, null);
    }

    @Override
    public void sendMessage(int clientId, @NotNull Message message, @Nullable Map<String, String> properties) throws
            MessagingException {
        Objects.requireNonNull(message, "message must not be null");
        String[] output = formatter.format(message.getText(), properties);
        sendMessage(clientId, output);
    }

    @Override
    public void fetchAndSendMessage(int clientId, @NotNull String context) {
        fetchAndSendMessage(clientId, context, null);
    }

    @Override
    public void fetchAndSendMessage(int clientId, @NotNull String context, @Nullable Map<String, String> properties) {
        List<Message> messages = null;
        try {
            messages = getMessages(context, Messages.DEFAULT_LOCALE);
            messages = filterMessages(messages, clientId);
            sendMessage(clientId, getOne(messages));
        } catch (MessagingException e) {
            LOG.error("cannot send message to client " + clientId, e);
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
     * Returns exactly one randomly selected {@link Message} from a {@link List} of messages.
     *
     * @param messages the {@link List} of {@link Message} objects
     * @return exactly one {@link Message} from the original {@link List}
     */
    private Message getOne(@NotNull List<Message> messages) {
        return messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
    }

}
