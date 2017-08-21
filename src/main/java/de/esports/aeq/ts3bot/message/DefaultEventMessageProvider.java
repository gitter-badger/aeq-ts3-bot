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

package de.esports.aeq.ts3bot.message;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import de.esports.aeq.ts3bot.message.api.EventMessageFilter;
import de.esports.aeq.ts3bot.message.api.EventMessageProvider;
import de.esports.aeq.ts3bot.repository.JdbcMessageDAO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Default implementation of the {@link EventMessageFormatter}.
 * <p>
 * Searches for message candidates in the database and then returns the first one that matches the related permissions.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 20.01.2017
 */
@Component
public class DefaultEventMessageProvider implements EventMessageProvider {

    private ApplicationContext context;

    @Autowired
    public DefaultEventMessageProvider(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public @Nullable Message getMessage(@NotNull String id, @NotNull Locale locale, @NotNull BaseEvent event) {
        List<Message> messages = getCandidates(id, locale);
        if (messages != null) {
            // Shuffle to switch between messages
            Collections.shuffle(messages);
            Optional<Message> match = messages.stream()
                    .filter((Message m) -> EventMessageFilter.filter(m.getFilters(), m, event)).findFirst();
            if (match.isPresent())
                return match.get();
        }
        return null;
    }

    /**
     * Returns a list of message candidates.
     *
     * @param id     the id of the message
     * @param locale the locale of the message
     * @return a {@link List} of message candidates
     */
    private List<Message> getCandidates(@NotNull String id, @NotNull Locale locale) {
        JdbcMessageDAO dao = context.getBean(JdbcMessageDAO.class);
        return dao.getMessages(id, locale.getLanguage());
    }

}
