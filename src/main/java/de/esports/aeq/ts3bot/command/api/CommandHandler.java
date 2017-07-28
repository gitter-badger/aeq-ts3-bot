package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;

import java.util.HashMap;

/**
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 27.07.2017.
 */
@FunctionalInterface
public interface CommandHandler {

    /**
     * Handles a given command.
     *
     * @param eventInfo   information about the user who triggered the event
     * @param isFullAdmin whether the user has full admin permissions for the bot or not
     * @param isAdmin     whether the user has admin permissions for the bot or not
     * @throws CHandleException if an error occurred during the process and the command was not successfully handled
     */
    void handle(AeQESportsTS3Bot botInstance, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) throws CHandleException;
}
