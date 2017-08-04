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
import de.esports.aeq.ts3bot.message.Messages;

public class DefaultTextMessageHandler extends TS3EventAdapter {

    private CommandParser commandParser = new DefaultCommandParser();
    private AsyncCommandHandler asyncCommandHandler = new AsyncCommandHandler();

    private AeqTS3Bot ts3Bot;

    public DefaultTextMessageHandler(AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
        Command command = null;
        try {
            command = commandParser.parse(e.getMessage());
        } catch (UnregisteredCommandException ex) {
            String message = Messages.getTranslatedString(Messages.UNKNOWN_COMMAND);
            ts3Bot.getApi().sendPrivateMessage(e.getInvokerId(), message);
        } catch (CommandParsingException ex) {
            ex.printStackTrace();
        }
        // handle each command in a new thread
        if (command != null) asyncCommandHandler.handle(command, e);
    }
}
