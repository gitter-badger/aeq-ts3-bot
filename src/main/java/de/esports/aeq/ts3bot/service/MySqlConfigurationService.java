package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import de.esports.aeq.ts3bot.service.api.ConfigurationService;

import java.util.List;

public class MySqlConfigurationService implements ConfigurationService {

    @Override
    public boolean addConfiguration(BotConfiguration configuration) {
        return false;
    }

    @Override
    public void updateConfiguration(String configId, BotConfiguration configuration) {

    }

    @Override
    public void deleteConfiguration(String configId) {

    }

    @Override
    public BotConfiguration getConfiguration(String configId) {
        return null;
    }

    @Override
    public List<BotConfiguration> getConfigurations() {
        return null;
    }

    @Override
    public BotConfiguration getActiveConfiguration() {
        return null;
    }

    @Override
    public boolean updateActiveConfiguration(BotConfiguration configuration) {
        return false;
    }
}
