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

/**
 * @author Lukas Kannenberg
 */
public enum Role {

    DEVELOPER(9, new int[]{0}),

    CAO(8, new int[]{0}),

    DIRECTOR(7, new int[]{0}),

    EVENT_SPECIALIST(6, new int[]{0}),

    SUPPORTER(5, new int[]{0}),

    TRUSTED_MEMBER(4, new int[]{0}),

    MEMBER(3, new int[]{0}),

    RECRUIT(2, new int[]{0}),

    APPLICANT(1, new int[]{0}),

    GUEST(0, new int[]{0});

    private int power;
    private int[] groupIds;

    Role(int power, int[] groupIds) {
        this.power = power;
        this.groupIds = groupIds;
    }

    public static boolean hasPrivilege(Role requiredRole, int[] powers) {
        for (int i : powers)
            if (i >= requiredRole.getPower()) return true;
        return false;
    }

    public int getPower() {
        return power;
    }

    public int[] getGroupIds() {
        return groupIds;
    }
}
