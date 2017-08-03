package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import de.esports.aeq.ts3bot.config.CredentialsUtil;
import de.esports.aeq.ts3bot.config.StaticConfigurationLoader;
import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import de.esports.aeq.ts3bot.core.api.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lukas on 23.07.2017.
 */
public class AeqTS3Bot {

    public static final Logger log = LoggerFactory.getLogger(AeqTS3Bot.class);


    public static void main(String[] args) {
        new AeqTS3Bot().startBot();
    }

    private void startBot() {
        BotConfiguration botConfiguration = loadConfiguration();
        if (botConfiguration == null) {
            throw new AeqBotException("Cannot load bot configuration.");
        }
        final TS3Config config = new TS3Config();
        config.setHost(botConfiguration.getHostname());
        config.setConnectionHandler(new AeqBotConnectionHandler());
        if (botConfiguration.getQueryPort() > 0) {
            config.setQueryPort(botConfiguration.getQueryPort());
        }

        final TS3Query query = new TS3Query(config);
        query.connect();

        final TS3Api api = query.getApi();
        final TS3ApiAsync apiAsync = query.getAsyncApi();
        api.selectVirtualServerByPort(botConfiguration.getVirtualServerPort());
        api.login(botConfiguration.getUsername(), botConfiguration.getPassword());

        api.setNickname(botConfiguration.getName());
        TS3Listener ts3Listener = new AeqTS3Listener(api, apiAsync);
        api.addTS3Listeners(ts3Listener);
    }

    private BotConfiguration loadConfiguration() {
        Credentials credentials;
        try {
            credentials = CredentialsUtil.readFromCommandLine();
        } catch (Exception e) {
            log.error("Cannot read credentials from command line.");
            return null;
        }
        return new StaticConfigurationLoader(credentials.getUsername(), credentials.getPassword())
                .getBotConfiguration();
    }
}
