package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.CExecutionContext;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.permission.CPermission;
import de.esports.aeq.ts3bot.command.permission.CPermissionValidator;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.api.PermissionService;

public class CommandHelpers {

    /**
     * Validates whether a given command can be executed by the provided {@link CExecutionContext}.
     *
     * @param command the command to be executed
     * @param context the {@link CExecutionContext} of the command
     * @return true if the check was successful, false otherwise
     */
    public static boolean hasPermission(Command command, CExecutionContext context) {
        PermissionService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getPermissionService();
        CPermission permissions = service.getCommandPermissions(command.getPrefix()).blockingFirst();
        CPermissionValidator validator = new CPermissionValidator(permissions);
        return validator.match(context.getEventInfo(), context.isFullAdmin(), context.isAdmin());
    }

}
