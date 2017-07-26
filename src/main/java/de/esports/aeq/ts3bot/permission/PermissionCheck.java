package de.esports.aeq.ts3bot.permission;

import de.esports.aeq.ts3bot.core.api.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 25.07.2017.
 */
public class PermissionCheck {

    private List<User> users = new ArrayList<>();

    private List<String> requiredUserGroups = new ArrayList<>();

    private List<String> forbiddenUserGroups = new ArrayList<>();

    // Metadata

    private HashMap<String, String> eventInfo;
    private boolean isFullAdmin;
    private boolean isAdmin;

    public PermissionCheck(HashMap<String, String> eventInfo, boolean isFullAdmin, boolean isAdmin) {
        this.eventInfo = eventInfo;
        this.isFullAdmin = isFullAdmin;
        this.isAdmin = isAdmin;
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

    public boolean validate() {
        // TODO(glains): implement
        return true;
    }

}
