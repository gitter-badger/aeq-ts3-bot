package de.esports.aeq.ts3bot.command.commands;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import de.esports.aeq.ts3bot.command.api.Command;
import de.esports.aeq.ts3bot.command.exception.CHandleException;
import de.esports.aeq.ts3bot.core.AeqTS3Bot;
import de.esports.aeq.ts3bot.message.Messages;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Lukas on 27.07.2017.
 */
public class CReject implements Command {

    private static final Logger log = LoggerFactory.getLogger(CReject.class);
    public static final String PREFIX = "reject";

    private AeqTS3Bot ts3Bot;

    public CReject(AeqTS3Bot ts3Bot) {
        this.ts3Bot = ts3Bot;
    }

    @Override
    public @NotNull String getPrefix() {
        return PREFIX;
    }

    @Override
    public void execute(TextMessageEvent e) throws CHandleException {
        // TODO(glains)
        log.debug("executing command {}", CReject.class.getSimpleName());
        String message = Messages.getTranslatedString(Messages.ERROR_NOT_IMPLEMENTED);
        ts3Bot.getApi().sendPrivateMessage(e.getInvokerId(), message);
    }
}
