package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EchoTextMessageHandler extends TS3EventAdapter {

    private static final Logger log = LoggerFactory.getLogger(EchoTextMessageHandler.class);

    private AeqTS3Bot ts3Bot;

    @Autowired
    public EchoTextMessageHandler(AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public void onTextMessage(TextMessageEvent e) {
        super.onTextMessage(e);
        if (e.getTargetMode() != TextMessageTargetMode.CLIENT) return;
        log.debug("received private message from client {}: {}", e.getInvokerName(), e.getMessage());
        if (!ts3Bot.getApi().sendPrivateMessage(e.getInvokerId(), e.getMessage())) {
            log.warn("could not echo reply to {}", e.getInvokerName());
        } else {
            log.debug("successfully echo replied to {}", e.getInvokerName());
        }
    }
}
