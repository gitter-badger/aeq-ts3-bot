package de.esports.aeq.ts3bot.message;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.AsyncCommandHandler;
import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.DefaultCommandParser;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandParser;
import de.esports.aeq.ts3bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3bot.command.exception.UnregisteredCommandException;
import de.esports.aeq.ts3bot.event.TextMessageHandler;

public class DefaultTextMessageHandler implements TextMessageHandler {

    private TS3Api api;
    private TS3ApiAsync apiAsync;

    private CommandParser commandParser = new DefaultCommandParser();
    private AsyncCommandHandler asyncCommandHandler = new AsyncCommandHandler();

    public DefaultTextMessageHandler(TS3Api api, TS3ApiAsync apiAsync) {
        this.api = api;
        this.apiAsync = apiAsync;
    }

    @Override
    public void handle(TextMessageEvent event) {
        Command command = null;
        try {
            command = commandParser.parse(event.getMessage());
        } catch (UnregisteredCommandException e) {
            String errorMessage = Messages.getTranslatedString(Messages.UNKNOWN_COMMAND);
            // TODO(glains): send message to client
        } catch (CommandParsingException e) {
            e.printStackTrace();
        }
        // handle each command in a new thread
        CommandExecutionContext context = new CommandExecutionContext(api, apiAsync, event);
        asyncCommandHandler.handle(command, context);
    }

    public TS3Api getApi() {
        return api;
    }

    public void setApi(TS3Api api) {
        this.api = api;
    }

    public TS3ApiAsync getApiAsync() {
        return apiAsync;
    }

    public void setApiAsync(TS3ApiAsync apiAsync) {
        this.apiAsync = apiAsync;
    }


}
