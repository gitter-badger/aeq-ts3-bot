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

package de.aeq.esports.ts3.bot.messages;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import de.aeq.esports.ts3.bot.messages.api.EventMessageFilter;
import de.esports.aeq.ts3.bot.model.TS3Bot;

public class EMAmountConnectedFilter implements EventMessageFilter {

    private TS3Bot ts3Bot;
    private int minJoins = -1;
    private int maxJoins = -1;

    public EMAmountConnectedFilter(TS3Bot ts3Bot, int minJoins, int maxJoins) {
        this.ts3Bot = ts3Bot;
        this.minJoins = minJoins;
        this.maxJoins = maxJoins;
    }

    @Override
    public boolean apply(Message message, BaseEvent event) {
        return isWithinRange(getAmountConnected(event.getInvokerId()));
    }

    private int getAmountConnected(int clientId) {
        // TODO: make a database call here
        return 0;
    }

    private boolean isWithinRange(int connectionAmount) {
        return (minJoins == -1 || connectionAmount >= minJoins) && (maxJoins == -1 || connectionAmount <= maxJoins);
    }

    public int getMinJoins() {
        return minJoins;
    }

    public void setMinJoins(int minJoins) {
        this.minJoins = minJoins;
    }

    public int getMaxJoins() {
        return maxJoins;
    }

    public void setMaxJoins(int maxJoins) {
        this.maxJoins = maxJoins;
    }
}
