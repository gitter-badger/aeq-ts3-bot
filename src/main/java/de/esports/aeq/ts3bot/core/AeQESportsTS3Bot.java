package de.esports.aeq.ts3bot.core;

import de.esports.aeq.ts3bot.handler.ApplicationAcceptHandler;
import de.esports.aeq.ts3bot.handler.api.TS3CommandHandler;
import de.esports.aeq.ts3bot.handler.api.TS3EventHandler;
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

    private HashMap<String, TS3CommandHandler> commandHandlers = new HashMap();
    private List<TS3EventHandler> eventHandlers = new ArrayList<>();

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
        List<String> commandList = new ArrayList<>();
        for (HashMap.Entry<String, TS3CommandHandler> entry : commandHandlers.entrySet()) {
            if (entry.getValue() == null) continue;
            if (entry.getValue().canExecute(eventInfo, isFullAdmin, isAdmin)) commandList.add(entry.getKey());
        }
        return commandList.toArray(new String[0]);
    }

    @Override
    public String botChatCommandHelp(String command) {
        TS3CommandHandler handler = commandHandlers.get(command);
        return handler != null ? handler.getHelpText() : null;
    }

    @Override
    public boolean handleChatCommands(String command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        TS3CommandHandler handler = commandHandlers.get(command);
        if (handler != null) return handler.handleCommand(command, eventInfo, isFullAdmin, isAdmin);
        return false;
    }

    @Override
    public void handleTS3Events(String eventType, HashMap eventInfo) {
        for (TS3EventHandler handler : this.eventHandlers) {
            handler.handleTS3Event(eventType, eventInfo);
        }
    }

    public void addCommandHandler(String command, TS3CommandHandler commandHandler) {
        this.commandHandlers.put(command, commandHandler);
    }

    public void removeCommandHandler(String command) {
        this.commandHandlers.remove(command);
    }

    public void addTS3EventHandler(TS3EventHandler eventHandler) {
        this.eventHandlers.add(eventHandler);
    }

    public void removeTS3CommandHandler(TS3EventHandler eventHandler) {
        this.eventHandlers.remove(eventHandler);
    }

    private void initializeHandlers() {
        new ApplicationAcceptHandler(this);
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
