package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CLink implements Command {

    public static final String PREFIX = "link";

    public CLink(String prefix) { }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(@NotNull CExecutionContext context) throws CHandleException {
        // TODO(glains)
    }
}
