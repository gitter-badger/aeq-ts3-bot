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

public class GuestsClientJoinHandlerConfiguration {

    /**
     * Total amount of messages send, one for each time the user reconnects.
     */
    private int repeatAmount = -1;

    /**
     * If there is a timeout the user wont be notified within that time.
     */
    private int repeatTimeout = -1;

    /**
     * Create a new configuration with default values.
     */
    public GuestsClientJoinHandlerConfiguration() {

    }

    public GuestsClientJoinHandlerConfiguration(int repeatAmount, int repeatTimeout) {
        this.repeatAmount = repeatAmount;
        this.repeatTimeout = repeatTimeout;
    }

    public int getRepeatAmount() {
        return repeatAmount;
    }

    public void setRepeatAmount(int repeatAmount) {
        this.repeatAmount = repeatAmount;
    }

    public int getRepeatTimeout() {
        return repeatTimeout;
    }

    public void setRepeatTimeout(int repeatTimeout) {
        this.repeatTimeout = repeatTimeout;
    }
}
