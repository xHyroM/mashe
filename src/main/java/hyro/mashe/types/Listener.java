package hyro.mashe.types;

/**
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
@FunctionalInterface
public interface Listener {
    void invoke(Event event);
}
