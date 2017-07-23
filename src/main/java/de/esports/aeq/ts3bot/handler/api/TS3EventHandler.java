package de.esports.aeq.ts3bot.handler.api;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Lukas on 23.07.2017.
 */
public abstract class TS3EventHandler {

    protected AeQESportsTS3Bot ts3Bot;

    public TS3EventHandler(@NotNull AeQESportsTS3Bot ts3Bot) {
        if (ts3Bot == null) throw new IllegalArgumentException("ts3bot cannot be null");
        this.ts3Bot = ts3Bot;
    }

    public abstract void handleTS3Event(String type, HashMap eventInfo);

}
