package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.AccountService;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.RecruitService;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlServiceFactory extends ServiceFactory {

    @Override
    public AccountService getAccountService() {
        return new MySqlAccountService();
    }

    @Override
    public ApplicationService getApplicationService() {
        return new MySqlApplicationService();
    }

    @Override
    public RecruitService getRecruitService() {
        return new MySqlRecruitService();
    }
}
