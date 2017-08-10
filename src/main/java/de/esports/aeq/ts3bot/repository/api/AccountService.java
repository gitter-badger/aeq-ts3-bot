package de.esports.aeq.ts3bot.repository.api;

import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a general interface for account repository calls.
 *
 * @author Lukas Kannenberg
 * @since 19.07.2017.
 */
public interface AccountService {

    /**
     * Returns the access-key for a specific teamspeak id if present.
     * <p>
     *
     * @param ts3Id the teamspeak id of the user
     * @return an {@link Observable} that resolves when the action finished or rejects when an error occurred
     * during the process. The resolved String may be null if no access-key if present for that id.
     */
    @NotNull
    Observable<String> getAccessKey(@Nullable String ts3Id);

    /**
     * @param ts3Id the teamspeak id of the user
     * @param key   the access-key of the user
     * @return
     */
    @NotNull
    Observable<Boolean> linkAccessKey(@Nullable String ts3Id, @Nullable String key);

}
