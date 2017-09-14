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

package de.esports.aeq.ts3.bot.privilege.api;

import de.esports.aeq.ts3.bot.model.Role;
import de.esports.aeq.ts3.bot.privilege.Roles;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import de.esports.aeq.ts3.bot.workflow.exception.WorkflowException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 09.09.2017
 */
public interface Privilege {

    /**
     * Validates whether a client owns a specific {@link Roles}.
     *
     * @param uniqueClientId the unique id of the client
     * @param role           the required {@link Roles}
     * @return true if the clients owns the specific {@link Roles}, otherwise false
     * @deprecated use {@link #isMemberOfRole(String, Roles)} instead, this method will not be implemented and always
     * return false
     */
    @Deprecated
    boolean hasRole(@NotNull String uniqueClientId, @NotNull Roles role);

    /**
     * Validates whether a client is member of the a specific {@link Role}.
     * <p>
     * Please note that this process only considers a single role and <b>does not check for any hierarchical
     * relationships</b>. If you want to check if a client has the required privileges for a specific role use {@link
     * #hasRequiredPrivileges(String, Roles)} instead.
     *
     * @param uniqueClientId the unique id of the client
     * @param role           the single role to check for
     * @return true if the client is member of exactly the provided role, otherwise false
     */
    boolean isMemberOfRole(@NotNull String uniqueClientId, @NotNull Roles role) throws UserNotFoundException;

    /**
     * Validates whether a client has privileges for a specific {@link Role}.
     * <p>
     * This process <b>does consider hierarchical relationships</b> and also returns true if the client has a role that
     * is higher in the tree than the required role. Use this method to check if a client has permission to execute an
     * action.
     *
     * @param uniqueClientId the unique id of the client
     * @param role           the minimum required role of the client
     * @return true if the client has the required privileges, otherwise false
     */
    boolean hasRequiredPrivileges(@NotNull String uniqueClientId, @NotNull Roles role) throws WorkflowException;

    /**
     * Updates the permissions of a client.
     * <p>
     * This will read the current state of the client from the database and set the corresponding server and channel
     * groups accordingly.
     *
     * @param clientUniqueId the unique id of the client
     */
    void updateServerGroups(@NotNull String clientUniqueId);

}
