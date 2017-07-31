package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.command.exception.CommandParsingException;
import org.jetbrains.annotations.NotNull;

/**
 * This interface defines how commands are being parsed.
 *
 * @author Lukas Kannenberg
 * @since 31.07.2017
 */
@FunctionalInterface
public interface CommandParser {

    /**
     * Attempts to parse a command from a given message.
     *
     * @param message the message to be parsed
     * @return always a valid {@link Command} if no exception has been thrown
     * @throws CommandParsingException if an exception occurred during the process.
     * @see de.esports.aeq.ts3bot.command.exception.InvalidPrefixException
     * @see de.esports.aeq.ts3bot.command.exception.UnregisteredCommandException
     */
    @NotNull Command parse(@NotNull String message) throws CommandParsingException;
}
