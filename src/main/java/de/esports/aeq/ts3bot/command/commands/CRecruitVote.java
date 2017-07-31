package de.esports.aeq.ts3bot.command.commands;

import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;

import java.util.HashMap;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CRecruitVote extends Command {

    public static final String PREFIX = "vote";

    public CRecruitVote(String prefix) {
        super(PREFIX);
    }

    @Override
    public void handle(AeQESportsTS3Bot botInstance, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) throws CHandleException {
        // TODO(glains)
    }
}
