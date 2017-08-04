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
import de.esports.aeq.ts3bot.event.EchoTextMessageHandler;
import de.esports.aeq.ts3bot.event.GuestClientJoinHandler;
import de.esports.aeq.ts3bot.event.PrivilegedMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 23.07.2017.
 */
@Component()
public class AeqTS3Bot {

    public static final Logger log = LoggerFactory.getLogger(AeqTS3Bot.class);

    private final AnnotationConfigApplicationContext context;
    private final ConfigurationBuilder configurationBuilder;

    private BotConfiguration configuration;

    private TS3Api api;
    private TS3ApiAsync apiAsync;

    @Autowired
    public AeqTS3Bot(AnnotationConfigApplicationContext context, ConfigurationBuilder configurationBuilder) {
        this.context = context;
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
        initEventHandlers();
        api.registerAllEvents();
    }

    private void initEventHandlers() {
        List<TS3Listener> listeners = new ArrayList<>();

        listeners.add(context.getBean(EchoTextMessageHandler.class));
        listeners.add(context.getBean(GuestClientJoinHandler.class));
        for (TS3Listener listener : listeners) {
            // We wrap a privileged handler around for testing purposes
            api.addTS3Listeners(new PrivilegedMessageHandler(listener));
        }
    }

    private void buildConfiguration() {
        log.info("building bot configuration");
        try {
            this.configuration = configurationBuilder.build();
        } catch (ConfigurationBuildException e) {
            throw new RuntimeException("cannot build bot configuration", e);
        }
        log.info("bot configuration successfully build with {}", configurationBuilder.getClass().getName());
    }

    private void login() throws AeqBotException {
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

    public TS3ApiAsync getApiAsync() {
        return apiAsync;
    }
}
