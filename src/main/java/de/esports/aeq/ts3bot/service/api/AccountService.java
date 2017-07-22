package de.esports.aeq.ts3bot.service.api;

import io.reactivex.Observable;

/**
 * Represents a general interface for account service calls.
 *
 * @author Lukas Kannenberg
 * @since 19.07.2017.
 */
public interface AccountService {

    /**
     * Returns the access-key for a specific ts3id if present.
     * <p>
     *
     * @param ts3Id the teamspeak3 id of the user
     * @return an {@link Observable<String>} that resolves when the action finished or rejects when an error occurred
     * during the process. The resolved String may be null if no access-key if present for that.
     */
    public Observable<String> getAccessKey(String ts3Id);

    /**
     * @param ts3Id
     * @param key
     * @return
     */
    public Observable<Boolean> linkAccessKey(String ts3Id, String key);

}
