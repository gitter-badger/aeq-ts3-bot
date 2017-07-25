package de.esports.aeq.ts3bot.command;

import java.util.HashMap;

/**
 * Created by Lukas on 25.07.2017.
 */
public interface CommandPermissions {

    public boolean match(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin);

}
