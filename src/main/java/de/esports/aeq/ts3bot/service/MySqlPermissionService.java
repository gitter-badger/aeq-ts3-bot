package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.command.permission.CommandPermissions;
import de.esports.aeq.ts3bot.service.api.PermissionService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MySqlPermissionService implements PermissionService {

    @Override
    public @NotNull Observable<CommandPermissions> getCommandPermissions(@Nullable String prefix) {
        return Observable.create(emitter -> {
            emitter.onNext(new CommandPermissions(false, null));
            emitter.onComplete();
        });
    }
}
