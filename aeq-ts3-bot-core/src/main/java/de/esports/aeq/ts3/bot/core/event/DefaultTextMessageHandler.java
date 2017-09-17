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

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3.bot.command.AsyncCommandHandler;
import de.esports.aeq.ts3.bot.command.DefaultCommandParser;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.api.CommandParser;
import de.esports.aeq.ts3.bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3.bot.command.exception.UnregisteredCommandException;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DefaultTextMessageHandler extends TS3EventAdapter {

    private static final Logger log = LoggerFactory.getLogger(DefaultTextMessageHandler.class);

    private AsyncCommandHandler asyncCommandHandler = new AsyncCommandHandler();

    private ApplicationContext context;
    private TS3Bot ts3Bot;
    private EchoTextMessageHandler messageHandler;

    @Autowired
    public DefaultTextMessageHandler(ApplicationContext context, TS3Bot ts3Bot, EchoTextMessageHandler
            messageHandler) {
        this.context = context;
        this.ts3Bot = ts3Bot;
        this.messageHandler = messageHandler;
    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
        log.debug("received text message from {}: {}", e.getInvokerName(), e.getMessage());
        if (isCommand(e.getMessage())) {
            log.debug("message as been identified as command, parsing...");
            parseCommand(e);
        } else {
            messageHandler.onTextMessage(e);
        }
    }

    private boolean isCommand(String message) {
        return message.startsWith(DefaultCommandParser.COMMAND_PREFIX);
    }

    private void parseCommand(TextMessageEvent e) {
        Command command;
        try {
            CommandParser parser = context.getBean(DefaultCommandParser.class);
            command = parser.parse(e.getMessage());
            // handle each command in a new thread
            asyncCommandHandler.handle(command, e);
        } catch (UnregisteredCommandException ex) {
            //String error = Messages.getTranslatedString(Messages.UNKNOWN_COMMAND);
            //ts3Bot.getTs3Api().sendPrivateMessage(e.getInvokerId(), "Command not found");
        } catch (CommandParsingException ex) {
            log.warn("error while parsing command", ex);
        }
    }
}
