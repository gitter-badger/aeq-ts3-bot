package de.esports.aeq.ts3bot.repository.api;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import io.reactivex.Observable;

import java.util.List;

public interface ConfigurationService {

    /**
     * Adds a new {@link BotConfiguration} to the database.
     * <p>
     * Each {@link BotConfiguration} must specify a unique name, otherwise an existing {@link BotConfiguration} will
     * be replaced
     *
     * @param configuration the {@link BotConfiguration}
     * @return an {@link Observable} that resolves to true if the action was successful or to false if an error
     * occurred
     */
    boolean addConfiguration(BotConfiguration configuration);

    void updateConfiguration(String configId, BotConfiguration configuration);

    void deleteConfiguration(String configId);

    BotConfiguration getConfiguration(String configId);

    List<BotConfiguration> getConfigurations();

    BotConfiguration getActiveConfiguration();

    boolean updateActiveConfiguration(BotConfiguration configuration);
}
