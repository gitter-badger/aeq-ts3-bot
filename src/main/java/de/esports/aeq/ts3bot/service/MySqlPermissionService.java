package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.command.permission.CPermission;
import de.esports.aeq.ts3bot.service.api.PermissionService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MySqlPermissionService implements PermissionService {

    @Override
    public @NotNull Observable<CPermission> getCommandPermissions(@Nullable String prefix) {
        return Observable.create(emitter -> {
            emitter.onNext(new CPermission(false, null));
            emitter.onComplete();
        });
    }
}
