package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ConnectionInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.VirtualServer;
import de.esports.aeq.ts3bot.command.AsyncCommandHandler;
import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.DefaultCommandParser;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandParser;
import de.esports.aeq.ts3bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3bot.command.exception.UnregisteredCommandException;
import de.esports.aeq.ts3bot.message.Messages;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 23.07.2017.
 */
public class AeQESportsTS3Bot {

    private CommandParser commandParser = new DefaultCommandParser();
    private AsyncCommandHandler asyncCommandHandler = new AsyncCommandHandler();

    public static void main(String[] args) {
        final TS3Config config = new TS3Config();
        config.setHost("137.74.223.0");
        config.setConnectionHandler(new ConnectionHandler() {
            @Override
            public void onConnect(TS3Query ts3Query) {
                System.out.println("CONNECTED");
            }

            @Override
            public void onDisconnect(TS3Query ts3Query) {
                System.out.println("DISCONNECTED");
            }
        });

        final TS3Query query = new TS3Query(config);
        query.connect();

        final TS3Api api = query.getApi();
        api.selectVirtualServerByPort(9889);

        // LOGIN

        api.setNickname("Merlin");

        // Get our own client ID by running the "whoami" command
        final int clientId = api.whoAmI().getId();
        System.out.println(clientId + "\n");
    }

    public boolean handleChatCommands(String message, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        Command command = null;
        try {
            commandParser.parse(message);
        } catch (UnregisteredCommandException e) {
            String errorMessage = Messages.getTranslatedString(Messages.UNKNOWN_COMMAND);
            // TODO(glains): send message to client
        } catch (CommandParsingException e) {
            e.printStackTrace();
            return false;
        }
        // handle each command in a new thread if it is valid
        CommandExecutionContext context = new CommandExecutionContext(this, eventInfo, isFullAdmin, isAdmin);
        asyncCommandHandler.handle(command, context);
        return true;
    }

}
