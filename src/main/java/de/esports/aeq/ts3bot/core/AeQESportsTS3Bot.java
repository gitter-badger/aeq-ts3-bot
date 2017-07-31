package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.command.AsyncCommandHandler;
import de.esports.aeq.ts3bot.command.CommandParser;
import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CommandParsingException;
import de.esports.aeq.ts3bot.command.exceptions.UnregisteredCommandException;
import de.esports.aeq.ts3bot.messages.Messages;
import de.stefan1200.jts3servermod.interfaces.HandleBotEvents;
import de.stefan1200.jts3servermod.interfaces.HandleTS3Events;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;

import java.util.HashMap;

/**
 * Created by Lukas on 23.07.2017.
 */
public class AeQESportsTS3Bot implements HandleBotEvents, HandleTS3Events {

    private JTS3ServerMod_Interface jts3ServerMod;
    private JTS3ServerQuery jts3ServerQuery;
    private String prefix;

    private AsyncCommandHandler asyncCommandHandler = new AsyncCommandHandler();

    @Override
    public void initClass(JTS3ServerMod_Interface jts3ServerMod_interface, JTS3ServerQuery jts3ServerQuery, String
            prefix) {
        this.jts3ServerMod = jts3ServerMod_interface;
        this.jts3ServerQuery = jts3ServerQuery;
        this.prefix = prefix;
    }

    @Override
    public void handleOnBotConnect() {

    }

    @Override
    public void handleAfterCacheUpdate() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void unload() {

    }

    @Override
    public boolean multipleInstances() {
        return false;
    }

    @Override
    public int getAPIBuild() {
        return 0;
    }

    @Override
    public String getCopyright() {
        return null;
    }

    @Override
    public String[] botChatCommandList(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return null;
    }

    @Override
    public String botChatCommandHelp(String command) {
        // TODO(glains): write helper class for prefix
        return "";
    }

    @Override
    public boolean handleChatCommands(String message, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        Command command = null;
        try {
            command = new CommandParser(message).parse();
        } catch (UnregisteredCommandException e) {
            String errorMessage = Messages.getTranslatedString(Messages.UNKNOWN_COMMAND);
            jts3ServerMod.sendMessageToClient(prefix, "chat", 0001, errorMessage);
        } catch (CommandParsingException e) {
            e.printStackTrace();
            return false;
        }
        // handle each command in a new thread if it is valid
        CExecutionContext context = new CExecutionContext(this, eventInfo, isFullAdmin, isAdmin);
        asyncCommandHandler.handle(command, context);
        return true;
    }

    @Override
    public void handleTS3Events(String eventType, HashMap eventInfo) {

    }

    public JTS3ServerMod_Interface getJts3ServerMod() {
        return jts3ServerMod;
    }

    public JTS3ServerQuery getJts3ServerQuery() {
        return jts3ServerQuery;
    }

    public String getPrefix() {
        return prefix;
    }

}
