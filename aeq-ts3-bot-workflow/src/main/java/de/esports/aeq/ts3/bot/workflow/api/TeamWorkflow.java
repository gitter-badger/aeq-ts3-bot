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

package de.esports.aeq.ts3.bot.workflow.api;

import de.esports.aeq.ts3.bot.model.Game;
import de.esports.aeq.ts3.bot.workflow.exception.InsufficientPermissionException;
import de.esports.aeq.ts3.bot.workflow.exception.InvalidTeamNameException;
import de.esports.aeq.ts3.bot.workflow.exception.UnresolvedGameTypeException;
import de.esports.aeq.ts3.bot.workflow.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Defines common methods to interact with the workflow for teams.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 10.09.2017
 */
public interface TeamWorkflow {

    /**
     * Creates a new team with the specified team name.
     * <p>
     * The game type for this team will be determined by the channel the executing user is currently in. If the team is
     * created successfully, the executing user will become the leader of this team.
     *
     * @param clientUniqueId the unique id of the client
     * @param name           the name of the team
     * @return the newly created {@link Team}
     * @throws UnresolvedGameTypeException if the game type cannot be resolved automatically
     * @throws InvalidTeamNameException    if the name of the team is either <code>null</code>, empty or invalid because
     *                                     of other naming conventions
     * @throws UserNotFoundException       if the user cannot be found
     */
    @NotNull
    Team createTeam(@Nullable String clientUniqueId, @Nullable String name) throws InsufficientPermissionException,
            UnresolvedGameTypeException, InvalidTeamNameException, UserNotFoundException;

    /**
     * Creates a new team with the specified team name and game type.
     * <p>
     * If the team is created successfully, the executing user will become the leader of this team.
     *
     * @param clientUniqueId the unique id of the client
     * @param game           the type of {@link Game} for this team
     * @param name           the name of the team
     * @return the newly created {@link Team} object
     * @throws InvalidTeamNameException if the name of the team is either <code>null</code>, empty or invalid because of
     *                                  other naming conventions
     * @throws UserNotFoundException    if the user cannot be found
     */
    @NotNull
    Team createTeam(@Nullable String clientUniqueId, @Nullable Game game, @Nullable String name) throws
            InsufficientPermissionException, InvalidTeamNameException, UserNotFoundException;

    /**
     * Deletes the team of the user that matches the specified unique id.
     * <p>
     * Either of the following requirements must be fulfilled to determine the correct team to delete: <ul> <li>The user
     * is only member of one team</li> <li>The user is within the related team channel</li><li>The user is within the
     * related game area and is only member within one team in that area</li></ul>
     *
     * @param clientUniqueId the unique id of the client
     */
    void deleteTeam(@Nullable String clientUniqueId);

    /**
     * @param clientUniqueId
     * @param teamId
     */
    void deleteTeam(@Nullable String clientUniqueId, int teamId);

    void joinTeam(@Nullable String clientUniqueId, int teamId);

    void joinTeam(@Nullable String clientUniqueId, @Nullable String name);

    void leaveTeam(@Nullable String clientUniqueId, int teamId);

    void leaveTeam(@Nullable String clientUniqueId, @Nullable String name);


}
