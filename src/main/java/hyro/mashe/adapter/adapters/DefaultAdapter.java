package hyro.mashe.adapter.adapters;

import hyro.mashe.types.Event;
import hyro.mashe.adapter.Adapter;
import hyro.mashe.adapter.Data;
import hyro.mashe.priority.Priority;
import hyro.mashe.priority.PriorityComparator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Adapter that uses HashMap and ArrayList
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class DefaultAdapter implements Adapter {
    private final HashMap<Class<? extends Event>, ArrayList<Data>> list = new HashMap<>();

    @Override
    public void register(
            final Object object,
            final Class<? extends Event> parameter,
            final Method method,
            Priority priority
    ) {
        if (this.list.containsKey(parameter)) {
            this.list.get(parameter).add(new Data(method, object, priority));
        } else {
            ArrayList<Data> methods = new ArrayList<>();
            methods.add(new Data(method, object, priority));
            this.list.put(parameter, methods);
        }

        this.sort();
    }

    @Override
    public void sort() {
        for (ArrayList<Data> list : this.list.values()) {
            list.sort(new PriorityComparator());
        }
    }

    @Override
    public void fire(final Event o) {
        if (!this.list.containsKey(o.getClass())) return;

        this.list.get(o.getClass()).forEach(e -> {
            try {
                e.getMethod().invoke(e.getInstance(), o);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
