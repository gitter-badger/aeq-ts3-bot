package de.esports.aeq.ts3bot.command.commands;

import com.beust.jcommander.Parameter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
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

    private AeqTS3Bot ts3Bot;

    @Parameter()
    @SuppressWarnings("unused")
    private String ts3id;

    public CAccept(AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent e) throws CHandleException {
        sendStartTaskMessage(e);
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(ts3id).subscribe(
                value -> sendSuccessMessage(e),
                error -> sendErrorMessage(e)
        );
    }

    private void sendStartTaskMessage(TextMessageEvent e) {
        String message = Messages.getRandomTranslatedMessageOfType(MessageType.WAITING); // TODO(glains): params
        ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }

    private void sendSuccessMessage(TextMessageEvent e) {
        String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_SUCCESS); // TODO(glains): params
        ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }

    private void sendErrorMessage(TextMessageEvent e) {
        String message = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_ERROR); // TODO(glains): params
        ts3Bot.getApiAsync().sendPrivateMessage(e.getInvokerId(), message);
    }
}
