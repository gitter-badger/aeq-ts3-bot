package de.esports.aeq.ts3bot.handler;

import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public abstract class CommandHandler {

    protected JTS3ServerMod_Interface jts3ServerMod;
    protected JTS3ServerQuery jts3ServerQuery;

    public CommandHandler(@NotNull JTS3ServerMod_Interface jts3ServerMod, @NotNull JTS3ServerQuery jts3ServerQuery) {
        if (jts3ServerMod == null) throw new IllegalArgumentException("jts3ServerMod cannot be null");
        if (jts3ServerQuery == null) throw new IllegalArgumentException("jts3ServerQuery cannot be null");
        this.jts3ServerMod = jts3ServerMod;
        this.jts3ServerQuery = jts3ServerQuery;
    }

    public abstract String getName();

    public abstract String getHelpText();

    public abstract boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean
            isAdmin);
}
