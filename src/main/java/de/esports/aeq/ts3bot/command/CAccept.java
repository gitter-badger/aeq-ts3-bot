package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.Parameter;
import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.command.permission.CPermission;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.User;
import de.esports.aeq.ts3bot.core.api.UserGroups;
import de.esports.aeq.ts3bot.messages.MessageType;
import de.esports.aeq.ts3bot.messages.Messages;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.UserService;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CAccept implements Command {

    public static final String PREFIX = "accept";

    @Parameter()
    private String ts3id;

    public CAccept() { }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(CExecutionContext context) throws CHandleException {
        String id = "";
        final User user = UserService.getUserWithTS3Id(id);

        sendStartTaskMessage(context.getBotInstance(), id);
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(ts3id).subscribe(
                value -> sendSuccessMessage(context.getBotInstance(), user, id),
                error -> sendErrorMessage(context.getBotInstance(), user, id)
        ).dispose();
    }

    public @NotNull String getTs3id() {
        return ts3id;
    }

    public void setTs3id(@NotNull String ts3id) {
        this.ts3id = ts3id;
    }

    private void sendStartTaskMessage(AeQESportsTS3Bot botInstance, String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = botInstance.getJts3ServerMod();
        String startTask = Messages.getRandomTranslatedMessageOfType(MessageType.WAITING);
        jts3ServerMod.sendMessageToClient(botInstance.getPrefix(), "chat", Integer.valueOf(ts3Id), startTask);
    }

    private void sendSuccessMessage(AeQESportsTS3Bot botInstance, User user, String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = botInstance.getJts3ServerMod();
        String onSuccess = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_SUCCESS, user.getUsername());
        jts3ServerMod.sendMessageToClient(botInstance.getPrefix(), "chat", Integer.valueOf(ts3Id), onSuccess);
    }

    private void sendErrorMessage(AeQESportsTS3Bot botInstance, User user, String ts3Id) {
        JTS3ServerMod_Interface jts3ServerMod = botInstance.getJts3ServerMod();
        String onError = Messages.getTranslatedString(Messages.ACCEPT_APPLICATION_ON_ERROR, user.getUsername());
        jts3ServerMod.sendMessageToClient(botInstance.getPrefix(), "chat", Integer.valueOf(ts3Id), onError);
    }
}
