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

package de.esports.aeq.ts3.bot.admittance.api;

import de.esports.aeq.ts3.bot.admittance.exception.AccountAlreadyLinkedException;
import de.esports.aeq.ts3.bot.model.RecruitVotes;
import de.esports.aeq.ts3.bot.model.User;
import de.esports.aeq.ts3.bot.workflow.exception.InvalidUserGroupException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;

/**
 * Defines the workflow for new users to become members.
 * <p>
 * This workflow involves the starting point as an applicant and the further trial period as a recruit where the user
 * has to obtain a certain amount of votes to become a member.
 *
 * @author Lukas Kannenberg
 * @version 0.2
 * @since 08.09.2017
 */
public interface AdmittanceWorkflow {

    /**
     * Creates a link between a teamspeak identity and a {@link User}.
     * <p>
     * The user to link to will be found by a matching access key. Relinking an already linked teamspeak will replace
     * the stored identity as long as it differs from the old one.
     * <p>
     * <b>Important:</b> A successful link will update the server and channel groups of the user. Relinking to another
     * account will remove all server and channel groups from the old account and transfer that groups to the new
     * account.
     *
     * @param clientUniqueId the unique id of the client
     * @param accessKey      the access key bound to the target {@link User} to create a link to
     * @throws UserNotFoundException         if no {@link User} matching the specified access key can be found
     * @throws AccountAlreadyLinkedException if a link has already been established with the same teamspeak identity
     */
    void linkAccount(String clientUniqueId, String accessKey) throws UserNotFoundException,
            AccountAlreadyLinkedException;

    /**
     * Checks whether a teamspeak identity has been linked to a {@link User}.
     *
     * @param clientUniqueId the unique id of the client
     * @return true if the teamspeak identity has been linked to an existing {@link User}, otherwise false
     */
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

    void addPositiveVote(String clientUniqueId, String comment);

    void addNegativeVote(String clientUniqueId);

    void addNegativeVote(String clientUniqueId, String comment);

    RecruitVotes getVotes(String clientUniqueId);

    void setVotes(String clientUniqueId, RecruitVotes votes);

    boolean hasRecruitExceededTrialPeriod(String clientUniqueId);

    void promoteToMember(String clientUniqueId, boolean skipVotes) throws InvalidUserGroupException;

    void requestSupporterType(String uniqueClientId);

    void promoteToSupporter(String uniqueClientId);

    /**
     * Send a message to the target client to advertise membership.
     * <p>
     * Details are up to the implementation.
     *
     * @param clientId the id of the client to send to message to
     */
    void advertiseMembership(int clientId);

    /**
     * Notifies all clients that match the member recruiter server group about a new applicant.
     *
     * @param clientUniqueId the unique id of the applicant
     */
    void notifyMemberRecruitersAboutApplicant(String clientUniqueId);

    /**
     * Sends a notification to all member recruiters about the current status of all recruits.
     * <p>
     * This involves information about recruits that would shortly exceed their trial period.
     */
    void notifyMemberRecruitersAboutRecruitsStatus();

}
