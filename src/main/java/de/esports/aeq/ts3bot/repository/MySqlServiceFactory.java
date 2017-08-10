package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.repository.api.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlServiceFactory extends ServiceFactory {

    @Override
    public @NotNull PermissionService getPermissionService() {
        return new MySqlPermissionService();
    }

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

    @Override
    public @NotNull ConfigurationService getConfigurationService() {
        return new MySqlConfigurationService();
    }

    @Override
    public @NotNull AuthenticationService getAuthenticationService() {
        return new MySqlAuthenticationService();
    }
}
