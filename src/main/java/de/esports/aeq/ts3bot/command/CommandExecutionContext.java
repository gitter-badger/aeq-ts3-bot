package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;

import java.util.HashMap;

public class CommandExecutionContext {

    private AeQESportsTS3Bot botInstance;
    private HashMap<String, String> eventInfo;
    private boolean isFullAdmin;
    private boolean isAdmin;

    public CommandExecutionContext(AeQESportsTS3Bot botInstance, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        this.botInstance = botInstance;
        this.eventInfo = eventInfo;
        this.isFullAdmin = isFullAdmin;
        this.isAdmin = isAdmin;
    }

    public AeQESportsTS3Bot getBotInstance() {
        return botInstance;
    }

    public void setBotInstance(AeQESportsTS3Bot botInstance) {
        this.botInstance = botInstance;
    }

    public HashMap<String, String> getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(HashMap<String, String> eventInfo) {
        this.eventInfo = eventInfo;
    }

    public boolean isFullAdmin() {
        return isFullAdmin;
    }

    public void setFullAdmin(boolean fullAdmin) {
        isFullAdmin = fullAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
