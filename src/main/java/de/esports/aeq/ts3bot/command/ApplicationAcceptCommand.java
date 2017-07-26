package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.Parameter;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandPermissions;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.UserGroups;
import de.esports.aeq.ts3bot.handler.ApplicationAcceptHandler;
import de.esports.aeq.ts3bot.handler.api.MessageHandler;
import de.esports.aeq.ts3bot.permission.PermissionCheck;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 24.07.2017.
 */
public class ApplicationAcceptCommand implements Command {

    @Parameter()
    private String ts3id;

    public @NotNull String getTs3id() {
        return ts3id;
    }

    public void setTs3id(@NotNull String ts3id) {
        this.ts3id = ts3id;
    }

    @Override
    public @Nullable MessageHandler createMessageHandler(@NotNull AeQESportsTS3Bot ts3Bot) {
        return new ApplicationAcceptHandler(ts3Bot);
    }

    @Override
    public @Nullable CommandPermissions getPermissions() {
        return (eventInfo, isFullAdmin, isAdmin) -> {
            return new PermissionCheck(eventInfo, isFullAdmin, isAdmin).requireUserGroup(UserGroups.MEMBER_RECRUITER).validate();
        };
    }
}
