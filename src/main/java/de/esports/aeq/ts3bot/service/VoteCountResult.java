package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.RecruitService;

/**
 * Represents the result of a {@link RecruitService#getVoteCount}.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public class VoteCountResult {

    public int positiveVotes;

    public int negativeVotes;

    public VoteCountResult(int positiveVotes, int negativeVotes) {
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

}
