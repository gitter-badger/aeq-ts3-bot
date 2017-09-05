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

import de.esports.aeq.ts3.bot.model.message.Message;

import java.util.Locale;
import java.util.Map;

/**
 * Defines common methods for a messaging component.
 * <p>
 * Implementations should provide an easy way for other modules to fetch {@link Message} objects from the persistence
 * layer while also do not have to worry about any formatting of messages.
 *
 * @author Lukas Kannenberg
 * @version 1.1
 * @since 05.09.2017
 */
public interface Messaging {

    /**
     * Sends the target {@link Message} to the specified client and applies basic formatting using the default
     * formatter.
     *
     * @param clientId the id of the client
     * @param message  the message to send to the client
     * @throws MessagingException if the message can not be send, or any other exception occurs during the process
     */
    void sendMessage(int clientId, Message message) throws MessagingException;

    /**
     * Sends the target {@link Message} to the specified client and applies basic formatting using the default
     * formatter.
     *
     * @param clientId   the id of the client
     * @param message    the message to send to the client
     * @param properties any additional properties to pass to the formatter that will be considered when performing
     *                   key value replacements
     * @throws MessagingException if the message can not be send, or any other exception occurs during the process
     */
    void sendMessage(int clientId, Message message, Map<String, String>... properties) throws MessagingException;

    /**
     * Fetches a {@link Message} that match the given context using the clients default {@link Locale}. The target
     * {@link Message} is then send to the specified client. Also, basic formatting is applied by the default formatter.
     *
     * @param clientId the id of the client
     * @param context  the context of the message to fetch
     * @throws MessagingException
     */
    void fetchAndSendMessage(int clientId, String context) throws MessagingException;

    /**
     * Fetches a {@link Message} that match the given context using the clients default {@link Locale}. The target
     * {@link Message} is then send to the specified client. Also, basic formatting is applied by the default formatter.
     *
     * @param clientId   the id of the client
     * @param context    the context of the message to fetch
     * @param properties any additional properties to pass to the formatter that will be considered when performing
     *                   key value replacements
     * @throws MessagingException if the message can not be send or found, or any other exception occurs during the
     *                            process
     */
    void fetchAndSendMessage(int clientId, String context, Map<String, String>... properties) throws MessagingException;

}
