/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import de.esports.aeq.ts3.bot.configuration.BotConfiguration;
import de.esports.aeq.ts3.bot.configuration.ConfigurationBuildException;
import de.esports.aeq.ts3.bot.configuration.XmlConfigurationBuilder;
import de.esports.aeq.ts3.bot.core.event.DefaultTextMessageHandler;
import de.esports.aeq.ts3.bot.core.event.PrivilegedHandler;
import de.esports.aeq.ts3.bot.core.event.WelcomeClientJoinHandler;
import de.esports.aeq.ts3.bot.model.TS3Bot;
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
public class AeqTS3Bot implements TS3Bot {

    private static final Logger LOG = LoggerFactory.getLogger(TS3Bot.class);

    private final AnnotationConfigApplicationContext context;

    private BotConfiguration configuration;

    private TS3Query query;
    private TS3Api api;
    private TS3ApiAsync apiAsync;

    @Autowired
    public AeqTS3Bot(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    @Override
    public void buildConfiguration(String pathname) {
        LOG.info("building bot configuration");
        try {
            XmlConfigurationBuilder builder = new XmlConfigurationBuilder(pathname);
            this.configuration = builder.build();
        } catch (ConfigurationBuildException e) {
            throw new AeqBotException("cannot build bot configuration", e);
        }

        LOG.info("bot configuration successfully build from {}", pathname);
        if (LOG.isInfoEnabled()) {
            LOG.info(configuration.toString());
        }
    }

    @Override
    public void start() {
        if (configuration == null) {
            throw new AeqBotException("bot configuration is not successfully initialized");
        }
        LOG.info("Starting bot.");
        query = new TS3Query(createTS3Configuration());
        query.connect();
        api = query.getApi();
        apiAsync = query.getAsyncApi();
        api.selectVirtualServerByPort(configuration.getServer().getVirtualServerPort());

        login();

        api.setNickname(configuration.getName());
        initEventHandlers();
        api.registerAllEvents();
    }

    @Override
    public void stop() {
        LOG.info("Stopping bot.");
        // Remember to do any cleanup operations before shutting down the bot
        query.exit();
    }

    @Override
    public void restart() {
        LOG.info("Restarting bot.");
        stop();
        start();
    }

    @Override
    public TS3Api getTs3Api() {
        return api;
    }

    @Override
    public TS3ApiAsync getTs3ApiAsync() {
        return apiAsync;
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

    /**
     * Create a {@link TS3Config} object using the initialized {@link BotConfiguration}.
     *
     * @return the configuration object
     */
    private TS3Config createTS3Configuration() {
        if (configuration == null) {
            throw new AeqBotException("unable to build ts3 configuration: configuration is not properly initialized");
        }
        TS3Config config = new TS3Config();
        config.setHost(configuration.getServer().getHostname());
        config.setQueryPort(configuration.getServer().getQueryPort());
        return config;
    }

    private void login() {
        LOG.info("attempting to login as {}", configuration.getPermissions().getUsername());
        if (api.login(configuration.getPermissions().getUsername(), configuration.getPermissions().getPassword())) {
            ChannelInfo info = api.getChannelInfo(api.whoAmI().getChannelId());
            LOG.info("login successful, joined channel {} as {}", info.getName(), api.whoAmI().getNickname());
        } else {
            throw new AeqBotException("unable to login as " + api.whoAmI().getNickname());
        }
    }

}
