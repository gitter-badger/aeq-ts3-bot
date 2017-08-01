package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface ConfigurationLoader {

    /**
     * Loads the target {@link BotConfiguration}.
     * <p>
     * The exact method of loading the configuration (e.g. using a service or file-based system) is used is up to the
     * implementation.
     *
     * @return the loaded {@link BotConfiguration} or null
     */
    @Nullable
    BotConfiguration getBotConfiguration();
}
