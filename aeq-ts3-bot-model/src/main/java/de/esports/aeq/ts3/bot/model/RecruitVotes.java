/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.model;

import java.util.List;

/**
 * @author Lukas Kannenberg
 */
public class RecruitVotes {

    private int currentPositiveVotes;
    private int currentNegativeVotes;
    private int requiredVotes;
    private List<RecruitVoteComment> comments;

    public RecruitVotes(int currentPositiveVotes, int currentNegativeVotes, int requiredVotes,
                        List<RecruitVoteComment> comments) {
        this.currentPositiveVotes = currentPositiveVotes;
        this.currentNegativeVotes = currentNegativeVotes;
        this.requiredVotes = requiredVotes;
        this.comments = comments;
    }

    public int getCurrentPositiveVotes() {
        return currentPositiveVotes;
    }

    public void setCurrentPositiveVotes(int currentPositiveVotes) {
        this.currentPositiveVotes = currentPositiveVotes;
    }

    public int getCurrentNegativeVotes() {
        return currentNegativeVotes;
    }

    public void setCurrentNegativeVotes(int currentNegativeVotes) {
        this.currentNegativeVotes = currentNegativeVotes;
    }

    public int getRequiredVotes() {
        return requiredVotes;
    }

    public void setRequiredVotes(int requiredVotes) {
        this.requiredVotes = requiredVotes;
    }

    public List<RecruitVoteComment> getComments() {
        return comments;
    }

    public void setComments(List<RecruitVoteComment> comments) {
        this.comments = comments;
    }
}
