package de.esports.aeq.ts3bot.service.api;

import io.reactivex.Observable;

/**
 * Represents a general interface for application service calls.
 *
 * @author Lukas Kannenberg
 * @since 22.07.2017.
 */
public interface ApplicationService {

    /**
     * Accepts an application from a user.
     *
     * @param ts3id the teamspeak3 id of the user
     * @return an {@link Observable<Boolean>} that resolves to true if the applicant got accepted, to false if the
     * action could not be performed or gets rejected if any network error occurs.
     */
    public Observable<Boolean> acceptApplication(String ts3id);

    /**
     * Rejects an application from a user.
     *
     * @param ts3id the teamspeak3 id of the user
     * @return
     */
    public Observable<Boolean> rejectApplication(String ts3id);

}
