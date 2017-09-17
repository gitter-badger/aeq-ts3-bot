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
import de.esports.aeq.ts3.bot.admittance.api.AdmittanceWorkflow;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.model.RecruitVotes;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.privilege.Roles;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.workflow.exception.WorkflowException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Command to be used by Recruits and Supporter.
 * <p>
 * <p> Recruits can use this commands to get their current amount of votes. This does not include negative Votes. </p>
 * <p> Supporters can use this Function to get a full view of any Recruits Votes. This requires the unique Identifier of
 * the Recruit. </p>
 *
 * @author Lukas Peer
 * @version 1.0
 * @since 13.09.2017
 */
public class CVotes implements Command {

    public static final String PREFIX = "votes";
    private static final Logger log = LoggerFactory.getLogger(CVotes.class);
    private TS3Bot ts3Bot;

    private Messaging messaging;
    private PrivilegeApi privilege;
    private AdmittanceWorkflow workflow;

    @Parameter
    private String ts3Id;

    @Autowired
    public CVotes(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent event) throws CommandExecutionException {
        try {
            if (privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Roles.SUPPORTER) && ts3Id != null &&
                    !ts3Id.isEmpty()) {
                Map<String, String> properties = event.getMap();
                properties.put("client_uid", ts3Id);
                RecruitVotes clientVotes = workflow.getVotes(ts3Id);

                properties.put("votes_positive", String.valueOf(clientVotes.getCurrentPositiveVotes()));
                properties.put("votes_negative", String.valueOf(clientVotes.getCurrentNegativeVotes()));
                messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_VOTES_SUPPORT_REPLY, properties);
                return;
            }

            if (privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Roles.RECRUIT)) {
                Map<String, String> properties = event.getMap();
                RecruitVotes clientVotes = workflow.getVotes(event.getInvokerUniqueId());
                properties.put("votes_positive", String.valueOf(clientVotes.getCurrentPositiveVotes()));
                messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_VOTES_RECRUIT_REPLY, properties);
                return;
            }

        } catch (WorkflowException e) {
            //TEMP
        }
    }
}
