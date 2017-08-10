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
