import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

/**
* Uses specific comparators to schedule events.
* @author Owen McGrath
* @version 10/18/2024
*/
public class 
Scheduler 
{   
    List<Event> requiredEvents = new ArrayList<>();
    List<Event> optionalEvents = new ArrayList<>();

    /**
    * Divides the events into required and optional lists.
    * @param events
    * @author Owen McGrath
    * @version 10/29/2024
    */
    public 
    Scheduler(List<Event> events)
    {
        //determines which list the event should go in
        for (Event event : events) 
        {
            if (event.isRequired()) 
            {
                requiredEvents.add(event);
            } 
            else
            {
                optionalEvents.add(event);
            }
        }       
    }
    
    /**
    * Creates the schedules based on the events, the comparator, and conflicts.
    * @param requiredEvents, optionalEvents, comparator
    * @return scheduledEvents -> a sorted schedule
    * @author Owen McGrath
    * @version 10/18/2024
    */
    public List<Event> 
    generateSchedule(Comparator<Event> comparator)
    {
        List<Event> remainingEvents = new ArrayList<>(optionalEvents); 
        //create the scheduledEvents list with the required events since they need to be in the list no matter what
        List<Event> scheduledEvents = new ArrayList<>(requiredEvents); 
        //sort the remainingEvents (all events) based on the comparator that the schedule has
        Collections.sort(remainingEvents, comparator);
        
        //events are put into a schedule if there no conflicts or if it is required
        for (Event event : remainingEvents) 
        {
            if (!hasConflicts(event, scheduledEvents)) 
            {
                scheduledEvents.add(event);
            } 
        }
        //sort the event based on start times
        Collections.sort(scheduledEvents);
        return scheduledEvents;
    }

    /**
    * Determines if there is a conflict between two events.
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
