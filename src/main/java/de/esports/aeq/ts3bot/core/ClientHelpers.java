package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

public class ClientHelpers {

    public static boolean clientContainsServerGroup(ClientInfo client, int serverGroupId) {
        for (int i : client.getServerGroups())
            if (i == serverGroupId) return true;
        return false;
    }

    public static void sendMessage(AeqTS3Bot ts3Bot, int clientId, String[] messages) {
        for (String s : messages) {
            ts3Bot.getApi().sendPrivateMessage(clientId, s);
        }
    }

}
