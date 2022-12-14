package hyro.mashe.adapter;

import hyro.mashe.enums.Priority;
import hyro.mashe.types.Listener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Record for Adapter's storage
 * <p>
 * Includes:
 * <ol>
 * <li>{@link #listener}</li>
 * <li>{@link #priority}</li>
 * </ol>
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
@AllArgsConstructor
@Getter
@ToString
public final class Data {
    private final Listener listener;
    private final Priority priority;
    private final Object subscriber;
}
