package hyro.mashe.adapter.adapters;

import hyro.mashe.types.Event;
import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.Data;
import hyro.mashe.enums.Priority;
import hyro.mashe.types.Listener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Adapter that uses HashMap and ArrayList
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class DefaultAdapter implements Adapter {
    private final HashMap<Class<?>, ArrayList<Data>> list = new HashMap<>();

    @Override
    public void register(
            final Class<?> parameter,
            final Listener listener,
            final Priority priority
    ) {
        list.computeIfAbsent(parameter, k -> new ArrayList<>()).add(new Data(listener, priority));

        this.sort();
    }

    @Override
    public void unregister(
            final Class<?> parameter
    ) {
        list.remove(parameter);
    }

    @Override
    public void unregister(
            final Listener listener
    ) {
        for (ArrayList<Data> list : this.list.values()) {
            list.removeIf(data -> data.getListener() == listener);
        }
    }

    @Override
    public void sort() {
        for (ArrayList<Data> list : this.list.values()) {
            list.sort(Comparator.<Data>comparingInt(o -> o.getPriority().getSlot()).reversed());
        }
    }

    @Override
    public void fire(final Event event) {
        ArrayList<Data> datas = this.list.get(event.getClass());
        if (datas == null) return;

        for (Data data : datas) {
            data.getListener().invoke(event);
        }
    }
}
