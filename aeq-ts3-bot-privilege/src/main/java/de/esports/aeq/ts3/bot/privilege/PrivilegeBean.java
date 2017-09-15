/*
 * MIT License
 *
 * Copyright (c) 2017 Lukas Kannenberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.esports.aeq.ts3.bot.privilege;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import de.esports.aeq.ts3.bot.dataprovider.api.UserRepository;
import de.esports.aeq.ts3.bot.model.Role;
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.model.User;
import de.esports.aeq.ts3.bot.privilege.api.PrivilegeApi;
import de.esports.aeq.ts3.bot.workflow.exception.ClientNotFoundException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Lukas Kannenberg
 */
@Component
public class PrivilegeBean implements PrivilegeApi {

    private TS3Bot ts3Bot;
    private UserRepository userRepository;

    public PrivilegeBean(TS3Bot ts3Bot, UserRepository userRepository) {
        this.ts3Bot = ts3Bot;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isMemberOfRole(@NotNull String uniqueClientId, @NotNull Roles role) throws
            ClientNotFoundException, UserNotFoundException {
        checkRoleForNull(role);
        Role userRole = getUser(uniqueClientId).getRole();
        ClientInfo clientInfo = getClientInfo(uniqueClientId);
        return checkRoleForServerGroups(userRole, clientInfo.getServerGroups());
    }

    @Override
    public boolean hasRequiredPrivileges(@NotNull String uniqueClientId, @NotNull Roles role) throws
            ClientNotFoundException, UserNotFoundException {
        checkRoleForNull(role);
        ClientInfo clientInfo = getClientInfo(uniqueClientId);
        Role currentRole = getUser(uniqueClientId).getRole();
        while (currentRole != null) {
            if (checkRoleForServerGroups(currentRole, clientInfo.getServerGroups())) return true;
            currentRole = currentRole.getParent();
        }
        return false;
    }

    @Override
    public void updateServerGroups(@NotNull String clientUniqueId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Validates whether a {@link Role} contains one of the given server groups.
     *
     * @param serverGroups the array of server groups
     * @param role         the {@link Role} to check
     * @return true if the {@link Role} does contain at least one of the given server groups, otherwise false
     */
    private boolean checkRoleForServerGroups(@Nullable Role role, @NotNull int[] serverGroups) {
        if (role == null) return false;
        int[] roles = role.getServerGroups().stream().mapToInt(i -> i).toArray();
        for (int i : roles)
            if (ArrayUtils.contains(serverGroups, i))
                return true;
        return false;
    }

    @NotNull
    private User getUser(@NotNull String uniqueClientId) throws UserNotFoundException {
        Objects.requireNonNull(uniqueClientId, "unique client id must not be null");
        User user = userRepository.getFirstByTs3Id(uniqueClientId);
        if (user == null) {
            throw new UserNotFoundException("cannot find user with uid " + uniqueClientId);
        }
        return user;
    }

    @NotNull
    private ClientInfo getClientInfo(String uniqueClientId) throws ClientNotFoundException {
        ClientInfo clientInfo = ts3Bot.getTs3Api().getClientByUId(uniqueClientId);
        if (clientInfo == null)
            throw new ClientNotFoundException(uniqueClientId);
        return clientInfo;
    }

    private void checkRoleForNull(Roles role) {
        Objects.requireNonNull(role, "role must not be null");
    }
}
