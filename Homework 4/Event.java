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
    getStartTime() 
    {
        return toMinutes(startTime);
    }

    public int 
    getEndTime() 
    {
        return toMinutes(endTime);
    }

    public 
    String getDescription() 
    {
        return description;
    }

    public boolean 
    isRequired() 
    {
        return isRequired;
    }

    //computing the duration by converting the end time and start time to time since midnight and subtracting them
    public long getDuration()
    {
        return toMinutes(endTime) - toMinutes(startTime);
    }

    //checks if two events are overlapping, and if they are, returns true
    public boolean 
    overlapsWith(Event other)
    {
        int currentEventStart = toMinutes(this.startTime);
        int currentEventEnd = toMinutes(this.endTime);
        int otherEventStart = toMinutes(other.startTime);
        int otherEventEnd = toMinutes(other.endTime);        
        
        return currentEventStart < otherEventEnd && otherEventStart < currentEventEnd;
    }

    //prints out each event with the original start time/end time (in HH:mm)
    @Override
    public 
    String toString()
    {
        return " " + startTime + " " + endTime + " " + description;
    }

    //create a default, comaprable that schedules them based on their start tim
    @Override
    public int 
    compareTo(Event other) 
    {
        return Integer.compare(this.getStartTime(), other.getStartTime());
    }

    //private helper method to convert events into time since midnight in minutes
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
