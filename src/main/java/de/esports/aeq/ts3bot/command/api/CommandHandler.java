package de.esports.aeq.ts3bot.command.api;

/**
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 27.07.2017.
 */
@FunctionalInterface
public interface CommandHandler {

    /**
     * Handles a given command.
     */
    void handle(Command command, CExecutionContext context);
}
