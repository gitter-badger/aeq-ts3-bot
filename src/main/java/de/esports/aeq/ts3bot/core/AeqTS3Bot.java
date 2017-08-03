package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import de.esports.aeq.ts3bot.config.ConfigurationBuildException;
import de.esports.aeq.ts3bot.config.ConfigurationBuilder;
import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lukas on 23.07.2017.
 */
@Component
public class AeqTS3Bot {

    public static final Logger log = LoggerFactory.getLogger(AeqTS3Bot.class);

    private final ConfigurationBuilder configurationBuilder;

    private BotConfiguration configuration;

    private TS3Api api;
    private TS3ApiAsync apiAsync;

    @Autowired
    public AeqTS3Bot(ConfigurationBuilder configurationBuilder) {
        this.configurationBuilder = configurationBuilder;
    }

    public void start() {
        buildConfiguration();
        final TS3Config config = new TS3Config();
        config.setHost(configuration.getHostname());
        if (configuration.getQueryPort() > 0) {
            config.setQueryPort(configuration.getQueryPort());
        }
        config.setConnectionHandler(new AeqBotConnectionHandler());

        final TS3Query query = new TS3Query(config);
        query.connect();

        // Register external beans
        api = query.getApi();
        apiAsync = query.getAsyncApi();

        api.selectVirtualServerByPort(configuration.getVirtualServerPort());

        login();

        api.setNickname(configuration.getName());
        TS3Listener ts3Listener = new AeqTS3Listener(api, apiAsync);
        api.addTS3Listeners(ts3Listener);
        api.registerAllEvents();
    }

    public void buildConfiguration() {
        log.info("building bot configuration");
        try {
            this.configuration = configurationBuilder.build();
        } catch (ConfigurationBuildException e) {
            throw new RuntimeException("cannot build bot configuration", e);
        }
        log.info("bot configuration successfully build with {}", configurationBuilder.getClass().getName());
    }

    public void login() throws AeqBotException {
        log.info("attempting to login as {}", configuration.getUsername());
        if (api.login(configuration.getUsername(), configuration.getPassword())) {
            ChannelInfo info = api.getChannelInfo(api.whoAmI().getChannelId());
            log.info("login successful, joined channel {} as {}", info.getName(), api.whoAmI().getNickname());
        } else {
            throw new AeqBotException("unable to login as " + api.whoAmI().getNickname());
        }
    }

    public TS3Api getApi() {
        return api;
    }

    public void setApi(TS3Api api) {
        this.api = api;
    }

    public TS3ApiAsync getApiAsync() {
        return apiAsync;
    }

    public void setApiAsync(TS3ApiAsync apiAsync) {
        this.apiAsync = apiAsync;
    }
}
