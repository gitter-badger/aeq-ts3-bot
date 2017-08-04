package de.esports.aeq.ts3bot.command.permission;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Kannenberg
 * @since 25.07.2017.
 */
@FunctionalInterface
public interface CommandPermissionValidator {

    /**
     * Validates if the specified {@link TextMessageEvent} allows the execution of the command.
     *
     * @param command the {@link Command}
     * @param event the {@link TextMessageEvent}
     * @return true if the command may be executed, otherwise false
     */
    boolean match(@NotNull Command command, @NotNull TextMessageEvent event);
}
