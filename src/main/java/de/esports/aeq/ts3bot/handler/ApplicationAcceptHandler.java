package de.esports.aeq.ts3bot.handler;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.User;
import de.esports.aeq.ts3bot.handler.api.TS3CommandHandler;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.UserService;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationAcceptHandler extends TS3CommandHandler {

    private ApplicationAcceptCommand command;

    public ApplicationAcceptHandler(@NotNull AeQESportsTS3Bot ts3Bot) {
        super(ts3Bot);
        ts3Bot.addCommandHandler("accept", this);
    }

    public void parseCommand(String command) throws ParameterException {
        String[] array = CommandHelpers.argsFromString(command);
        JCommander.newBuilder().addObject(command).build().parse(array);
    }

    @Override
    public String getHelpText() {
        return "";
    }

    @Override
    public boolean canExecute(HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        return true;
    }

    public boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        try {
            parseCommand(msg);
        } catch (ParameterException e) {
            // send usage to user
            return true;
        }

        // check if service has permissions
        String id = "";
        final User user = UserService.getUserWithTS3Id(id);

        JTS3ServerMod_Interface jts3ServerMod = ts3Bot.getJts3ServerMod();
        jts3ServerMod.sendMessageToClient("prefix", "chat", 0001, getStartTaskMessage());
        ApplicationService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getApplicationService();
        service.acceptApplication(user.getUsername()).subscribe((value) -> {
            jts3ServerMod.sendMessageToClient("prefix", "chat", 0001, getSuccessMessage(user.getUsername()));
        }, error -> {
            jts3ServerMod.sendMessageToClient("prefix", "chat", 0001, getSuccessMessage(user.getUsername()));
        });
        return true;
    }

    private String getStartTaskMessage() {
        return "Hang on for just a moment, trying to call the remote server...";
    }

    private String getSuccessMessage(String user) {
        String template = "{1} has been approved and switched to recruit status, voting is now active.";
        return MessageFormat.format(template, user);
    }

}
