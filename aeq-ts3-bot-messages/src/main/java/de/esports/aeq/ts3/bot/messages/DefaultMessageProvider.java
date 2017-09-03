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

package de.esports.aeq.ts3.bot.messages;

import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import de.esports.aeq.ts3.bot.dataprovider.api.MessageRepository;
import de.esports.aeq.ts3.bot.messages.api.MessageProvider;
import de.esports.aeq.ts3.bot.model.message.Message;
import de.esports.aeq.ts3.bot.model.message.MessageFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DefaultMessageProvider implements MessageProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageProvider.class);

    private ApplicationContext context;

    @Autowired
    public DefaultMessageProvider(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public @Nullable Message getMessage(@NotNull String context, @NotNull Locale locale, @NotNull BaseEvent event) {
        LOG.debug("Fetching message for context {} and locale {}", context, locale.getLanguage());
        MessageRepository repository = this.context.getBean(MessageRepository.class);
        List<Message> messages = repository.findByContextAndLocale(context, locale);
        LOG.debug("found {} candidates for context {} and locale {}", messages.size(), context, locale.getLanguage());
        // Shuffle to switch between messages
        Collections.shuffle(messages);
        Optional<Message> match = messages.stream().filter((Message m) -> MessageFilter.filter(m.getFilters()
                , m, event)).findFirst();
        return match.orElse(null);
    }

}
