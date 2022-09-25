package hyro.mashe.types;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an cancellable event
 *
 * @see Event
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public abstract class CancellableEvent extends Event {
    @Getter
    @Setter
    private boolean cancelled;
}
