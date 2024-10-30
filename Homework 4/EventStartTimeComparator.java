import java.util.Comparator;

/**
* Compares events based on the start times.
* @author Owen McGrath
* @version 10/20/2024
*/
public class 
EventStartTimeComparator 
implements Comparator<Event> 
{
    /**
    * Compares two events to find which one has the earliest start time
    * @param event1, event2
    * @return return return Integer.compare(event1.getStartTime(), event2.getStartTime());
    * @author Owen McGrath
    * @version 10/20/2024
    */
    @Override
    public int 
    compare(Event event1, Event event2) 
    {
        return Integer.compare(event1.getStartTimeInMinutes(), event2.getStartTimeInMinutes());
    }
}
