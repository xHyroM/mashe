package hyro.mashe.adapter;

import hyro.mashe.types.Event;
import hyro.mashe.enums.Priority;
import hyro.mashe.types.Listener;

import java.lang.reflect.Method;

/**
 * Interface for Mashe adapters
 * <p>
 * Stores registered events
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public interface Adapter {
    /**
     * Implementation for registering event
     *
     * @param parameter main parameter - {@link Event}
     * @param listener {@link Listener}
     * @param priority {@link Priority}
     */
    void register(
            final Class<?> parameter,
            final Listener listener,
            final Priority priority
    );

    /**
     * Implementation for sorting events by priority
     */
    default void sort() {
        // Function for sorting - not required
    }

    /**
     * Implementation for firing event
     *
     * @param event {@link Event}
     */
    void fire(final Event event);
}
