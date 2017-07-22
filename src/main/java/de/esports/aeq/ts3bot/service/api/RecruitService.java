package de.esports.aeq.ts3bot.service.api;

import de.esports.aeq.ts3bot.service.VoteCountResult;
import io.reactivex.Observable;

/**
 * Represents a general interface for recruit service calls.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public interface RecruitService {

    public Observable<Boolean> addPositiveVote(String ts3id);

    public Observable<Boolean> addPositiveVotes(String ts3id, int positiveVotes);

    public Observable<Boolean> addNegativeVote(String ts3id);

    public Observable<Boolean> addNegativeVotes(String ts3id, int negativeVotes);

    public Observable<VoteCountResult> getVoteCount(String ts3id);

}