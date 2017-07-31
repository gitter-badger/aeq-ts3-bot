package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.AccountService;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.RecruitService;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlServiceFactory extends ServiceFactory {

    @Override
    public @NotNull AccountService getAccountService() {
        return new MySqlAccountService();
    }

    @Override
    public @NotNull ApplicationService getApplicationService() {
        return new MySqlApplicationService();
    }

    @Override
    public @NotNull RecruitService getRecruitService() {
        return new MySqlRecruitService();
    }
}
