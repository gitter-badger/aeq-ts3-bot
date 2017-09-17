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

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3.bot.admittance.api.AdmittanceWorkflow;
import de.esports.aeq.ts3.bot.channels.Channel;
import de.esports.aeq.ts3.bot.channels.api.ChannelManagement;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.privilege.Roles;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.workflow.exception.WorkflowException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Command to be used by applicants.
 * <p>
 * If the client has his online account already linked to his teamspeak account, the he will be moved into the channel
 * for applicants. Also, all clients matching {@link Roles#SUPPORTER} will be notified about that client.
 * <p>
 * If the client does not have his online account linked, a message will be displayed to do that first.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 09.09.2017
 */
@Component
@Scope("prototype")
public class CApply implements Command {

    private static final String PREFIX = "apply";

    private PrivilegeApi privilege;
    private Messaging messaging;
    private AdmittanceWorkflow workflow;
    private ChannelManagement channelManagement;

    @Autowired
    public CApply(PrivilegeApi privilege, Messaging messaging, AdmittanceWorkflow workflow, ChannelManagement
            channelManagement) {
        this.privilege = privilege;
        this.messaging = messaging;
        this.workflow = workflow;
        this.channelManagement = channelManagement;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull TextMessageEvent event) throws CommandExecutionException {
        // members should not be able to use this command, no need to apply
        try {
            if (privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Roles.MEMBER)) {
                messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_APPLY_ALREADY_MEMBER);
                return;
            }
        } catch (WorkflowException e) {
            // TODO: send message to user
        }

        if (!workflow.isAccountLinked(event.getInvokerUniqueId())) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_APPLY_NOT_LINKED, event.getMap());
        } else {
            channelManagement.moveClientToChannel(event.getInvokerId(), Channel.APPLICATION_CHANNEL);
            workflow.notifyMemberRecruitersAboutApplicant(event.getInvokerUniqueId());
        }
    }

}
