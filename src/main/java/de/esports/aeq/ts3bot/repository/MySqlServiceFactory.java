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

package de.esports.aeq.ts3bot.repository;

import de.esports.aeq.ts3bot.repository.api.*;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 19.07.2017.
 */
public class MySqlServiceFactory extends ServiceFactory {

    @Override
    public @NotNull PermissionService getPermissionService() {
        return new MySqlPermissionService();
    }

    @Override
    public @NotNull AccountService getAccountService() {
        return new MySqlAccountService();
    }

    @Override
    public @NotNull ApplicationService getApplicationService() {
        return new MySqlApplicationService();
    }

    @Override
    public @NotNull RecruitService getRecruitService() {
        return new MySqlRecruitService();
    }

    @Override
    public @NotNull ConfigurationService getConfigurationService() {
        return new MySqlConfigurationService();
    }

    @Override
    public @NotNull AuthenticationService getAuthenticationService() {
        return new MySqlAuthenticationService();
    }
}
