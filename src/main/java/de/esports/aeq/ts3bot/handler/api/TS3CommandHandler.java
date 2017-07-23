package de.esports.aeq.ts3bot.handler.api;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public abstract class TS3CommandHandler {

    protected AeQESportsTS3Bot ts3Bot;

    public TS3CommandHandler(@NotNull AeQESportsTS3Bot ts3Bot) {
        if (ts3Bot == null) throw new IllegalArgumentException("ts3bot cannot be null");
        this.ts3Bot = ts3Bot;
    }

    public abstract String getHelpText();

    public abstract boolean canExecute(HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin);

    public abstract boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean
            isAdmin);
}
