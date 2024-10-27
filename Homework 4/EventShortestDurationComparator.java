import java.util.Comparator;

public class EventShortestDurationComparator implements Comparator<Event> 
{
    @Override
    public int compare(Event e1, Event e2) 
    {
        int durationComparison = Long.compare(e1.getDuration(), e2.getDuration());
        if (durationComparison == 0) 
        {
            return Integer.compare(e1.getStartTime(), e2.getStartTime());
        }
        return durationComparison;
    }
}
