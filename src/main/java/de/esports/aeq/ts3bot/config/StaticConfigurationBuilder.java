package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import de.esports.aeq.ts3bot.core.api.Credentials;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serves a static {@link BotConfiguration}.
 * <p>
 * Remember that for security reasons the username and password still needs to be entered manually. Preferably you
 * can use {@link CredentialsUtil#readFromCommandLine()}.
 *
 * @author Lukas Kannenberg
 * @since 01.07.2017
 */
public class StaticConfigurationBuilder implements ConfigurationBuilder {

    private static final Logger log = LoggerFactory.getLogger(StaticConfigurationBuilder.class);

    private static final String HOSTNAME = "137.74.223.0";
    private static final int QUERY_PORT = 10011;
    private static final int VIRTUAL_SERVER_PORT = 9889;
    private static final String NAME = "Merlin";

    public StaticConfigurationBuilder() {

    }

    @Override
    public @NotNull BotConfiguration build() throws ConfigurationBuildException {
        Credentials credentials;
        try {
            credentials = CredentialsUtil.readFromCommandLine();
        } catch (Exception e) {
            throw new ConfigurationBuildException("cannot read credentials", e);
        }
        return new BotConfiguration(HOSTNAME, QUERY_PORT, VIRTUAL_SERVER_PORT, credentials.getUsername(), credentials
                .getPassword(), NAME);
    }
}
