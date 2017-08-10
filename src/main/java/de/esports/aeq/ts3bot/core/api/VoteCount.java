package de.esports.aeq.ts3bot.core.api;

import de.esports.aeq.ts3bot.repository.api.RecruitService;

/**
 * Represents the result of a {@link RecruitService#getVoteCount}.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public class VoteCount {

    public int positiveVotes;

    public int negativeVotes;

    public VoteCount(int positiveVotes, int negativeVotes) {
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
