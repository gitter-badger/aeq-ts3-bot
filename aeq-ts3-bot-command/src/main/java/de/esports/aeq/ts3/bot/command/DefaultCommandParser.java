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

package de.esports.aeq.ts3.bot.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.esports.aeq.ts3.bot.command.api.Command;
import de.esports.aeq.ts3.bot.command.api.CommandParser;
import de.esports.aeq.ts3.bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3.bot.command.exception.InvalidPrefixException;
import de.esports.aeq.ts3.bot.command.exception.UnregisteredCommandException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lukas on 25.07.2017.
 */
@Component
public class DefaultCommandParser implements CommandParser {

    public static final String COMMAND_PREFIX = "!";
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCommandParser.class);
    private static HashMap<String, Class<? extends Command>> commands = new HashMap<>();

    private ApplicationContext context;

    @Autowired
    public DefaultCommandParser(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public @NotNull Command parse(@NotNull String message) throws CommandParsingException {
        if (!message.startsWith(COMMAND_PREFIX)) {
            throw new InvalidPrefixException();
        }
        if (message.length() < 2) {
            throw new CommandParsingException();
        }
        String[] args = argsFromString(message.substring(1));
        String name = args.length != 0 ? args[0] : null;
        Class<? extends Command> clazz = commands.get(name);
        if (clazz == null) {
            throw new UnregisteredCommandException(name);
        }
        args = Arrays.copyOfRange(args, 1, args.length);
        Command command = context.getBean(clazz);
        try {
            new JCommander.Builder().addObject(command).build().parse(args);
        } catch (ParameterException e) {
            throw new CommandParsingException(e);
        }
        return command;
    }

    private String[] argsFromString(String string) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find())
            list.add(m.group(1).replace("\"", ""));
        return list.toArray(new String[0]);
    }
}
