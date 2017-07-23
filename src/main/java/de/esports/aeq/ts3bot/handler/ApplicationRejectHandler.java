package de.esports.aeq.ts3bot.handler;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.handler.api.TS3CommandHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationRejectHandler extends TS3CommandHandler {

    public ApplicationRejectHandler(@NotNull AeQESportsTS3Bot ts3Bot) {
        super(ts3Bot);
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public boolean canExecute(HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return true;
    }

    @Override
    public boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return false;
    }
}
