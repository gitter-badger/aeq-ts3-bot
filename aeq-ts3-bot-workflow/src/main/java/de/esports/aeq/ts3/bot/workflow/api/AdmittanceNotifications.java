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

/**
 * @author Lukas Kannenberg
 */
public interface AdmittanceNotifications {

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
