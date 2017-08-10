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

package de.esports.aeq.ts3bot.repository.api;

import de.esports.aeq.ts3bot.core.api.BotConfiguration;
import io.reactivex.Observable;

import java.util.List;

public interface ConfigurationService {

    /**
     * Adds a new {@link BotConfiguration} to the database.
     * <p>
     * Each {@link BotConfiguration} must specify a unique name, otherwise an existing {@link BotConfiguration} will
     * be replaced
     *
     * @param configuration the {@link BotConfiguration}
     * @return an {@link Observable} that resolves to true if the action was successful or to false if an error
     * occurred
     */
    boolean addConfiguration(BotConfiguration configuration);

    void updateConfiguration(String configId, BotConfiguration configuration);

    void deleteConfiguration(String configId);

    BotConfiguration getConfiguration(String configId);

    List<BotConfiguration> getConfigurations();

    BotConfiguration getActiveConfiguration();

    boolean updateActiveConfiguration(BotConfiguration configuration);
}
