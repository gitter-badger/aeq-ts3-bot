package de.esports.aeq.ts3bot.command.permission;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Created by Lukas on 25.07.2017.
 */
public class CommandPermissionValidator {

    private CommandPermissions permissions;

    public CommandPermissionValidator(@Nullable CommandPermissions permissions) {
        this.permissions = permissions;
    }

    public boolean match(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return true;
    }

    public CommandPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(CommandPermissions permissions) {
        this.permissions = permissions;
    }
}
