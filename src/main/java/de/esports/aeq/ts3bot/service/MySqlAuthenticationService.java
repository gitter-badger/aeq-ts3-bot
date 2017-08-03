package de.esports.aeq.ts3bot.service;

import de.esports.aeq.ts3bot.core.api.Credentials;
import de.esports.aeq.ts3bot.service.api.AuthenticationService;

public class MySqlAuthenticationService implements AuthenticationService {

    @Override
    public boolean authenticateUser(Credentials credentials) {
        return false;
    }
}
