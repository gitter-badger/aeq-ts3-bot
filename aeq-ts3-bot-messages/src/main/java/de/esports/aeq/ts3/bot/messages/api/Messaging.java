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

package de.esports.aeq.ts3.bot.messages.api;

import de.esports.aeq.ts3.bot.messages.MessagingException;
import de.esports.aeq.ts3.bot.model.message.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Defines common methods for retrieving and sending messages to clients.
 * <p>
 * Implementations should provide an easy way for other modules to fetch {@link Message} objects from the persistence
 * layer while also do not have to worry about any formatting of messages.
 *
 * @author Lukas Kannenberg
 * @version 1.2
 * @since 05.09.2017
 */
public interface Messaging {

    /**
     * Returns a {@link Message} that matches the given context and {@link Locale}.
     * <p>
     * Even if multiple matches are found, only one message is returned. This is effectively equal to calling {@link
     * #getMessages(String, Locale)} and returning one message of the list.
     *
     * @param context the context of the message
     * @param locale  the locale of the message
     * @return a {@link Message} that matches the given context and {@link Locale} or <code>null</code> if not match can
     * be found
     * @throws MessagingException if an error occurs while retrieving the message
     */
    @Nullable
    Message getMessage(@NotNull String context, @NotNull Locale locale) throws MessagingException;

    /**
     * Returns a {@link List} of {@link Message} objects that match the given context and {@link Locale}.
     *
     * @param context the context of the message
     * @param locale  the locale of the message
     * @return a {@link List} of {@link Message} objects that match the given context and {@link Locale} or an empty
     * list if no match can be found
     * @throws MessagingException if an error occurs while retrieving the messages
     */
    @NotNull
    List<Message> getMessages(@NotNull String context, @NotNull Locale locale) throws MessagingException;

    /**
     * Filters a {@link List} of {@link Message} objects.
     * <p>
     * This process involves iterating through all messages and applying all filters of that message to itself. If at
     * least one filter does not match, the message will be filtered and therefore removed from the resulting list.
     *
     * @param messages a {@link List} of {@link Message} objects to be filtered
     * @param clientId the client id to retrieve information for the filters
     * @return the filtered {@link List} of {@link Message} objects (will always return a list, even if empty)
     * @throws MessagingException if an error occurs while filtering
     */
    @NotNull
    List<Message> filterMessages(@NotNull List<Message> messages, int clientId) throws MessagingException;

    /**
     * Sends the target {@link Message} to the specified client and applies basic formatting using the default
     * formatter.
     *
     * @param clientId the id of the client
     * @param message  the message to send to the client
     * @throws MessagingException if the message can not be send, or any other exception occurs during the process
     */
    void sendMessage(int clientId, @NotNull Message message) throws MessagingException;

    /**
     * Sends the target {@link Message} to the specified client and applies basic formatting using the default
     * formatter.
     *
     * @param clientId   the id of the client
     * @param message    the message to send to the client
     * @param properties any additional properties to pass to the formatter that will be considered when performing key
     *                   value replacements
     * @throws MessagingException if the message can not be send, or any other exception occurs during the process
     */
    void sendMessage(int clientId, @NotNull Message message, @Nullable Map<String, String> properties) throws
            MessagingException;

    /**
     * Fetches a {@link Message} that match the given context using the clients default {@link Locale}. The target
     * {@link Message} is then send to the specified client. Also, basic formatting is applied by the default
     * formatter.
     *
     * @param clientId the id of the client
     * @param context  the context of the message to fetch
     * @return true if the message was send successfully, otherwise false
     */
    boolean fetchAndSendMessage(int clientId, @NotNull String context);

    /**
     * Fetches a {@link Message} that match the given context using the clients default {@link Locale}. The target
     * {@link Message} is then send to the specified client. Also, basic formatting is applied by the default
     * formatter.
     *
     * @param clientId   the id of the client
     * @param context    the context of the message to fetch
     * @param properties any additional properties to pass to the formatter that will be considered when performing key
     *                   value replacements
     * @return true if the message was send successfully, otherwise false
     */
    boolean fetchAndSendMessage(int clientId, @NotNull String context, @Nullable Map<String, String> properties);

    /**
     * Iterates through the provided array of contexts and fetches the related {@link Message} using the default {@link
     * Locale}. The first {@link Message} found is then send to the specified client. Also, basic formatting is applied
     * by the default formatter.
     *
     * @param clientId   the id of the client
     * @param contexts   the contexts of the messages to fetch
     * @param properties any additional properties to pass to the formatter that will be considered when performing key
     *                   value replacements
     * @return true if at least one message was send successfully, otherwise false
     */
    boolean fetchAndSendFirstMessage(int clientId, @Nullable String[] contexts, @Nullable Map<String, String>
            properties);

}
