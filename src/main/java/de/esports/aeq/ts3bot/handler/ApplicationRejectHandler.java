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
public class ApplicationRejectHandler extends CommandHandler {

    public ApplicationRejectHandler(@NotNull JTS3ServerMod_Interface jts3ServerMod, @NotNull JTS3ServerQuery jts3ServerQuery, @NotNull String prefix) {
        super(jts3ServerMod, jts3ServerQuery, prefix);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return false;
    }
}
