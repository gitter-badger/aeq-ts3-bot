package de.esports.aeq.ts3bot.command.permission;

import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import org.jetbrains.annotations.NotNull;

public class UnrestrictedPermissionValidator implements CommandPermissionValidator {

    public UnrestrictedPermissionValidator() { }

    @Override
    public boolean match(@NotNull Command command, @NotNull CommandExecutionContext context) {
        return true;
    }
}
