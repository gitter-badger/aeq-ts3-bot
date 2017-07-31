package de.esports.aeq.ts3bot.command;

import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.permission.CommandPermissionValidator;
import de.esports.aeq.ts3bot.command.permission.CommandPermissions;
import de.esports.aeq.ts3bot.service.ServiceFactory;
import de.esports.aeq.ts3bot.service.api.PermissionService;

public class CommandHelpers {

    /**
     * Validates whether a given command can be executed by the provided {@link CommandExecutionContext}.
     *
     * @param command the command to be executed
     * @param context the {@link CommandExecutionContext} of the command
     * @return true if the check was successful, false otherwise
     */
    public static boolean hasPermission(Command command, CommandExecutionContext context) {
        PermissionService service = ServiceFactory.getServiceFactory(ServiceFactory.MYSQL).getPermissionService();
        CommandPermissions permissions = service.getCommandPermissions(command.getPrefix()).blockingFirst();
        CommandPermissionValidator validator = new CommandPermissionValidator(permissions);
        return validator.match(context.getEventInfo(), context.isFullAdmin(), context.isAdmin());
    }
}
