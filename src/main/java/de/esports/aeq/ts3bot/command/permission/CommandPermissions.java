package de.esports.aeq.ts3bot.command.permission;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CommandPermissions {

    private boolean enabled;
    private String[] requiredUserGroups;

    public CommandPermissions(boolean enabled, String[] requiredUserGroups) {
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
