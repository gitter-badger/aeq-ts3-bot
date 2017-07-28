package de.esports.aeq.ts3bot.command.permission;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CPermission {

    private boolean enabled;
    private String[] requiredUserGroups;

    public CPermission(boolean enabled, String[] requiredUserGroups) {
        this.enabled = enabled;
        this.requiredUserGroups = requiredUserGroups;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getRequiredUserGroups() {
        return requiredUserGroups;
    }

    public void setRequiredUserGroups(String[] requiredUserGroups) {
        this.requiredUserGroups = requiredUserGroups;
    }
}
