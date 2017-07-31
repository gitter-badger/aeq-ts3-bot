package de.esports.aeq.ts3bot.service.api;

import de.esports.aeq.ts3bot.command.permission.CommandPermissions;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PermissionService {

    @NotNull
    Observable<CommandPermissions> getCommandPermissions(@Nullable String prefix);
}
