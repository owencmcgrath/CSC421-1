import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* Uses specific comparators to schedule events.
* @author Owen McGrath
* @version 10/18/2024
*/
public class 
Scheduler 
{   

    /**
    * Creates the schedules based on the events, the comparator, and conflicts
    * @param requiredEvents, optionalEvents, comparator
    * @return scheduledEvents -> a sorted schedule
    * @author Owen McGrath
    * @version 10/18/2024
    */
    public 
    List<Event> generateSchedule(List<Event> requiredEvents, List<Event> optionalEvents, Comparator<Event> comparator)
    {

        //events are combined into the same list, and then sorted based on the comparator 
        List<Event> allEvents = new ArrayList<>(requiredEvents);
        List<Event> scheduledEvents = new ArrayList<>();
        allEvents.addAll(optionalEvents); 
        allEvents.sort(comparator);

        //events are put into a schedule if there no conflicts or if it is required
        for (Event event : allEvents) 
        {
            if (!hasConflicts(event, scheduledEvents) || event.isRequired()) 
            {
                scheduledEvents.add(event);
            } 
        }
        return scheduledEvents;
    }

    /**
    * Determines if there is a conflict between two events
    * @param newEvent, scheduledEvents
    * @return true, false
    * @author Owen McGrath
    * @version 10/19/2024
    */
    private boolean 
    hasConflicts(Event newEvent, List<Event> scheduledEvents) 
    {
        //for every event in the list, determine if it overlaps
        for (Event scheduledEvent : scheduledEvents) 
        {
            if (scheduledEvent.overlapsWith(newEvent)) 
            {
                return true;
            }
        }
        return false;
    }
}
