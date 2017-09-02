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

package de.esports.aeq.bot.command.commands;

import com.beust.jcommander.Parameter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.bot.command.api.Command;
import de.esports.aeq.bot.command.exception.CommandExecutionException;
import de.esports.aeq.ts3.bot.model.TS3Bot;
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
public class CAccept implements Command {

    private static final Logger log = LoggerFactory.getLogger(CAccept.class);
    private static final String PREFIX = "accept";

    private TS3Bot ts3Bot;

    @Parameter()
    @SuppressWarnings("unused")
    private String ts3id;

    public CAccept(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent e) throws CommandExecutionException {
        log.debug("executing command {}", CAccept.class.getSimpleName());
        //String message = Messages.getTranslatedString(Messages.ERROR_NOT_IMPLEMENTED);
        //ts3Bot.getApi().sendPrivateMessage(e.getInvokerId(), message);
        /*
        sendStartTaskMessage(e);
        ApplicationService repository = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        repository.acceptApplication(ts3id).subscribe(
                value -> sendSuccessMessage(e),
                error -> sendErrorMessage(e)
        );
        */
    }

    private void sendStartTaskMessage(TextMessageEvent e) {
        //String message = Messages.getRandomTranslatedMessageOfType(MessageType.WAITING); // TODO(glains): params
        //ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }

    private void sendSuccessMessage(TextMessageEvent e) {
        //String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_SUCCESS); // TODO(glains): params
        //ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }

    private void sendErrorMessage(TextMessageEvent e) {
        //String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_ERROR); // TODO(glains): params
        //ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }
}
