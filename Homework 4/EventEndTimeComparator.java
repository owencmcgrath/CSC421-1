import java.util.Comparator;

public class EventEndTimeComparator implements Comparator<Event>
{
    @Override
    public int compare(Event e1, Event e2)
    {
        return e1.getEndTime().compareTo(e2.getEndTime());
    }
}
