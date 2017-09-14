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
import de.esports.aeq.ts3.bot.privilege.api.Privilege;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import de.esports.aeq.ts3.bot.workflow.exception.WorkflowException;
import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Lukas Kannenberg
 */
@Component
public class PrivilegeBean implements Privilege {

    private TS3Bot ts3Bot;
    private UserRepository userRepository;

    public PrivilegeBean(TS3Bot ts3Bot, UserRepository userRepository) {
        this.ts3Bot = ts3Bot;
        this.userRepository = userRepository;
    }

    @Override
    @Deprecated
    public boolean hasRole(@NotNull String uniqueClientId, @NotNull Roles role) {
        return false;
    }

    @Override
    public boolean isMemberOfRole(@NotNull String uniqueClientId, @NotNull Roles role) throws UserNotFoundException {
        checkRoleForNull(role);
        return getUser(uniqueClientId).getRole().getName().equals(role.getName());
    }

    @Override
    public boolean hasRequiredPrivileges(@NotNull String uniqueClientId, @NotNull Roles role) throws
            UserNotFoundException, WorkflowException {
        // TODO: remove general exception and use specific one
        checkRoleForNull(role);
        ClientInfo clientInfo = ts3Bot.getTs3Api().getClientByUId(uniqueClientId);
        if (clientInfo == null) {
            throw new WorkflowException("client must not be null");
        }
        Role currentRole = getUser(uniqueClientId).getRole();
        while (currentRole != null) {
            int[] roles = currentRole.getServerGroups().stream().mapToInt(i -> i).toArray();
            for (int i : roles) {
                if (ArrayUtils.contains(clientInfo.getServerGroups(), i)) {
                    return true;
                }
            }
            currentRole = currentRole.getParent();
        }
        return false;
    }

    @Override
    public void updateServerGroups(@NotNull String clientUniqueId) {
        throw new UnsupportedOperationException();
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

    private void checkRoleForNull(Roles role) {
        Objects.requireNonNull(role, "role must not be null");
    }
}
