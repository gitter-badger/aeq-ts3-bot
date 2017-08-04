package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 04.08.2017
 */
public class PrivilegedMessageHandler implements TS3Listener {

    private Logger log = LoggerFactory.getLogger(PrivilegedMessageHandler.class);

    private TS3Listener ts3Listener;
    private List<String> whitelistedIds = new ArrayList<>();

    public PrivilegedMessageHandler(TS3Listener ts3Listener) {
        this.ts3Listener = ts3Listener;
        initWhitelist();
    }

    private void initWhitelist() {
        whitelistedIds.add("1lwtSCSAMm5D1wIbViYa7G0Ho2I=");
        whitelistedIds.add("h54PGmeQlaOaGabuYVAyfqlUCWI=");
    }

    @Override
    public void onTextMessage(TextMessageEvent textMessageEvent) {
        log.debug("text message from {} requires permission check, validating...", textMessageEvent.getInvokerName());
        for (String s : whitelistedIds) {
            if (s.equals(textMessageEvent.getInvokerUniqueId())) {
                log.debug("passed permission check, delegating message to default handler");
                ts3Listener.onTextMessage(textMessageEvent);
                return;
            }
        }
        log.debug("permission check failed, client is not whitelisted");
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {

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
}
