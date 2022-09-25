package hyro.mashe;

import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.adapters.DefaultAdapter;
import hyro.mashe.annotations.Listen;
import hyro.mashe.priority.Priority;
import hyro.mashe.types.Event;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class Mashe {
    @Getter
    @Setter
    private Adapter adapter;

    /**
     * Create Mashe
     * <p></p>
     * If you have Eclipse Collections installed, it will use {@link hyro.mashe.adapter.adapters.EclipseCollectionsAdapter}
     * <p>
     * otherwise it use {@link DefaultAdapter}
     *
     * @see #Mashe(Adapter)
     */
    public Mashe() {
        try {
            Class.forName("org.eclipse.collections.api.factory.Maps");
            setAdapter(((Adapter) Class.forName("hyro.mashe.adapter.adapters.EclipseCollectionsAdapter").getDeclaredConstructor().newInstance()));
        } catch (Exception ignored) {
            setAdapter(new DefaultAdapter());
        }
    }

    /**
     * Create Mashe with specific adapter
     *
     * @param adapter {@link Adapter}
     * @see #Mashe()
     */
    public Mashe(Adapter adapter) {
        setAdapter(adapter);
    }

    /**
     * Register consumer as event handler
     * @param object {@link Event}
     * @param consumer {@link Consumer}
     *
     * @see #register(Class, Priority, Consumer)
     * @see #register(Object)
     */
    public<T extends Event> void register(Class<T> object, Consumer<T> consumer) {
        register(object, Priority.LOW, consumer);
    }

    /**
     * Register consumer as event handler with specific priority
     * @param object {@link Event}
     * @param priority {@link Priority}
     * @param consumer {@link Consumer}
     *
     * @see #register(Class, Consumer)
     * @see #register(Object)
     */
    public<T extends Event> void register(Class<T> object, Priority priority, Consumer<T> consumer) {
        try {
            Method method = consumer.getClass().getDeclaredMethod("accept", Object.class);
            method.setAccessible(true);

            getAdapter().register(
                    consumer,
                    object,
                    method,
                    priority
            );
        } catch (NoSuchMethodException ignored) {
            // TODO: message
        }
    }

    /**
     * Register all methods in class with Listen annotation as event handlers
     * @param object your class
     *
     * @see #register(Class, Consumer)
     * @see #register(Class, Priority, Consumer)
     */
    public void register(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Listen.class)) {
                // TODO: message
                continue;
            };

            Listen info = method.getAnnotation(Listen.class);

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }

            method.setAccessible(true);

            getAdapter().register(object, (Class<? extends Event>) parameterTypes[0], method, info.priority());
        }
    }

    /**
     * Fires event with {@link Event}
     * @param event {@link Event}
     */
    public void fire(Event event) {
        getAdapter().fire(event);
    }
}
