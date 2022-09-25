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
    public final String getEventName() {
        return getClass().getSimpleName();
    }
}
