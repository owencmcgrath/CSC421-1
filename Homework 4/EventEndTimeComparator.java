import java.util.Comparator;

/**
* Compares events based on their respective end times.
* @author Owen McGrath
* @version 10/20/2024
*/
public class 
EventEndTimeComparator 
implements Comparator<Event> 
{
    /**
    * Compares event1 with event2 based on their end times.
    * @param event1, event2
    * @return Integer.compare(event1.getEndTime(), event2.getEndTime())
    * @author Owen McGrath
    * @version 10/20/2024
    */
    @Override
    public int 
    compare(Event event1, Event event2) 
    {
        return Integer.compare(event1.getEndTime(), event2.getEndTime());
    }
}
