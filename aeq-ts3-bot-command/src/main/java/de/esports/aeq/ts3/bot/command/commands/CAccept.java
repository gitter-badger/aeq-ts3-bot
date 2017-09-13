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
import de.aeq.esports.ts3.bot.worflow.InvalidUserGroupException;
import de.aeq.esports.ts3.bot.worflow.api.AdmittanceWorkflow;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Lukas on 27.07.2017.
 */
@Component
@Scope("prototype")
public class CAccept implements Command {

    private static final Logger LOG = LoggerFactory.getLogger(CAccept.class);
    private static final String PREFIX = "accept";

    private Messaging messaging;
    private AdmittanceWorkflow workflow;

    @Parameter()
    private String ts3id;

    @Parameter(names = {"-force"})
    private boolean force = false;

    @Autowired
    public CAccept(Messaging messaging, AdmittanceWorkflow workflow) {
        this.messaging = messaging;
        this.workflow = workflow;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull TextMessageEvent event) throws CommandExecutionException {
        try {
            if (force) {
                workflow.promoteToMember(ts3id, true);
            } else {
                workflow.promoteToMember(ts3id, false);
            }
        } catch (InvalidUserGroupException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.PROMOTE_TO_MEMBER_INVALID_USER_GROUP, event
                    .getMap());
        }
    }

    public String getTs3id() {
        return ts3id;
    }

    public void setTs3id(String ts3id) {
        this.ts3id = ts3id;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
