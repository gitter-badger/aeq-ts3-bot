package de.esports.aeq.ts3bot.command;

import com.beust.jcommander.Parameter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 24.07.2017.
 */
public class ApplicationAcceptCommand implements Command {

    /**
     * The ts3id of the teamspeak user.
     */
    @Parameter()
    private String ts3id;

    public ApplicationAcceptCommand(String ts3id) {
        this.ts3id = ts3id;
    }

    public @NotNull String getTs3id() {
        return ts3id;
    }

    public void setTs3id(@NotNull String ts3id) {
        this.ts3id = ts3id;
    }

}
