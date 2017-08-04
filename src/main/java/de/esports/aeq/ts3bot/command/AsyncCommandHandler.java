package de.esports.aeq.ts3bot.command;

import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.api.CommandHandler;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.command.permission.CommandPermissionValidator;
import de.esports.aeq.ts3bot.command.permission.UnrestrictedPermissionValidator;
import de.esports.aeq.ts3bot.message.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCommandHandler extends TS3EventAdapter implements CommandHandler {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private CommandPermissionValidator validator = new UnrestrictedPermissionValidator();

    public AsyncCommandHandler() {

    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
    }

    @Override
    public void handle(@NotNull Command command, TextMessageEvent context) {
        executor.execute(() -> {
            if (!validator.match(command, context)) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_INVALID_PERMISSIONS);
                // TODO(glains): send message to client
                return;
            }
            try {
                command.execute(context);
            } catch (CHandleException e) {
                String errorMessage = Messages.getTranslatedString(Messages.ERROR_COMMAND_EXCEPTION);
                // TODO(glains): send message to client
            }
        });
    }
}
