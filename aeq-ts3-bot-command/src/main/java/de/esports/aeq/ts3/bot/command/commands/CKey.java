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
import de.esports.aeq.ts3.bot.messages.Messages;
import de.esports.aeq.ts3.bot.messages.api.Messaging;
import de.esports.aeq.ts3.bot.privilege.Role;
import de.esports.aeq.ts3.bot.privilege.api.Privilege;
import de.esports.aeq.ts3.bot.workflow.api.UserManagement;
import de.esports.aeq.ts3.bot.workflow.exception.InsufficientPermissionException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CKey implements Command {

    private static final String PREFIX = "ts3Id";
    private static final Logger LOG = LoggerFactory.getLogger(CKey.class);

    private UserManagement userManagement;
    private Messaging messaging;
    private Privilege privilege;

    @Parameter(names = {"--ts3Id", "-id"})
    private String ts3Id;

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent event) throws CommandExecutionException {
        String id = null;
        if (ts3Id != null && !ts3Id.isEmpty()) {
            if (!privilege.hasRequiredPrivileges(event.getInvokerUniqueId(), Role.CAO)) {
                messaging.fetchAndSendMessage(event.getInvokerId(), Messages.COMMAND_INVALID_PERMISSIONS, event
                        .getMap());
                return;
            }
            id = ts3Id;
        } else {
            id = event.getInvokerUniqueId();
        }
        try {
            String key = userManagement.getAccessKey(id);
            Map<String, String> properties = event.getMap();
            properties.put("access_key", key);
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_KEY_ACCESS_KEY, properties);
        } catch (UserNotFoundException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_KEY_USER_NOT_FOUND);
        } catch (InsufficientPermissionException e) {
            messaging.fetchAndSendMessage(event.getInvokerId(), Messages.C_KEY_INVALID_PERMISSIONS);
        }
    }
}
