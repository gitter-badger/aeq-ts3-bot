package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 27.07.2017.
 */
@FunctionalInterface
public interface CommandHandler {

    /**
     * Handles a given {@link Command} with the provided {@link CommandExecutionContext}
     *
     * @param command the {@link Command} to be handled
     * @param context the provided {@link CommandExecutionContext}
     */
    void handle(@NotNull Command command, @NotNull CommandExecutionContext context);
}
