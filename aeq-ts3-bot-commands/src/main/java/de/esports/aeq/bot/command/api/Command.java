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

package de.esports.aeq.bot.command.api;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.bot.command.CommandStoreBean;
import de.esports.aeq.bot.command.exception.CommandExecutionException;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a parsable command from user input.
 * <p>
 * <b>All commands must implement this interface to be detected by the {@link CommandStoreBean}.</b>
 *
 * @author Lukas Kannenberg
 * @since 25.07.2017
 */
public interface Command {

    /**
     * Returns the unique prefix that identifies this command.
     * <p>
     * The prefix will be used to identify messages that should be mapped to this {@link Command}.
     *
     * @return the prefix that identifies to this command
     */
    @NotNull
    String getPrefix();

    /**
     * Executes this command with the given {@link TextMessageEvent}.
     *
     * @param event the {@link TextMessageEvent} of this command
     * @throws CommandExecutionException if an error occurs during the execution process
     */
    void execute(@NotNull TextMessageEvent event) throws CommandExecutionException;
}
