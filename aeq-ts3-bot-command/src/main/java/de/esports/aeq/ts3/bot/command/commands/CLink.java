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
import de.esports.aeq.ts3.bot.channels.Channel;
import de.esports.aeq.ts3.bot.channels.api.ChannelManagement;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.privilege.Roles;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.workflow.api.AdmittanceNotifications;
import de.esports.aeq.ts3.bot.workflow.api.AdmittanceWorkflow;
import de.esports.aeq.ts3.bot.workflow.exception.ClientNotFoundException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Lukas on 27.07.2017.
 */
@Component
@Scope("prototype")
public class CLink implements Command {

    private static final Logger LOG = LoggerFactory.getLogger(CLink.class);
    private static final String PREFIX = "link";

    private PrivilegeApi privilege;
    private Messaging messaging;
    private AdmittanceWorkflow workflow;
    private ChannelManagement channelManagement;
    private AdmittanceNotifications notifications;

    /**
     * The access key that is used by the member.
     */
    @Parameter
    private String key;

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull TextMessageEvent event) throws CommandExecutionException {
        if (workflow.isAccountLinked(event.getInvokerUniqueId())) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_LINK_ALREADY_LINKED, event.getMap());
            return;
        }
        workflow.linkAccount(event.getInvokerUniqueId(), key);
        privilege.updateServerGroups(event.getInvokerUniqueId());
        messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_LINK_SUCCESSFUL, event.getMap());

        try {
            if (privilege.isMemberOfRole(event.getInvokerUniqueId(), Roles.APPLICANT)) {
                channelManagement.moveClientToChannel(event.getInvokerId(), Channel.APPLICATION_CHANNEL);
                messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_LINK_AFTER_APPLICANT_MOVE);
                notifications.notifyMemberRecruitersAboutApplicant(event.getInvokerUniqueId());
            }
        } catch (UserNotFoundException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_LINK_FAILED, event.getMap());
        } catch (ClientNotFoundException e) {
            // TODO: send message to user
        }
    }
}
