import java.util.Comparator;

public class EventEndTimeComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        return Integer.compare(e1.getEndTime(), e2.getEndTime());
    }
}
