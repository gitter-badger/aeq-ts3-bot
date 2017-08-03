package de.esports.aeq.ts3bot.core;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.*;
import de.esports.aeq.ts3bot.event.EchoTextMessageHandler;
import de.esports.aeq.ts3bot.event.GuestsClientJoinHandler;
import de.esports.aeq.ts3bot.event.api.PermissionWrapperHandler;
import de.esports.aeq.ts3bot.event.api.TS3EventHandler;
import de.esports.aeq.ts3bot.event.api.TextMessageHandler;
import org.jetbrains.annotations.NotNull;

public class AeqTS3Listener implements TS3Listener {

    private TS3EventHandler<TextMessageEvent> messageHandler;
    private TS3EventHandler<ClientJoinEvent> guestsClientJoinHandler;

    private TS3Api api;
    private TS3ApiAsync apiAsync;

    public AeqTS3Listener(@NotNull TS3Api api, @NotNull TS3ApiAsync apiAsync) {
        this.api = api;
        this.apiAsync = apiAsync;
        // for now we will just use an echo reply handler
        messageHandler = new PermissionWrapperHandler<>(new EchoTextMessageHandler(api, apiAsync));
        // TODO(glains): initialise the configuration from api (rather than using the default one)
        guestsClientJoinHandler = new PermissionWrapperHandler<>(new GuestsClientJoinHandler(api, apiAsync));
    }

    @Override
    public void onTextMessage(TextMessageEvent textMessageEvent) {
        messageHandler.handle(textMessageEvent);
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        guestsClientJoinHandler.handle(clientJoinEvent);
    }

    @Override
    public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {

    }

    @Override
    public void onServerEdit(ServerEditedEvent serverEditedEvent) {

    }

    @Override
    public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

    }

    @Override
    public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

    }

    @Override
    public void onClientMoved(ClientMovedEvent clientMovedEvent) {

    }

    @Override
    public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

    }

    @Override
    public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {
        // TODO: check existing channel events for deleted channel
    }

    @Override
    public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

    }

    @Override
    public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

    }

    @Override
    public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

    }

    public TS3Api getApi() {
        return api;
    }

    public void setApi(TS3Api api) {
        this.api = api;
    }
}
