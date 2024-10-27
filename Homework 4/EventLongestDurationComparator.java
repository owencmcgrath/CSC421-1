import java.util.Comparator;

public class EventLongestDurationComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        long duration1 = event1.getDuration();
        long duration2 = event2.getDuration();
        
        int durationComparison = Long.compare(duration2, duration1); // Sort by longest duration first
        if (durationComparison == 0) {
            return Integer.compare(event1.getStartTime(), event2.getStartTime());
        }
        
        return durationComparison;
    }
}
