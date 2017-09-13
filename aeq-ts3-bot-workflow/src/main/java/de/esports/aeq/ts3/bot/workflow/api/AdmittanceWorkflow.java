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

package de.esports.aeq.ts3.bot.workflow.api;

import de.esports.aeq.ts3.bot.model.RecruitVote;
import de.esports.aeq.ts3.bot.model.User;
import de.esports.aeq.ts3.bot.workflow.InvalidUserGroupException;

/**
 * Defines the workflow for new users to become members.
 * <p>
 * This workflow involves the starting point as an applicant and the further trial period as a recruit where the user
 * has to obtain a certain amount of votes to become a member.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 08.09.2017
 */
public interface AdmittanceWorkflow {

    void linkAccount(String clientUniqueId, String accessKey);

    boolean isAccountLinked(String clientUniqueId);

    /**
     * Promotes the target {@link User} that matches the unique teamspeak id to an applicant.
     * <p>
     * During the process, the user will be persisted as an applicant and obtain the reserved server groups.
     *
     * @param clientUniqueId the unique teamspeak id of the user
     * @throws InvalidUserGroupException if the target {@link User} cannot be promoted to an applicant
     */
    void promoteToApplicant(String clientUniqueId) throws InvalidUserGroupException;

    void promoteToRecruit(String clientUniqueId) throws InvalidUserGroupException;

    void addPositiveVote(String clientUniqueId);

    void addNegativeVote(String clientUniqueId);

    RecruitVote getVotes(String clientUniqueId);

    void setVotes(String clientUniqueId, RecruitVote votes);

    boolean hasRecruitExceededTrialPeriod(String clientUniqueId);

    void promoteToMember(String clientUniqueId, boolean skipVotes) throws InvalidUserGroupException;

    void requestSupporterType(String uniqueClientId);

    void promoteToSupporter(String uniqueClientId);

}
