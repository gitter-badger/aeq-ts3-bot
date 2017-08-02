package de.esports.aeq.ts3bot.event.api;

/**
 * An event handler is responsible to handle a given event.
 * <p>
 * This interface is intended to be extended by interfaces, which specify the exact event to be handled.
 *
 * @param <T> the type of the event to handle
 * @author Lukas Kannenberg
 * @since 02.08.2017
 */
@FunctionalInterface
public interface EventHandler<T> {

    /**
     * Handles the given event.
     *
     * @param event the event to handle
     */
    void handle(T event);
}
