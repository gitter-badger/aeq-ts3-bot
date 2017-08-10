package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.AeqBotException;
import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import de.esports.aeq.ts3bot.repository.ServiceFactory;
import org.jetbrains.annotations.Nullable;

public class ServiceConfigurationLoader implements ConfigurationBuilder {

    @Override
    public @Nullable BotConfiguration build() {
        ServiceFactory serviceFactory = ServiceFactory.getServiceFactory(ServiceFactory.getDefaultFactoryType());
        if (serviceFactory == null) {
            throw new AeqBotException("Cannot find repository factory.");
        }
        return serviceFactory.getConfigurationService().getActiveConfiguration();
    }
}
