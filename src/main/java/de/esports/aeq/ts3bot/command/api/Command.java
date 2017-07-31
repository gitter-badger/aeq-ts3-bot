package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface that represents a parsable command from user input.
 * <p>
 * All commands should implement tis interface.
 *
 * @author Lukas Kannenberg
 * @since 25.07.2017.
 */
public interface Command {

    /**
     * @return the prefix that identifies to this command, not null
     */
    @NotNull
    String getPrefix();

    /**
     * Executes this command with the given {@link CExecutionContext}.
     *
     * @param context the execution context of this command, not null
     * @throws CHandleException if an error occurs during the execution process
     */
    void execute(@NotNull CExecutionContext context) throws CHandleException;

}
