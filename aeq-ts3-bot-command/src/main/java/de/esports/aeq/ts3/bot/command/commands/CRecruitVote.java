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
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.privilege.Roles;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.workflow.api.AdmittanceWorkflow;
import de.esports.aeq.ts3.bot.workflow.exception.WorkflowException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CRecruitVote implements Command {

    public static final String PREFIX = "vote";
    private static final Logger log = LoggerFactory.getLogger(CRecruitVote.class);
    private TS3Bot ts3Bot;

    private Messaging messaging;
    private PrivilegeApi privilege;
    private AdmittanceWorkflow workflow;


    @Parameter(names = {"--vote", "-v"})
    private String voteResult;

    @Parameter(names = {"--ts3Id", "-id", "-u"})
    private String userUID;

    @Parameter(names = {"--comment", "-c"})
    private String comment;

    @Autowired
    public CRecruitVote(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent event) throws CommandExecutionException {
        try {
            //Checking if user is Member or higher to vote
            if (privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Roles.MEMBER)) {
                //Checking if parameter were set correctly
                if (voteResult == null || voteResult.isEmpty() || !voteResult.equalsIgnoreCase("yes") || !voteResult
                        .equalsIgnoreCase("no")) {
                    //TODO Send user message that vote is not correct
                    return;
                }

                //Check if uid is correct and user is Recruit
                if (userUID == null || userUID.isEmpty() || privilege.isMemberOfRole(userUID, Roles.RECRUIT)) {
                    //TODO Send user message that user is no recruit or formatiing of uid is wrong
                    return;
                }

                Boolean vote = voteResult.equalsIgnoreCase("yes");
                if (vote) {
                    workflow.addPositiveVote(userUID);
                    //TODO Send user message that vote was successfull
                    return;
                } else {
                    if (comment == null || comment.isEmpty()) {
                        //TODO Send user message that comment is [ENG: nötig]
                        return;
                    }
                    workflow.addNegativeVote(userUID, comment);
                }
            }

        } catch (WorkflowException e) {
            //Send user a error
        }

    }
}
