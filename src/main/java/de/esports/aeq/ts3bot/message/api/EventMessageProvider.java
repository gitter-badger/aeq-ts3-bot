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

package de.esports.aeq.ts3bot.message.api;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import de.esports.aeq.ts3bot.message.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Provides messages the are used in this application.
 * <p>
 * Messages are usually stored within a database instead of a resource bundle to make them easily changeable with a
 * service.
 *
 * @author Lukas Kannenberg
 * @version 0.2
 * @since 20.08.2017
 */
public interface EventMessageProvider {

    /**
     * Retrieves a {@link Message} that matches the given id and locale.
     * <p>
     * If multiple matches are found, only one of the messages will be returned while the selection progress is up to
     * the implementation.
     *
     * @param id     the message id
     * @param locale the locale of the message
     * @param event  the related event
     * @return the {@link Message} or null if no match has been found
     */
    @Nullable
    Message getMessage(@NotNull String id, @NotNull Locale locale, @NotNull BaseEvent event);
}
