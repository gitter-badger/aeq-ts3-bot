package de.esports.aeq.ts3bot.repository.api;

import de.esports.aeq.ts3bot.core.api.Credentials;

/**
 * Responsible for authenticating users that are persisted in the database.
 *
 * @author Lukas Kannenberg
 * @since 01.07.2017
 */
public interface AuthenticationService {

    /**
     * Authenticates a user with the given {@link Credentials}.
     *
     * @param credentials the {@link Credentials} of the user
     * @return true if the authentication is successful, otherwise false
     */
    boolean authenticateUser(Credentials credentials);
}
