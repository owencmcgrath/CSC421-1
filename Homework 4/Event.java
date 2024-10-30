/**
* An object that encompasses the Event class.
* @author Owen McGrath
* @version 10/17/202
*/
public class 
Event
implements
Comparable <Event>
{
    private final String startTime;
    private final String endTime;
    private final String description;
    private final boolean isRequired; 

    /**
    * Instantiatiates the event object with the relevant fields
    * @param startTime, endTime, description, isRequired
    * @author Owen McGrath
    * @version 10/20/2024
    */
    public 
    Event(String startTime, String endTime, String description, boolean isRequired)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isRequired = isRequired;
    }

    //getters for important variables, converted to minutes after creation so that the original time is retained
    public int 
    getStartTimeInMinutes() 
    {
        return toMinutes(startTime);
    }

    public int 
    getEndTimeInMinutes() 
    {
        return toMinutes(endTime);
    }

    public String 
    getDescription() 
    {
        return description;
    }

    public boolean 
    isRequired() 
    {
        return isRequired;
    }

    /**
    * Calculates the duration of an event
    * @return duration
    * @author Owen McGrath
    * @version 10/20/2024
    */
    public long getDuration()
    {
        int duration = toMinutes(endTime) - toMinutes(startTime);
        return duration;
    }

    /**
    * Determines whether or not an event overlaps with another
    * @param other 
    * @return overlapsWith
    * @author Owen McGrath
    * @version 10/21/2024
    */
    public boolean 
    overlapsWith(Event other)
    {
        int currentEventStart = toMinutes(this.startTime);
        int currentEventEnd = toMinutes(this.endTime);
        int otherEventStart = toMinutes(other.startTime);
        int otherEventEnd = toMinutes(other.endTime);        
        
        boolean overlapsWith =  currentEventStart < otherEventEnd && otherEventStart < currentEventEnd;
        return overlapsWith;
    }

    /**
    * Returns a formatted string for an event.
    * @return formatted string
    * @author Owen McGrath
    * @version 10/20/2024
    */
    @Override
    public 
    String toString()
    {
        String formattedString =  " " + startTime + " " + endTime + " " + description;
        return formattedString;
    }

    /**
    * A comparator that compares two events based on their start times.
    * @param other
    * @return comparedEvents
    * @author Owen McGrath
    * @version 10/28/2024
    */
    @Override
    public int 
    compareTo(Event other) 
    {
        int comparedEvents = Integer.compare(this.getStartTimeInMinutes(), other.getStartTimeInMinutes());
        return comparedEvents;
    }

    /**
    * Splits the times based on the colon and converts them to integers.
    * @param time
    * @return minutesSinceMidnight
    * @author Owen McGrath
    * @version 10/20/2024
    */
    private int 
    toMinutes(String time)
    {
        String[] hoursThenMinutes = time.split(":"); //split the time on the semicolon and put them into an array
        int hours = Integer.parseInt(hoursThenMinutes[0]); //first piece is the hours, converted by parseInt
        int minutes = Integer.parseInt(hoursThenMinutes[1]); //second part is the minutes, converted by parstInt
        int minutesSinceMidnight = hours * 60 + minutes; //converts it into total minutes since midnight
        return minutesSinceMidnight; 
    }
}
