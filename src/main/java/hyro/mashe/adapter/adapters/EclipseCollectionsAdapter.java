package hyro.mashe.adapter.adapters;

import hyro.mashe.types.Event;
import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.Data;
import hyro.mashe.enums.Priority;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Adapter that uses Eclipse Collections
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class EclipseCollectionsAdapter implements Adapter {
    private final UnifiedMap<Class<? extends Event>, FastList<Data>> list = new UnifiedMap<>();

    @Override
    public void register(
            final Object object,
            final Class<? extends Event> o,
            final Method method,
            Priority priority
    ) {
        if (list.containsKey(o)) {
            list.get(o).add(new Data(method, object, priority));
        } else {
            FastList<Data> methods = new FastList<>();
            methods.add(new Data(method, object, priority));
            list.put(o, methods);
        }

        this.sort();
    }

    @Override
    public void sort() {
        for (FastList<Data> list : this.list.values()) {
            list.sort(Comparator.<Data>comparingInt(o -> o.getPriority().getSlot()).reversed());
        }
    }

    @Override
    public void fire(final Event event) {
        if (!list.containsKey(event.getClass())) return;

        for (Data data : this.list.get(event.getClass())) {
            try {
                data.getMethod().invoke(data.getInstance(), event);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
