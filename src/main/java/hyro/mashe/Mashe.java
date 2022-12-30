package hyro.mashe;

import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.adapters.DefaultAdapter;
import hyro.mashe.annotations.Listen;
import hyro.mashe.enums.Priority;
import hyro.mashe.types.Event;
import hyro.mashe.types.Listener;
import lombok.Getter;
import lombok.Setter;

import java.lang.invoke.*;
import java.lang.reflect.InvocationTargetException;
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
    @Getter
    @Setter
    private Consumer<String> logger;

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final MethodType LISTENER_INVOKE_TYPE = MethodType.methodType(void.class, Event.class);

    /**
     * Create Mashe
     * <p></p>
     * If you have Eclipse Collections installed, it will use {@link hyro.mashe.adapter.adapters.EclipseCollectionsAdapter}
     * <p>
     * otherwise it use {@link DefaultAdapter}
     * <p>
     * For logging, it uses {@link System#out}
     *
     * @see #Mashe(Adapter)
     * @see #Mashe(Adapter, Consumer)
     * @since 0.1.0
     */
    public Mashe() {
        try {
            Class.forName("org.eclipse.collections.api.factory.Maps");
            setAdapter(
                    ((Adapter) Class.forName("hyro.mashe.adapter.adapters.EclipseCollectionsAdapter").getDeclaredConstructor().newInstance())
            );
        } catch (Exception ignored) {
            setAdapter(new DefaultAdapter());
        }

        setLogger(System.out::println);
    }

    /**
     * Create Mashe with specific adapter
     *
     * @param adapter {@link Adapter}
     * @see #Mashe()
     * @see #Mashe(Adapter, Consumer)
     * @since 0.1.0
     */
    public Mashe(Adapter adapter) {
        setAdapter(adapter);
        setLogger(System.out::println);
    }

    /**
     * Create Mashe with specific adapter and logger
     *
     * @param adapter {@link Adapter}
     * @param logger {@link Consumer}
     * @see #Mashe()
     * @see #Mashe(Adapter)
     * @since 0.1.1
     */
    public Mashe(Adapter adapter, Consumer<String> logger) {
        setAdapter(adapter);
        setLogger(logger);
    }

    /**
     * Register consumer as event handler
     * @param object {@link Event}
     * @param consumer {@link Consumer}
     *
     * @see #register(Class, Priority, Consumer)
     * @see #register(Object)
     * @since 0.1.0
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
     * @since 0.1.0
     */
    @SuppressWarnings("unchecked") // We know that it's safe
    public<T extends Event> void register(Class<T> object, Priority priority, Consumer<T> consumer) {
        getAdapter().register(
                object,
                event -> consumer.accept((T) event),
                priority
        );
    }

    /**
     * Register all methods in class with Listen annotation as event handlers
     * @param subscriber your class
     *
     * @see #register(Class, Consumer)
     * @see #register(Class, Priority, Consumer)
     * @since 0.1.0
     */
    public void register(Object subscriber) {
        MethodType factoryType = MethodType.methodType(Listener.class, subscriber.getClass());

        for (Method method : subscriber.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Listen.class)) {
                // TODO: message
                continue;
            };

            // TODO: add checks

            Listen info = method.getAnnotation(Listen.class);

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }

            try {
                MethodHandle handle = MethodHandles.lookup().unreflect(method);
                CallSite site = LambdaMetafactory.metafactory(
                        LOOKUP,
                        "invoke",
                        factoryType,
                        LISTENER_INVOKE_TYPE,
                        handle,
                        MethodType.methodType(void.class, parameterTypes[0])
                );

                Listener listener = (Listener) site.getTarget().invoke(subscriber);

                getAdapter().register(parameterTypes[0], listener, info.priority());
            } catch (Throwable e) {
                this.logger.accept(String.format(
                        "WARNING: %s: Can't use LambdaMetafactory for %s, switching to reflections",
                        subscriber.getClass().getName(), method.getName()
                ));

                // Use reflections
                method.setAccessible(true);

                Listener listener = event -> {
                    try {
                        method.invoke(subscriber, event);
                    } catch (InvocationTargetException | IllegalAccessException e1) {
                        // nothing to do
                    }
                };

                getAdapter().register(parameterTypes[0], listener, info.priority());
            }
        }
    }

    /**
     * Unregister your event
     *
     * @param subscriber your class - {@link Object}
     * @since 0.1.3
     */
    public void unregister(Object subscriber) {
        getAdapter().unregister(subscriber);
    }

    /**
     * Unregister your event
     *
     * @param listener main parameter - {@link Event}
     * @since 0.1.3
     */
    public<T extends Event> void unregister(Listener listener) {
        getAdapter().unregister(listener);
    }

    /**
     * Fires event with {@link Event}
     * @param event {@link Event}
     */
    public void fire(Event event) {
        getAdapter().fire(event);
    }
}
