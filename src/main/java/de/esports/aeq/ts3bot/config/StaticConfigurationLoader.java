package de.esports.aeq.ts3bot.config;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import org.jetbrains.annotations.Nullable;

/**
 * Serves a static {@link BotConfiguration}.
 * <p>
 * Remember that for security reasons the username and password still needs to be entered manually. Preferably you
 * can use {@link CredentialsUtil#readFromCommandLine()}.
 *
 * @author Lukas Kannenberg
 * @since 01.07.2017
 */
public class StaticConfigurationLoader implements ConfigurationLoader {

    private static final String HOSTNAME = "137.74.223.0";
    private static final int VIRTUAL_SERVER_PORT = 9889;

    private String username;
    private String password;

    public StaticConfigurationLoader(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public @Nullable BotConfiguration getBotConfiguration() {
        return new BotConfiguration(HOSTNAME, 0, VIRTUAL_SERVER_PORT, username, password, "Merlin");
    }
}
