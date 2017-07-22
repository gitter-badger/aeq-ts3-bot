package de.esports.aeq.ts3bot.handler;

import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.User;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.UserService;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationAcceptHandler extends CommandHandler {

    public ApplicationAcceptHandler(@Nullable JTS3ServerMod_Interface jts3ServerMod,
                                    @Nullable JTS3ServerQuery jts3ServerQuery) {
        super(jts3ServerMod, jts3ServerQuery);
    }

    public String getName() {
        return "accept";
    }

    public String getHelpText() {
        return "";
    }

    public boolean handleCommand(String msg, HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        // check if service has permissions
        String id = "";
        final User user = UserService.getUserWithTS3Id(id);
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
