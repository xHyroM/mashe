package hyro.mashe.adapter;

import hyro.mashe.enums.Priority;
import hyro.mashe.types.Event;
import hyro.mashe.types.Listener;

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
     * @since 0.1.0
     */
    void register(
            final Class<?> parameter,
            final Listener listener,
            final Priority priority,
            final Object object
    );

    /**
     * Implementation for unregistering event
     *
     * @param parameter main parameter - {@link Event}
     * @since 0.1.3
     */
    void unregister(
            final Object parameter
    );

    /**
     * Implementation for unregistering event
     *
     * @param listener your listener (consumer) - {@link Listener}
     * @since 0.1.3
     */
    void unregister(
            final Listener listener
    );

    /**
     * Implementation for sorting events by priority
     * @since 0.1.0
     */
    default void sort() {
        // Function for sorting - not required
    }

    /**
     * Implementation for firing event
     *
     * @param event {@link Event}
     * @since 0.1.0
     */
    void fire(final Event event);
}
