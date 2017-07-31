package de.esports.aeq.ts3bot.service.api;

import de.esports.aeq.ts3bot.core.api.VoteCount;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a general interface for recruit service calls.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public interface RecruitService {

    @NotNull
    public Observable<Boolean> addPositiveVote(@Nullable String ts3id);

    @NotNull
    public Observable<Boolean> addPositiveVotes(@Nullable String ts3id, int positiveVotes);

    @NotNull
    public Observable<Boolean> addNegativeVote(@Nullable String ts3id);

    @NotNull
    public Observable<Boolean> addNegativeVotes(@Nullable String ts3id, int negativeVotes);

    @NotNull
    public Observable<VoteCount> getVoteCount(@Nullable String ts3id);

}