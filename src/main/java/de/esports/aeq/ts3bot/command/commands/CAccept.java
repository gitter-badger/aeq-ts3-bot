package de.esports.aeq.ts3bot.command.commands;

import com.beust.jcommander.Parameter;
import de.esports.aeq.ts3bot.command.CommandExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.message.MessageType;
import de.esports.aeq.ts3bot.message.Messages;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CAccept implements Command {

    public static final String PREFIX = "accept";

    @Parameter()
    private String ts3id;

    public CAccept() {
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(CommandExecutionContext context) throws CHandleException {
        sendStartTaskMessage(context);
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(ts3id).subscribe(
                value -> sendSuccessMessage(context),
                error -> sendErrorMessage(context)
        );
    }

    private void sendStartTaskMessage(CommandExecutionContext context) {
        String message = Messages.getRandomTranslatedMessageOfType(MessageType.WAITING); // TODO(glains): params
        int clientId = context.getMessageEvent().getInvokerId();
        context.getApiAsync().sendPrivateMessage(clientId, message);
    }

    private void sendSuccessMessage(CommandExecutionContext context) {
        String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_SUCCESS); // TODO(glains): params
        int clientId = context.getMessageEvent().getInvokerId();
        context.getApiAsync().sendPrivateMessage(clientId, message);
    }

    private void sendErrorMessage(CommandExecutionContext context) {
        String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_ERROR); // TODO(glains): params
        int clientId = context.getMessageEvent().getInvokerId();
        context.getApiAsync().sendPrivateMessage(clientId, message);
    }

    public @NotNull String getTs3id() {
        return ts3id;
    }

    public void setTs3id(@NotNull String ts3id) {
        this.ts3id = ts3id;
    }
}
