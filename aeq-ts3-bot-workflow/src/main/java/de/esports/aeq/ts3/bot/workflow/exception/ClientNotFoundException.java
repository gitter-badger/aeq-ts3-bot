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

package de.esports.aeq.ts3.bot.workflow.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown whenever a teamspeak client cannot be found.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 15.09.2017
 */
public class ClientNotFoundException extends WorkflowException {

    /**
     * The unique id of the client.
     */
    private final String clientUniqueId;

    /**
     * Constructs a new {@link ClientNotFoundException} with a client unique id.
     *
     * @param clientUniqueId the unique client if that can not been found
     */
    public ClientNotFoundException(String clientUniqueId) {
        this.clientUniqueId = clientUniqueId;
    }

    /**
     * Constructs a new {@link ClientNotFoundException} with message and a client unique id.
     *
     * @param message        the message
     * @param clientUniqueId the unique client if that can not been found
     */
    public ClientNotFoundException(String message, String clientUniqueId) {
        super(message);
        this.clientUniqueId = clientUniqueId;
    }

    /**
     * @return the unique id of the client
     */
    @NotNull
    public String getClientUniqueId() {
        return clientUniqueId;
    }
}
