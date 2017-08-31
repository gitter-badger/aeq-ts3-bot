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

import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EchoTextMessageHandler extends TS3EventAdapter {

    private static final Logger log = LoggerFactory.getLogger(EchoTextMessageHandler.class);

    private TS3Bot ts3Bot;

    @Autowired
    public EchoTextMessageHandler(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
        if (e.getTargetMode() != TextMessageTargetMode.CLIENT) return;
        log.debug("received private message from client {}: {}", e.getInvokerName(), e.getMessage());
        if (!ts3Bot.getTs3Api().sendPrivateMessage(e.getInvokerId(), e.getMessage())) {
            log.warn("could not echo reply to {}", e.getInvokerName());
        } else {
            log.debug("successfully echo replied to {}", e.getInvokerName());
        }
    }
}
