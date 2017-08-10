/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.esports.aeq.ts3bot.command;

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandHandler;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.command.permission.CommandPermissionValidator;
import de.esports.aeq.ts3bot.command.permission.UnrestrictedPermissionValidator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCommandHandler extends TS3EventAdapter implements CommandHandler {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private CommandPermissionValidator validator = new UnrestrictedPermissionValidator();

    public AsyncCommandHandler() {

    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
    }

    @Override
    public void handle(@NotNull Command command, TextMessageEvent context) {
        executor.execute(() -> {
            if (!validator.match(command, context)) {
                //String errorMessage = Messages.getTranslatedString(Messages.ERROR_INVALID_PERMISSIONS);
                // TODO(glains): send message to client
                return;
            }
            try {
                command.execute(context);
            } catch (CHandleException e) {
                //String errorMessage = Messages.getTranslatedString(Messages.ERROR_COMMAND_EXCEPTION);
                // TODO(glains): send message to client
            }
        });
    }
}
