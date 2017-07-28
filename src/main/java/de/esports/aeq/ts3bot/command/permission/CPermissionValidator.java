package de.esports.aeq.ts3bot.command.permission;

import java.util.HashMap;

/**
 * Created by Lukas on 25.07.2017.
 */
public class CPermissionValidator {

    private CPermission permissions;

    public CPermissionValidator(CPermission permissions) {
        this.permissions = permissions;
    }

    public boolean match(HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return true;
    }

    public CPermission getPermissions() {
        return permissions;
    }

    public void setPermissions(CPermission permissions) {
        this.permissions = permissions;
    }
}
