package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;

import java.util.HashMap;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CLink extends Command {

    public static final String PREFIX = "link";

    public CLink(String prefix) {
        super(PREFIX);
    }

    @Override
    public void handle(AeQESportsTS3Bot botInstance, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) throws CHandleException {
        // TODO(glains)
    }
}
