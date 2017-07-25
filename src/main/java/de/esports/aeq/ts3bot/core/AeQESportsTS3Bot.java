package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.command.Command;
import de.esports.aeq.ts3bot.command.CommandPermissions;
import de.esports.aeq.ts3bot.command.MessageHandler;
import de.esports.aeq.ts3bot.handler.ApplicationAcceptHandler;
import de.stefan1200.jts3servermod.interfaces.HandleBotEvents;
import de.stefan1200.jts3servermod.interfaces.HandleTS3Events;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 23.07.2017.
 */
public class AeQESportsTS3Bot implements HandleBotEvents, HandleTS3Events {

    private JTS3ServerMod_Interface jts3ServerMod;
    private JTS3ServerQuery jts3ServerQuery;
    private String prefix;

    private HashMap<String, MessageHandler> messageHandlers = new HashMap<>();
    private HashMap<String, CommandPermissions> commandPermissions = new HashMap<>();

    @Override
    public void initClass(JTS3ServerMod_Interface jts3ServerMod_interface, JTS3ServerQuery jts3ServerQuery, String s) {
        this.jts3ServerMod = jts3ServerMod_interface;
        this.jts3ServerQuery = jts3ServerQuery;
        this.prefix = prefix;
        initMessageHandlers();
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
        List<String> commandList = new ArrayList<>();
        for (HashMap.Entry<String, MessageHandler> entry : messageHandlers.entrySet()) {
            if (entry.getValue() == null) continue;
            // Only show commands which match the permissions of the user
            if (entry.getValue().getPermissions().match(eventInfo, isFullAdmin, isAdmin)) {
                commandList.add(entry.getKey());
            }
        }
        return commandList.toArray(new String[0]);
    }

    @Override
    public String botChatCommandHelp(String command) {
        // TODO(glains): write helper class for prefix
        return "";
    }

    @Override
    public boolean handleChatCommands(String message, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        // TODO(glains): extract command prefix from message
        String prefix = "";

        Command command = null; // TODO(glains): Parse command here
        MessageHandler handler = messageHandlers.get(prefix);
        if (handler != null) {
            handler.handle(command, eventInfo, isFullAdmin, isAdmin);
            return true;
        }
        return false;
    }

    @Override
    public void handleTS3Events(String eventType, HashMap eventInfo) {

    }

    private void addMessageHandler(MessageHandler messageHandler) {
        this.messageHandlers.put(messageHandler.getPrefix(), messageHandler);
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

    private void initMessageHandlers() {
        // TODO(glains): better automate that process, read permissions from config file etc.
        MessageHandler handler = new ApplicationAcceptHandler(this);
        CommandPermissions permissions = commandPermissions.get(handler.getPrefix());
        if (permissions != null) handler.setPermissions(permissions);
        addMessageHandler(handler);
    }

}
