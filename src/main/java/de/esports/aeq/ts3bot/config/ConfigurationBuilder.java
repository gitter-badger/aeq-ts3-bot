package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ConfigurationBuilder {

    /**
     * Builds the target {@link BotConfiguration}.
     * <p>
     * The exact method of loading the configuration (e.g. using a repository or file-based system) is used is up to the
     * implementation.
     *
     * @return the build {@link BotConfiguration}, never null
     * @throws ConfigurationBuildException if the configuration cannot be build successfully
     */
    @NotNull
    BotConfiguration build() throws ConfigurationBuildException;
}
