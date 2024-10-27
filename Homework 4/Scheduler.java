import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scheduler {     

    public List<Event> generateSchedule(List<Event> requiredEvents, List<Event> optionalEvents, Comparator<Event> comparator) {
    
        List<Event> allEvents = new ArrayList<>(requiredEvents);
        allEvents.addAll(optionalEvents);

        allEvents.sort(comparator);

        List<Event> scheduledEvents = new ArrayList<>();

        for (Event event : allEvents) 
        {
            if (!hasConflicts(event, scheduledEvents) || event.isRequired()) 
            {
                scheduledEvents.add(event);
            } 
        }
        return scheduledEvents;
    }

    private boolean hasConflicts(Event newEvent, List<Event> scheduledEvents) 
    {
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
