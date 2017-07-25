package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Lukas on 25.07.2017.
 */
public abstract class MessageHandler<T extends Command> {

    protected AeQESportsTS3Bot ts3Bot;
    protected String prefix;
    protected CommandPermissions permissions;

    public MessageHandler(@NotNull AeQESportsTS3Bot ts3Bot, @NotNull String prefix) {
        this.ts3Bot = ts3Bot;
        this.prefix = prefix;
    }

    public final void handle(@NotNull T command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        beforePermissionCheck(command, eventInfo, isFullAdmin, isAdmin);
        if (!permissions.match(eventInfo, isFullAdmin, isAdmin)) {
            onFailedPermissionCheck(command, eventInfo, isFullAdmin, isAdmin);
            return;
        }
        onSuccessfulPermissionCheck(command, eventInfo, isFullAdmin, isAdmin);
    }

    protected void beforePermissionCheck(@NotNull T command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {

    }

    protected abstract void onSuccessfulPermissionCheck(@NotNull T command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin);

    protected void onFailedPermissionCheck(@NotNull T command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {

    }

    public @NotNull String getPrefix() {
        return prefix;
    }

    public CommandPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(CommandPermissions permissions) {
        this.permissions = permissions;
    }

}
