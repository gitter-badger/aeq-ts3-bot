package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

public class ClientHelpers {

    public static boolean clientContainsServerGroup(ClientInfo client, int serverGroupId) {
        for (int i : client.getServerGroups())
            if (i == serverGroupId) return true;
        return false;
    }

}
