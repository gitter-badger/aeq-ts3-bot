package de.esports.aeq.ts3bot.task;

import com.sun.istack.internal.NotNull;
import de.esports.aeq.ts3bot.application.Application;

import java.util.concurrent.Callable;

/**
 * Class description.
 *
 * @author Lukas Kannenberg
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationAcceptTask implements Callable<String> {

    /**
     * The application to be accepted.
     */
    private String ts3Id;

    public ApplicationAcceptTask(@NotNull String ts3Id) {
        if (ts3Id == null)
            throw new IllegalArgumentException("application cannot be null");
        this.ts3Id = ts3Id;
    }

    @Override
    public String call() throws Exception {
        return null;
    }

}
