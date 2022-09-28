package hyro.mashetest.adapter;

import hyro.mashetest.types.Event;
import hyro.mashetest.enums.Priority;
import hyro.mashetest.types.Listener;

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
            final Priority priority
    );

    /**
     * Implementation for unregistering event
     *
     * @param parameter main parameter - {@link Event}
     * @since 0.1.3
     */
    void unregister(
            final Class<?> parameter
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