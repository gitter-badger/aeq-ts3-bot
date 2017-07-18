package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.handler.ApplicationAcceptHandler;
import de.esports.aeq.ts3bot.handler.CommandHandler;
import de.stefan1200.jts3servermod.interfaces.HandleTS3Events;

import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class HandleTS3EventsImpl implements HandleTS3Events {

    private HashMap<String, CommandHandler> commandHandlers = new HashMap();

    public HandleTS3EventsImpl() {
        initializeHandlers();
    }

    private void initializeHandlers() {
        ApplicationAcceptHandler cmdHdApplAccept = new ApplicationAcceptHandler();
        this.commandHandlers.put(cmdHdApplAccept.getName(), cmdHdApplAccept);
    }

    public String[] botChatCommandList(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return new String[0];
    }

    public String botChatCommandHelp(String command) {
        CommandHandler handler = commandHandlers.get(command);
        return handler != null ? handler.getHelpText() : null;
    }

    public boolean handleChatCommands(String command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        CommandHandler handler = commandHandlers.get(command);
        if (handler != null) return handler.handleCommand(command, eventInfo, isFullAdmin, isAdmin);
        return false;
    }

    public void handleTS3Events(String command, HashMap eventInfo) {

    }
}
