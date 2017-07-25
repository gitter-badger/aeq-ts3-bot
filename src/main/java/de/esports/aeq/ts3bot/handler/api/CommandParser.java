package de.esports.aeq.ts3bot.handler.api;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.esports.aeq.ts3bot.handler.CommandHelpers;

/**
 * Created by Lukas on 25.07.2017.
 */
public class CommandParser<T> {

    public void parseCommand(String message, T command) throws ParameterException {
        String[] array = CommandHelpers.argsFromString(message);
        JCommander.newBuilder().addObject(command).build().parse(array);
    }

}
