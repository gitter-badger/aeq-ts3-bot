package de.esports.aeq.ts3bot.repository.api;

import de.esports.aeq.ts3bot.core.api.Application;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a general interface for application repository calls.
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
    @NotNull
    public Observable<Application> acceptApplication(@Nullable String ts3id);

    /**
     * Rejects an application from a user.
     *
     * @param ts3id the teamspeak3 id of the user
     * @return
     */
    @NotNull
    public Observable<Application> rejectApplication(@Nullable String ts3id);

}
