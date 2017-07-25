package de.esports.aeq.ts3bot.handler;

import de.esports.aeq.ts3bot.command.ApplicationAcceptCommand;
import de.esports.aeq.ts3bot.command.MessageHandler;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.User;
import de.esports.aeq.ts3bot.messages.MessageType;
import de.esports.aeq.ts3bot.messages.Messages;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.UserService;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationAcceptHandler extends MessageHandler<ApplicationAcceptCommand> {

    public static final String PREFIX = "accept";

    public ApplicationAcceptHandler(AeQESportsTS3Bot ts3Bot) {
        super(ts3Bot, PREFIX);
    }

    @Override
    protected void onSuccessfulPermissionCheck(@NotNull ApplicationAcceptCommand command, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) {
        JTS3ServerMod_Interface jts3ServerMod = ts3Bot.getJts3ServerMod();
        String prefix = ts3Bot.getPrefix();
        String id = "";
        final User user = UserService.getUserWithTS3Id(id);

        sendStartTaskMessage(id);
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(command.getTs3id()).subscribe((value) -> {
            sendSuccessMessage(user, id);
        }, error -> {
            sendErrorMessage(user, id);
        });
    }

    private void sendStartTaskMessage(String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = ts3Bot.getJts3ServerMod();
        String startTask = Messages.getRandomTranslatedMessageOfType(MessageType.WAITING);
        jts3ServerMod.sendMessageToClient(ts3Bot.getPrefix(), "chat", Integer.valueOf(ts3Id), startTask);
    }

    private void sendSuccessMessage(User user, String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = ts3Bot.getJts3ServerMod();
        String onSuccess = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_SUCCESS, user.getUsername());
        jts3ServerMod.sendMessageToClient(ts3Bot.getPrefix(), "chat", Integer.valueOf(ts3Id), onSuccess);
    }

    private void sendErrorMessage(User user, String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = ts3Bot.getJts3ServerMod();
        String onError = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_ERROR, user.getUsername());
        jts3ServerMod.sendMessageToClient(ts3Bot.getPrefix(), "chat", Integer.valueOf(ts3Id), onError);
    }

}
