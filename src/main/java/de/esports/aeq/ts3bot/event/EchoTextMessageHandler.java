package de.esports.aeq.ts3bot.event;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.event.api.TextMessageHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoTextMessageHandler extends TextMessageHandler {

    private static final Logger log = LoggerFactory.getLogger(EchoTextMessageHandler.class);

    private TS3ApiAsync apiAsync;

    public EchoTextMessageHandler(TS3Api api, TS3ApiAsync apiAsync) {
        super(api, apiAsync);
    }

    @Override
    public void handle(@NotNull TextMessageEvent messageEvent) {
        int invokerId = messageEvent.getInvokerId();
        apiAsync.sendPrivateMessage(invokerId, messageEvent.getMessage()).onFailure(e -> {
            log.error("Could not echo reply message: {}", messageEvent.toString());
        });
    }

    public TS3ApiAsync getApiAsync() {
        return apiAsync;
    }

    public void setApiAsync(TS3ApiAsync apiAsync) {
        this.apiAsync = apiAsync;
    }
}
