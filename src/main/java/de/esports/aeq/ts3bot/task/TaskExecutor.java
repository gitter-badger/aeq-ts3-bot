package de.esports.aeq.ts3bot.task;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class TaskExecutor {

    public static final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors
            .newFixedThreadPool(4));

}
