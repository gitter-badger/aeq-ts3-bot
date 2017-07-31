package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
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
    public void execute(@NotNull CExecutionContext context) throws CHandleException {
        // TODO(glains)
    }
}
