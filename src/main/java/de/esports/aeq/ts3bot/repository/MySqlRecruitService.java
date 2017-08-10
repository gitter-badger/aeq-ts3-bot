package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.core.api.VoteCount;
import de.esports.aeq.ts3bot.repository.api.RecruitService;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 22.07.2017.
 */
public class MySqlRecruitService implements RecruitService {

    @Override
    public @NotNull Observable<Boolean> addPositiveVote(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Boolean> addPositiveVotes(@Nullable String ts3id, int positiveVotes) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });

    }

    @Override
    public @NotNull Observable<Boolean> addNegativeVote(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<Boolean> addNegativeVotes(@Nullable String ts3id, int negativeVotes) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(true);
            observer.onComplete();
        });
    }

    @Override
    public @NotNull Observable<VoteCount> getVoteCount(@Nullable String ts3id) {
        return Observable.create(observer -> {
            // TODO(glains): implement api call
            observer.onNext(null);
            observer.onComplete();
        });
    }
}
