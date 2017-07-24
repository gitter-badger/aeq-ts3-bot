package de.esports.aeq.ts3bot.handler;

import com.beust.jcommander.Parameter;
import org.apache.commons.cli.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 24.07.2017.
 */
public class ApplicationAcceptCommand {

    List<Option> options = new ArrayList<>();

    /**
     * The ts3id of the teamspeak user.
     */
    @Parameter()
    private String ts3id;

    public ApplicationAcceptCommand(String ts3id) {
        this.ts3id = ts3id;
    }

    public String getTs3id() {
        return ts3id;
    }

    public void setTs3id(String ts3id) {
        this.ts3id = ts3id;
    }
}
