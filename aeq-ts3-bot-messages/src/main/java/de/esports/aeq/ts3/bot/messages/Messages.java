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

package de.esports.aeq.ts3.bot.messages;

import java.util.Locale;

/**
 * Created by Lukas on 24.07.2017.
 */
public class Messages {

    /**
     * The default {@link Locale} to be used.
     */
    public static final Locale DEFAULT_LOCALE = Locale.GERMAN;

    // Messages codes used in the database
    public static final String WELCOME = "welcome";
    public static final String ADVERTISE_MEMBERSHIP = "advertise_membership";

    public static final String PROMOTE_TO_MEMBER_INVALID_USER_GROUP = "";

    public static final String COMMAND_INVALID_PERMISSIONS = "command_invalid_permissions";
    public static final String COMMAND_INVALID_APPLY_TYPE = "command_invalid_apply_type";
    public static final String PROMOTE_APPLICANT_SUCCESS = "command_invalid_user_group";
    public static final String COMMAND_INVALID_USER_GROUP = "command_invalid_user_group";


    public static final String C_TEAM_T_CREATE_INVALID_USER_GROUP = "c_team_t_create_invalid_user_group";
    public static final String C_TEAM_T_CREATE_INVALID_TEAM_NAME = "c_team_t_create_invalid_team_name";


    public static final String C_TEAM_T_CREATE_DUPLICATE_TEAM_NAME = "c_team_t_create_duplicate_team_name";
    public static final String C_TEAM_T_CREATE_EXCEPTION = "c_team_create_exception";
    public static final String C_TEAM_T_CREATE_SUCCESS = "c_team_create_success";

    public static final String C_APPLY_NOT_LINKED = "c_apply_not_linked";
    public static final String C_APPLY_LINKED = "c_apply_linked";

    public static final String C_APPLY_ALREADY_MEMBER = "c_apply_already_member";
    public static final String C_APPLY_AFTER_MOVE = "c_apply_after_move";

    public static final String C_LINK_ALREADY_LINKED = "c_link_already_linked";
    public static final String C_LINK_SUCCESSFUL = "c_link_successful";
    public static final String C_LINK_AFTER_APPLICANT_MOVE = "c_link_after_applicant_move";
}
