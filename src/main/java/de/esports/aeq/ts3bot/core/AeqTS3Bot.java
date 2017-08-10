/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
import de.esports.aeq.ts3bot.event.DefaultTextMessageHandler;
import de.esports.aeq.ts3bot.event.PrivilegedHandler;
import de.esports.aeq.ts3bot.event.WelcomeClientJoinHandler;
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
        listeners.add(context.getBean(DefaultTextMessageHandler.class));
        listeners.add(context.getBean(WelcomeClientJoinHandler.class));
        for (TS3Listener listener : listeners) {
            // We wrap a privileged handler around for testing purposes
            api.addTS3Listeners(new PrivilegedHandler(listener));
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
