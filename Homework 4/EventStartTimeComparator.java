import java.util.Comparator;

public class EventStartTimeComparator implements Comparator<Event>
{
    @Override
    public int compare(Event e1, Event e2)
    {
        return e1.getStartTime().compareTo(e2.getStartTime());   
    }
}
