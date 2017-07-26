package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.handler.api.MessageHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface that represents a parsable command from user input.
 * <p>
 * All commands should implement tis interface.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 25.07.2017.
 */
public interface Command {

    @Nullable MessageHandler createMessageHandler(@NotNull AeQESportsTS3Bot ts3Bot);

    @Nullable CommandPermissions getPermissions();

}
