package de.esports.aeq.ts3bot.command.permission;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import org.jetbrains.annotations.NotNull;

public class UnrestrictedPermissionValidator implements CommandPermissionValidator {

    public UnrestrictedPermissionValidator() {

    }

    @Override
    public boolean match(@NotNull Command command, @NotNull TextMessageEvent event) {
        return true;
    }
}
