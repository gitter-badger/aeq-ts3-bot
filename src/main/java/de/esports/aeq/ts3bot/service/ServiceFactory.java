package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.service.api.AccountService;
import de.esports.aeq.ts3bot.service.api.ApplicationService;
import de.esports.aeq.ts3bot.service.api.RecruitService;

/**
 * Created by Lukas on 19.07.2017.
 */
public abstract class ServiceFactory {

    public static final int MYSQL = 1;

    /**
     * Returns an appropriate {@link ServiceFactory}, depending on the specified database type.
     *
     * @param factoryType the database type, currently available {@link ServiceFactory#MYSQL}
     * @return the {@link ServiceFactory} if present
     */
    public static ServiceFactory getServiceFactory(int factoryType) {
        switch (factoryType) {
            case MYSQL:
                return new MySqlServiceFactory();
            default:
                return null;
        }
    }

    public abstract AccountService getAccountService();

    public abstract ApplicationService getApplicationService();

    public abstract RecruitService getRecruitService();

}
