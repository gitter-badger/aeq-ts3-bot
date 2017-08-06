package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.AsyncCommandHandler;
import de.esports.aeq.ts3bot.command.DefaultCommandParser;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandParser;
import de.esports.aeq.ts3bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3bot.command.exception.UnregisteredCommandException;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
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
    private AeqTS3Bot ts3Bot;
    private EchoTextMessageHandler messageHandler;

    @Autowired
    public DefaultTextMessageHandler(ApplicationContext context, AeqTS3Bot ts3Bot, EchoTextMessageHandler
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
            //ts3Bot.getApi().sendPrivateMessage(e.getInvokerId(), error);
        } catch (CommandParsingException ex) {
            log.warn("error while parsing command", ex);
        }
    }
}
