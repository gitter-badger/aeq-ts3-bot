package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CommandParsingException;
import de.esports.aeq.ts3bot.command.exceptions.InvalidPrefixException;
import de.esports.aeq.ts3bot.command.exceptions.UnregisteredCommandException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lukas on 25.07.2017.
 */
public class CommandParser {

    private static HashMap<String, Class<? extends Command>> commands = new HashMap<>();

    /**
     * TODO(glains): we should move this to a config file. Maybe add CommandList to constructor!
     */
    static {
        commands.put(CAccept.PREFIX, CAccept.class);
    }

    private String message;

    public CommandParser(String message) {
        this.message = message;
    }

    public @NotNull Command parse() throws CommandParsingException {
        String[] args = argsFromString(message);
        String prefix = args.length != 0 ? args[0] : null;
        if (prefix == null) {
            throw new InvalidPrefixException();
        }
        Class<? extends Command> clazz = commands.get(prefix);
        if (clazz == null) {
            throw new UnregisteredCommandException(prefix);
        }
        Command command = null;
        try {
            command = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CommandParsingException(e);
        }

        try {
            new JCommander.Builder().addObject(command).build().parse(args);
        } catch (ParameterException e) {
            throw new CommandParsingException(e);
        }
        command.setPrefix(prefix);
        return command;
    }

    /**
     * Assigns the given permissions to the specified command.
     *
     * @param command
     */
    private void assignPermissions(Command command) {

    }

    private String[] argsFromString(String string) {
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find())
            list.add(m.group(1).replace("\"", ""));
        return list.toArray(new String[0]);
    }
}
