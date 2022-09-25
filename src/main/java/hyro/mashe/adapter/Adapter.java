package hyro.mashe.adapter;

import hyro.mashe.types.Event;
import hyro.mashe.enums.Priority;

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
    void register(
            final Object object,
            final Class<?> parameter,
            final Method method,
            final Priority priority
    );

    default void sort() {
        // Function for sorting - not required
    }

    void fire(final Event o);
}
