package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.AccountService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlAccountService implements AccountService {

    @Override
    public @NotNull Observable<String> getAccessKey(@Nullable String ts3Id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(null);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Boolean> linkAccessKey(@Nullable String ts3Id, @Nullable String key) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }
}
