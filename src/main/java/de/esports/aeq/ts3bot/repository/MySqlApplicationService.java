package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.core.api.Application;
import de.esports.aeq.ts3bot.repository.api.ApplicationService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlApplicationService implements ApplicationService {

    @Override
    public @NotNull Observable<Application> acceptApplication(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(null);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Application> rejectApplication(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(null);
            observer.onComplete();
        });
    }
}
