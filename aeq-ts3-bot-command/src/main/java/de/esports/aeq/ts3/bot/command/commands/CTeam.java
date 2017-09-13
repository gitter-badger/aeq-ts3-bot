/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.command.commands;

import com.beust.jcommander.Parameter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3.bot.command.CommandTeamType;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.privilege.Role;
import de.esports.aeq.ts3.bot.privilege.api.Privilege;
import de.esports.aeq.ts3.bot.workflow.api.TeamWorkflow;
import de.esports.aeq.ts3.bot.workflow.exception.InsufficientPermissionException;
import de.esports.aeq.ts3.bot.workflow.exception.InvalidTeamNameException;
import de.esports.aeq.ts3.bot.workflow.exception.UnresolvedGameTypeException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Lukas Kannenberg
 */
@Component
@Scope("prototype")
public class CTeam implements Command {

    /**
     * The prefix of this command.
     */
    private static String PREFIX = "team";

    private Messaging messaging;
    private Privilege privilege;
    private TeamWorkflow workflow;
    @Parameter(names = {"--target", "-t"})
    private String type;
    @Parameter(names = {"-id"})
    private String teamId;
    @Parameter(names = {"--name", "-n"})
    private String teamName;

    @Autowired
    public CTeam(Messaging messaging, TeamWorkflow workflow) {
        this.messaging = messaging;
        this.workflow = workflow;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull TextMessageEvent event) throws CommandExecutionException {
        CommandTeamType teamType = null;
        try {
            teamType = CommandTeamType.valueOf(type);
        } catch (IllegalArgumentException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.COMMAND_INVALID_APPLY_TYPE);
            return;
        }

        if (teamType == CommandTeamType.CREATE) {
            handleCreateType(event);
        } else if (teamType == CommandTeamType.DELETE) {
        } else if (teamType == CommandTeamType.JOIN) {
        } else if (teamType == CommandTeamType.LEAVE) {
        }
    }

    /**
     * Uses the given {@link TeamWorkflow} to create a new team.
     * <p>
     * Any exceptions will be converted to related error messages and send to the user.
     *
     * @param event the {@link TextMessageEvent} that triggered this {@link Command}
     */
    private void handleCreateType(@NotNull TextMessageEvent event) {
        if (!privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Role.RECRUIT)) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_TEAM_T_CREATE_INVALID_USER_GROUP);
            return;
        }
        try {
            workflow.createTeam(event.getInvokerUniqueId(), teamName);
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_TEAM_T_CREATE_SUCCESS);
        } catch (InvalidTeamNameException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_TEAM_T_CREATE_INVALID_TEAM_NAME);
        } catch (UnresolvedGameTypeException e) {
            e.printStackTrace();
        } catch (InsufficientPermissionException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
