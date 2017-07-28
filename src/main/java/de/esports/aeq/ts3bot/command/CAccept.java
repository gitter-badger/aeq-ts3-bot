package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.Parameter;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exceptions.CHandleException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.User;
import de.esports.aeq.ts3bot.messages.MessageType;
import de.esports.aeq.ts3bot.messages.Messages;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.UserService;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CAccept extends Command {

    public static final String PREFIX = "accept";

    @Parameter()
    private String ts3id;

    public CAccept() {
        super(PREFIX);
    }

    public @NotNull String getTs3id() {
        return ts3id;
    }

    public void setTs3id(@NotNull String ts3id) {
        this.ts3id = ts3id;
    }

    @Override
    public void handle(AeQESportsTS3Bot botInstance, HashMap eventInfo, boolean isFullAdmin, boolean isAdmin) throws CHandleException {
        JTS3ServerMod_Interface jts3ServerMod = botInstance.getJts3ServerMod();
        String prefix = botInstance.getPrefix();
        String id = "";
        final User user = UserService.getUserWithTS3Id(id);

        sendStartTaskMessage(botInstance, id);
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(ts3id).subscribe((value) -> {
            sendSuccessMessage(botInstance, user, id);
        }, error -> {
            sendErrorMessage(botInstance, user, id);
        });
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
