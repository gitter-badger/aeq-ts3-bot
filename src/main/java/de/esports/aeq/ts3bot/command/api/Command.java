package de.esports.aeq.ts3bot.command.api;

import de.esports.aeq.ts3bot.command.permission.CPermission;

/**
 * Interface that represents a parsable command from user input.
 * <p>
 * All commands should implement tis interface.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 25.07.2017.
 */
public abstract class Command implements CommandHandler {

    private String prefix;
    private CPermission permissions;

    public Command(String prefix) {
        this.prefix = prefix;
    }

    public Command(String prefix, CPermission permissions) {
        this.prefix = prefix;
        this.permissions = permissions;
    }

    /**
     * @return the prefix that identifies this command
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix that identifies this command
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return the permissions to execute this command
     */
    public CPermission getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to execute this command
     */
    public void setPermissions(CPermission permissions) {
        this.permissions = permissions;
    }
}
