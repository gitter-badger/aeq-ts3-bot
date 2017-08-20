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

import de.esports.aeq.ts3bot.repository.JdbcMessageDAO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Lukas Kannenberg
 */
@Component
public class DefaultMessageProvider implements MessageProvider {

    private ApplicationContext context;

    @Autowired
    public DefaultMessageProvider(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public @Nullable String getMessage(@NotNull String id, @NotNull Locale locale) {
        JdbcMessageDAO dao = context.getBean(JdbcMessageDAO.class);
        List<Message> messages = dao.getMessages(id, locale.getLanguage());
        if (messages != null) {
            int rnd = ThreadLocalRandom.current().nextInt(0, messages.size() + 1);
            Message message = messages.get(rnd);
        }
        return null;
    }
}
