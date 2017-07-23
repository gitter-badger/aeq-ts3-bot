package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.RecruitService;
import io.reactivex.Observable;

/**
 * Created by Lukas on 22.07.2017.
 */
public class MySqlRecruitService implements RecruitService {

    @Override
    public Observable<Boolean> addPositiveVote(String ts3id) {
        return null;
    }

    @Override
    public Observable<Boolean> addPositiveVotes(String ts3id, int positiveVotes) {
        return null;
    }

    @Override
    public Observable<Boolean> addNegativeVote(String ts3id) {
        return null;
    }

    @Override
    public Observable<Boolean> addNegativeVotes(String ts3id, int negativeVotes) {
        return null;
    }

    @Override
    public Observable<VoteCountResult> getVoteCount(String ts3id) {
        return null;
    }

}
