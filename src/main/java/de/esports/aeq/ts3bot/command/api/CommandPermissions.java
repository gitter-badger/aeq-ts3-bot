package de.esports.aeq.ts3bot.command.api;

import java.util.HashMap;

/**
 * Created by Lukas on 25.07.2017.
 */
@FunctionalInterface
public interface CommandPermissions {

    // TODO(glains): validation maybe async?! use Observable<Boolean>?
    public boolean match(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin);

}
