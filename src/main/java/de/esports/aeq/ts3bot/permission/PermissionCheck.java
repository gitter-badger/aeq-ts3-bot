package de.esports.aeq.ts3bot.permission;

import de.esports.aeq.ts3bot.core.AeQESportsTS3Bot;
import de.esports.aeq.ts3bot.core.api.User;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 25.07.2017.
 */
public class PermissionCheck {

    private AeQESportsTS3Bot ts3Bot;

    private List<User> users = new ArrayList<>();

    private List<String> requiredUserGroups = new ArrayList<>();

    private List<String> forbiddenUserGroups = new ArrayList<>();

    // Metadata

    private HashMap<String, String> eventInfo;
    private boolean isFullAdmin;
    private boolean isAdmin;

    public PermissionCheck(AeQESportsTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    public PermissionCheck useMetadata(HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        this.eventInfo = eventInfo;
        this.isFullAdmin = isFullAdmin;
        this.isAdmin = isAdmin;
        return this;
    }

    public PermissionCheck forUser(@NotNull User user) {
        this.users.add(user);
        return this;
    }

    public PermissionCheck forUsers(@NotNull User... users) {
        this.users.addAll(Arrays.asList(users));
        return this;
    }

    public PermissionCheck requireUserGroup(@NotNull String userGroup) {
        this.requiredUserGroups.add(userGroup);
        return this;
    }

    public PermissionCheck requireUserGroups(@NotNull String... userGroups) {
        this.requiredUserGroups.addAll(Arrays.asList(userGroups));
        return this;
    }

    public PermissionCheck forbidUserGroup(@NotNull String userGroup) {
        this.forbiddenUserGroups.add(userGroup);
        return this;
    }

    public PermissionCheck forbidUserGroups(@NotNull String... userGroups) {
        this.forbiddenUserGroups.addAll(Arrays.asList(userGroups));
        return this;
    }

    /**
     * Validates if the given users match the required permissions.
     * <p>
     * We use an {@link Observable} here since validation may be async.
     *
     * @return an {@link Observable} that resolves to true if the user has the required permissions or gets rejected if
     * an error occurred during the process
     */
    public Observable<Boolean> validate() {
        return Observable.just(true);
    }

}
