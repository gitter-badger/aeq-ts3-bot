package de.esports.aeq.ts3bot.application;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import de.esports.aeq.ts3bot.task.ApplicationAcceptTask;
import de.esports.aeq.ts3bot.task.TaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Class description.
 *
 * @author Lukas
 * @version 0.1
 * @since 16.07.2017
 */
public class ApplicationService {

    public static void scheduleAccept(String ts3Id) {
        scheduleAccept(ts3Id, null);
    }

    public static void scheduleAccept(@NotNull String ts3Id, @Nullable FutureCallback<Application> callback) {
        if (ts3Id == null) {
            throw new IllegalArgumentException("application cannot be null");
        }
        Callable<Application> task = new ApplicationAcceptTask(ts3Id);
        ListeningExecutorService service = TaskExecutor.service;
        ListenableFuture<Application> future = service.submit(task);
        Futures.addCallback(future, callback, service);
    }

}
