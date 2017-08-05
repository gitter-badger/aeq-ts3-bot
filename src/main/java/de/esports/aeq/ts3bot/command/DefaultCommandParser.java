package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandParser;
import de.esports.aeq.ts3bot.command.commands.*;
import de.esports.aeq.ts3bot.command.exception.CommandParsingException;
import de.esports.aeq.ts3bot.command.exception.InvalidPrefixException;
import de.esports.aeq.ts3bot.command.exception.UnregisteredCommandException;
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
    private static final Logger log = LoggerFactory.getLogger(DefaultCommandParser.class);
    private static HashMap<String, Class<? extends Command>> commands = new HashMap<>();

    /**
     * TODO(glains): we should move this to a config file. Maybe add CommandList to constructor!
     */
    static {
        commands.put(CAccept.PREFIX, CAccept.class);
        commands.put(CKey.PREFIX, CKey.class);
        commands.put(CLink.PREFIX, CLink.class);
        commands.put(CRecruitVote.PREFIX, CRecruitVote.class);
        commands.put(CReject.PREFIX, CReject.class);
        commands.put(CVotes.PREFIX, CVotes.class);
    }

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
        message = message.substring(1);
        String[] args = argsFromString(message);
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
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find())
            list.add(m.group(1).replace("\"", ""));
        return list.toArray(new String[0]);
    }
}
