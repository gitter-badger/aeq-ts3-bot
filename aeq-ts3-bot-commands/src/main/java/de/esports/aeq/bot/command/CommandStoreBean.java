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

package de.esports.aeq.bot.command;

import de.esports.aeq.bot.command.api.Command;
import de.esports.aeq.bot.command.exception.UnregisteredCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores
 *
 * @author Lukas Kannenberg
 */
@Component
public class CommandStoreBean {

    private static final Logger LOG = LoggerFactory.getLogger(CommandStoreBean.class);

    private ApplicationContext context;
    private HashMap<String, Command> commandHashMap = new HashMap<>();

    @Autowired
    public CommandStoreBean(ApplicationContext context) {
        this.context = context;
        initializeStore();
    }

    private void initializeStore() {
        LOG.debug("Initializing command store");
        commandHashMap = (HashMap<String, Command>) context.getBeansOfType(Command.class);
        if (LOG.isDebugEnabled()) {
            for (Map.Entry<String, Command> command : commandHashMap.entrySet()) {
                LOG.debug("Registered command {} with maper class {}", command.getKey(), command.getValue().getClass
                        ().getName());
            }
        }
    }

    public Command getCommand(String name) throws UnregisteredCommandException {
        Command command = commandHashMap.get(name);
        if (command == null) {
            throw new UnregisteredCommandException(name);
        }
        return command;
    }

}
