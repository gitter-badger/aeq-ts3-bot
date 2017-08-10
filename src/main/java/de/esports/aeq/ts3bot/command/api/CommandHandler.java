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

package de.esports.aeq.ts3bot.command.api;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 27.07.2017.
 */
@FunctionalInterface
public interface CommandHandler {

    /**
     * Handles a given {@link Command} with the provided {@link TextMessageEvent}
     *
     * @param command the {@link Command} to be handled
     * @param context the provided {@link TextMessageEvent}
     */
    void handle(@NotNull Command command, TextMessageEvent context);
}
