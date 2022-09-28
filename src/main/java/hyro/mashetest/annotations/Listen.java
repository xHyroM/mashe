package hyro.mashetest.annotations;

import hyro.mashetest.enums.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods as being event handler methods
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listen {
    /**
     * Define the priority of the event.
     * <p>
     * First priority to the last priority executed:
     * <ol>
     * <li>LOWEST
     * <li>LOW
     * <li>NORMAL
     * <li>HIGH
     * <li>HIGHEST
     * <li>MONITOR
     * </ol>
     *
     * @return the priority of the event
     */
    Priority priority() default Priority.NORMAL;
}
