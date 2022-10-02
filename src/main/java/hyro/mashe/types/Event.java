package hyro.mashe.types;

import hyro.mashe.Mashe;
import hyro.mashe.enums.Priority;

import java.util.function.Consumer;

/**
 * Represents an event
 *
 * @see Mashe#register(Object)
 * @see Mashe#register(Class, Consumer)
 * @see Mashe#register(Class, Priority, Consumer)
 * @see Mashe#fire(Event)
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public abstract class Event {
    /**
     * Convenience method for providing a user-friendly identifier. By
     * default, it is the event's class's {@link Class#getSimpleName()
     * simple name}.
     *
     * @return event's name
     */
    public final String getEventName() {
        return getClass().getSimpleName();
    }
}
