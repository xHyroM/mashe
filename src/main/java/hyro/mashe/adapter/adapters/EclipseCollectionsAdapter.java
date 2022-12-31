package hyro.mashe.adapter.adapters;

import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.Data;
import hyro.mashe.enums.Priority;
import hyro.mashe.types.Event;
import hyro.mashe.types.Listener;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;

import java.util.Comparator;

/**
 * Adapter that uses Eclipse Collections
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class EclipseCollectionsAdapter implements Adapter {
    private final UnifiedMap<Class<?>, FastList<Data>> list = new UnifiedMap<>();

    @Override
    public void register(
            final Class<?> parameter,
            final Listener listener,
            final Priority priority,
            final Object object
    ) {
        list.computeIfAbsent(parameter, k -> new FastList<>()).add(new Data(listener, priority, object));

        this.sort();
    }

    @Override
    public void unregister(
            final Object parameter
    ) {
        for (FastList<Data> list : this.list.values()) {
            list.removeIf(data -> data.getSubscriber().equals(parameter));
        }
    }

    @Override
    public void unregister(
            final Listener listener
    ) {
        for (FastList<Data> list : this.list.values()) {
            list.removeIf(data -> data.getListener() == listener);
        }
    }

    @Override
    public void sort() {
        for (FastList<Data> list : this.list.values()) {
            list.sort(Comparator.<Data>comparingInt(o -> o.getPriority().getSlot()).reversed());
        }
    }

    @Override
    public void fire(final Event event) {
        FastList<Data> datas = this.list.get(event.getClass());
        if (datas == null) return;

        for (Data data : datas) {
            data.getListener().invoke(event);
        }
    }
}
