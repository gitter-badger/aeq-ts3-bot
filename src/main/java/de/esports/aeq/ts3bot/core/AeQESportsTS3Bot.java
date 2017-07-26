package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.command.CommandParser;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandPermissions;
import de.esports.aeq.ts3bot.command.exceptions.CommandParsingException;
import de.esports.aeq.ts3bot.handler.api.MessageHandler;
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

    private HashMap<String, CommandPermissions> commandPermissions = new HashMap<>();

    @Override
    public void initClass(JTS3ServerMod_Interface jts3ServerMod_interface, JTS3ServerQuery jts3ServerQuery, String s) {
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
        } catch (CommandParsingException e) {
            e.printStackTrace(); // TODO(glains): send error message to user, catch detailed exceptions
            return false;
        }
        if (!command.getPermissions().match(eventInfo, isFullAdmin, isAdmin)) {
            // TODO(glains): send invalid permissions error message to user
            return true;
        }
        MessageHandler handler = command.createMessageHandler(this);
        if (handler != null) {
            handler.handle(command, eventInfo, isFullAdmin, isAdmin);
            return true;
        }
        return false;
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
