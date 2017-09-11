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
import de.esports.aeq.ts3.bot.model.TS3Bot;
import de.esports.aeq.ts3.bot.privilege.api.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lukas Kannenberg
 */
@Component
public class PrivilegeBean implements Privilege {

    private TS3Bot ts3Bot;

    @Autowired
    public PrivilegeBean(TS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public boolean hasRequiredPrivileges(String clientUniqueId, Role requiredRole) {
        ClientInfo info = ts3Bot.getTs3Api().getClientByUId(clientUniqueId);
        return info != null && Role.hasPrivilege(requiredRole, info.getServerGroups());
    }

    @Override
    public boolean hasRole(Role role) {
        return false;
    }

    @Override
    public void updateServerGroups(String clientUniqueId) {

    }
}
