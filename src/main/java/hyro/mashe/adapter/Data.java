package hyro.mashe.adapter;

import hyro.mashe.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * Record for Adapter's storage
 * <p>
 * Includes:
 * <li>{@link #method}</li>
 * <li>{@link #instance}</li>
 * <li>{@link #priority}</li>
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
@AllArgsConstructor
@Getter
public final class Data {
    private final Method method;
    private final Object instance;
    private final Priority priority;
}
