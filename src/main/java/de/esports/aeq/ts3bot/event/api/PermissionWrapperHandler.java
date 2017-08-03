package de.esports.aeq.ts3bot.event.api;

import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.BaseEvent;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 03.08.2017
 */
public class PermissionWrapperHandler<T extends BaseEvent> extends TS3EventHandler<T> {

    private Logger log = LoggerFactory.getLogger(PermissionWrapperHandler.class);

    private static final String[] whitelistedIds = {
            "1lwtSCSAMm5D1wIbViYa7G0Ho2I=",
            "h54PGmeQlaOaGabuYVAyfqlUCWI="
    };

    private TS3EventHandler<T> handler;

    public PermissionWrapperHandler(TS3EventHandler<T> handler) {
        super(handler.api, handler.apiAsync);
        this.handler = handler;
    }

    @Override
    public void handle(T event) {
        log.debug("checking permissions for event {}", event.getClass().getSimpleName());
        if (event instanceof TextMessageEvent) {
            TextMessageEvent textMessageEvent = (TextMessageEvent) event;
            if (textMessageEvent.getTargetMode().equals(TextMessageTargetMode.CLIENT)) {
                List<String> validIds = Arrays.asList(whitelistedIds);
                for (String s : validIds) {
                    if (s.equals(event.getInvokerUniqueId())) {
                        if (handler != null) {
                            log.debug("passed permission check", event.getClass().getSimpleName());
                            handler.handle(event);
                        }
                        break;
                    }
                }
            }
        }
    }
}
