package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandHandler;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.command.permission.CPermissionValidator;
import de.esports.aeq.ts3bot.messages.Messages;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCommandHandler implements CommandHandler {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public AsyncCommandHandler() {

    }

    @Override
    public void handle(Command command, CExecutionContext context) {
        executor.execute(() -> {
            boolean hasPermission = new CPermissionValidator(command.getPermissions()).match(context.getEventInfo(),
                    context.isFullAdmin(), context.isAdmin());
            if (!hasPermission) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_INVALID_PERMISSIONS);
                context.getBotInstance().getJts3ServerMod().sendMessageToClient(context.getBotInstance().getPrefix(),
                        "chat", 0001, errorMessage);
                return;
            }
            try {
                command.execute(context);
            } catch (CHandleException e) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_COMMAND_EXCEPTION);
                context.getBotInstance().getJts3ServerMod().sendMessageToClient(context.getBotInstance().getPrefix(),
                        "chat", 0001, errorMessage);
                return;
            }
        });
    }
}
