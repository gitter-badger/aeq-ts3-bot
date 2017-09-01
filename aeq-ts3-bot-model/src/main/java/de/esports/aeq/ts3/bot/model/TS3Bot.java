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

package de.esports.aeq.ts3.bot.model;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;

/**
 * @author Lukas Kannenberg
 */
public interface TS3Bot {

    /**
     * Builds the configuration used by this bot using the given pathname.
     *
     * @param pathname the pathname of the configuration file
     */
    void buildConfiguration(String pathname);

    /**
     * Starts the bot if it is not already running.
     */
    void start();

    /**
     * Stops the bot if it is running.
     */
    void stop();

    /**
     * Restarts the bot.
     * <p>
     * This method should be equal to calling {@link #stop()} followed by {@link #start()}.
     */
    void restart();

    /**
     * Returns the underlying synchronous {@link TS3Api}.
     *
     * @return the underlying synchronous api
     */
    TS3Api getTs3Api();

    /**
     * Returns the underlying asynchronous {@link TS3ApiAsync}.
     *
     * @return the underlying asynchronous api
     */
    TS3ApiAsync getTs3ApiAsync();

}
