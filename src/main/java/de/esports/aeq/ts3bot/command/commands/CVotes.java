package de.esports.aeq.ts3bot.command.commands;

import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CVotes implements Command {

    public static final String PREFIX = "votes";

    public CVotes() { }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull CommandExecutionContext context) throws CHandleException {
        // TODO(glains)
    }
}