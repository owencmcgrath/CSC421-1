import java.util.Comparator;

public class EventStartTimeComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return Integer.compare(event1.getStartTime(), event2.getStartTime());
    }
}
