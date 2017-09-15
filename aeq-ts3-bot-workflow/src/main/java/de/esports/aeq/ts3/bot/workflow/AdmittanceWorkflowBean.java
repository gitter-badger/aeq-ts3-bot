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

package de.esports.aeq.ts3.bot.workflow;

import de.esports.aeq.ts3.bot.model.RecruitVote;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.workflow.api.AdmittanceWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 08.09.2017
 */
@Component
@Primary
public class AdmittanceWorkflowBean implements AdmittanceWorkflow {

    private TS3Bot ts3Bot;

    @Autowired
    public AdmittanceWorkflowBean(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void linkAccount(String clientUniqueId, String accessKey) {

    }

    @Override
    public boolean isAccountLinked(String clientUniqueId) {
        return false;
    }

    @Override
    public void promoteToApplicant(String clientUniqueId) throws InvalidUserGroupException {
        // check if the client has the server group guest

        // give client the applicant server groups and persist as applicant
    }

    @Override
    public void promoteToRecruit(String clientUniqueId) throws InvalidUserGroupException {
        // check if the client has the server group applicant

        // give client the applicant server groups and persist as recruit
    }

    @Override
    public void addPositiveVote(String clientUniqueId) {
        // check if the client has the server group recruit

        // add a positive vote for the recruit
    }

    @Override
    public void addPositiveVote(String clientUniqueId, String comment) {

    }

    @Override
    public void addNegativeVote(String clientUniqueId) {
        // check if the client has the server group recruit

        // add a negative vote for the recruit
    }

    @Override
    public void addNegativeVote(String clientUniqueId, String comment) {

    }


    @Override
    public RecruitVote getVotes(String clientUniqueId) {
        // check if the client has the server group recruit

        // return the amount of votes
        return null;
    }

    @Override
    public void setVotes(String clientUniqueId, RecruitVote votes) {
        // check if the client has the server group recruit

        // set the amount of votes
    }

    @Override
    public boolean hasRecruitExceededTrialPeriod(String clientUniqueId) {
        return false;
    }

    @Override
    public void promoteToMember(String clientUniqueId, boolean skipVotes) throws InvalidUserGroupException {
        // check if the client has the server group recruit

        // if !skipVotes: check if the client has enough votes

        // give the client member server groups and persist as member
    }

    @Override
    public void requestSupporterType(String uniqueClientId) {

    }

    @Override
    public void promoteToSupporter(String uniqueClientId) {

    }
}
