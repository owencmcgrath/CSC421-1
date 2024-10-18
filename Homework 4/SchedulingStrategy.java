import java.util.List;

/**
* An interface that provides two abstract methods for every implemented scheduling strategy.
* @author Owen McGrath
* @version 10/18/2024 (1.0)
*/
public interface SchedulingStrategy
{
    String getName(); //returns the name of each strategy  
    List<Event> sortEvents(List<Event> events); //a list of events to be sorted
}
