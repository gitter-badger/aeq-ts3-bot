package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandHandler;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.message.Messages;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCommandHandler implements CommandHandler {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public AsyncCommandHandler() {

    }

    @Override
    public void handle(Command command, CommandExecutionContext context) {
        executor.execute(() -> {
            if (!CommandHelpers.hasPermission(command, context)) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_INVALID_PERMISSIONS);
                // TODO(glains): send message to client
                return;
            }
            try {
                command.execute(context);
            } catch (CHandleException e) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_COMMAND_EXCEPTION);
                // TODO(glains): send message to client
                return;
            }
        });
    }
}
