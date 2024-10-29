import java.util.Comparator;

/**
* Compares events based on the longest duration.
* @author Owen McGrath
* @version 10/20/2024
*/
public class 
EventLongestDurationComparator 
implements Comparator<Event> 
{

    /**
    * Compares two events to find which one has the longest duration
    * @param event1, event2
    * @return Integer.compare(event1.getStartTime(), event2.getStartTime()), durationComparison
    * @author Owen McGrath
    * @version 10/20/2024
    */
    @Override
    public int 
    compare(Event event1, Event event2) 
    {
        long duration1 = event1.getDuration();
        long duration2 = event2.getDuration();
    
        int durationComparison = Long.compare(duration2, duration1);
        
        //result is a zero, startTimes are compared to determine which comes first 
        if (durationComparison == 0) 
        {
            return Integer.compare(event1.getStartTime(), event2.getStartTime());
        }
        return durationComparison;
    }
}
