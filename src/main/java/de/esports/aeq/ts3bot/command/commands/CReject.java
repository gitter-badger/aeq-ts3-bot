package de.esports.aeq.ts3bot.command.commands;

import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CReject implements Command {

    public static final String PREFIX = "reject";

    public CReject() { }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull CommandExecutionContext context) throws CHandleException {
        // TODO(glains)
    }
}
