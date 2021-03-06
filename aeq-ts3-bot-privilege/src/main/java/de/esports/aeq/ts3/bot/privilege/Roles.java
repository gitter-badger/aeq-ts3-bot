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

import de.esports.aeq.ts3.bot.model.Role;

/**
 * Maps a {@link Role} to a specific enum constant.
 * <p>
 * Use either of this constants instead of string literals.
 *
 * @author Lukas Kannenberg
 * @version 1.0
 * @since 09.09.2017
 */
public enum Roles {

    DEVELOPER("developer"),
    CAO("cao"),
    DIRECTOR("director"),
    EVENT_SPECIALIST("event_specialist"),
    SUPPORTER("supporter"),
    TRUSTED_MEMBER("trusted_member"),
    MEMBER("member"),
    RECRUIT("recruit"),
    APPLICANT("applicant"),
    GUEST("guest");

    /**
     * The database name of this role.
     */
    private String name;

    /**
     * Constructs a new role with a name.
     *
     * @param name the name of the role
     */
    Roles(String name) {
        this.name = name;
    }

    /**
     * @return the database name of the role
     */
    public String getName() {
        return name;
    }
}
