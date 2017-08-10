package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.repository.api.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lukas on 19.07.2017.
 */
public abstract class ServiceFactory {

    public static final int MYSQL = 1;

    /**
     * Returns the default factory type.
     *
     * @return the default factory type.
     */
    public static int getDefaultFactoryType() {
        return MYSQL;
    }

    /**
     * Returns an appropriate {@link ServiceFactory}, depending on the specified database type.
     *
     * @param factoryType the database type, currently available {@link ServiceFactory#MYSQL}
     * @return the {@link ServiceFactory} if present
     */
    @Nullable
    public static ServiceFactory getServiceFactory(int factoryType) {
        switch (factoryType) {
            case MYSQL:
                return new MySqlServiceFactory();
            default:
                return null;
        }
    }

    @NotNull
    public abstract PermissionService getPermissionService();

    @NotNull
    public abstract AccountService getAccountService();

    @NotNull
    public abstract ApplicationService getApplicationService();

    @NotNull
    public abstract RecruitService getRecruitService();

    @NotNull
    public abstract ConfigurationService getConfigurationService();

    @NotNull
    public abstract AuthenticationService getAuthenticationService();
}
