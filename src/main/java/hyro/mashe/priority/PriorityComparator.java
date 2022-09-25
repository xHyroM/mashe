package hyro.mashe.priority;

import hyro.mashe.adapter.Data;

import java.util.Comparator;

/**
 * Sort Adapter's list by priority
 *
 * @author <a href="https://github.com/xHyroM">xHyroM</a>
 * @since 0.1.0
 */
public final class PriorityComparator implements Comparator {
    public int compare(final Object o1, final Object o2) {
        int p1 = 0;
        int p2 = 0;

        if (o1 instanceof Data && o2 instanceof Data) {
            p1 = ((Data) o1).getPriority().getSlot();
            p2 = ((Data) o2).getPriority().getSlot();
        } else if (o1 instanceof Priority && o2 instanceof Priority) {
            p1 = ((Priority) o1).getSlot();
            p2 = ((Priority) o2).getSlot();
        }

        if (p1 == p2) return 0;
        else if (p1 < p2) return 1;
        else return -1;
    }
}
