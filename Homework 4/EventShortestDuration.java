import java.util.Comparator;

public class EventShortestDuration implements Comparator<Event>
{
    @Override
    public int compare(Event e1, Event e2)
    {
        long duration1 = e1.getDuration();
        long duration2 = e2.getDuration();
        return Long.compare(duration1, duration2);
    }
}
