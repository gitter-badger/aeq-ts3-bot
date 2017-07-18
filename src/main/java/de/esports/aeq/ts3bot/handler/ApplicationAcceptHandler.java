package de.esports.aeq.ts3bot.handler;

import com.google.common.util.concurrent.FutureCallback;
import de.esports.aeq.ts3bot.application.Application;
import de.esports.aeq.ts3bot.application.ApplicationService;
import de.esports.aeq.ts3bot.service.User;
import de.esports.aeq.ts3bot.service.UserManager;
import de.stefan1200.jts3servermod.interfaces.JTS3ServerMod_Interface;
import de.stefan1200.jts3serverquery.JTS3ServerQuery;
import org.apache.velocity.app.Velocity;
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
                                    @Nullable JTS3ServerQuery jts3ServerQuery, @Nullable String prefix) {
        super(jts3ServerMod, jts3ServerQuery, prefix);
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
        final User user = UserManager.getUserWithTS3Id(id);
        jts3ServerMod.sendMessageToClient("prefix", "chat", 0001, getStartTaskMessage());
        ApplicationService.scheduleAccept(user.getUsername(), new FutureCallback<Application>() {

            @Override
            public void onSuccess(@Nullable Application application) {
                // send message to service
                jts3ServerMod.sendMessageToClient("prefix", "chat", 0001, getSuccessMessage(user.getUsername()));
            }

            @Override
            public void onFailure(Throwable throwable) {
                // send message to service
            }
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
