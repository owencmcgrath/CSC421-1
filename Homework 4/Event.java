public class Event
{
    private final String startTime;
    private final String endTime;
    private final String description;
    private final boolean isRequired; 

    public Event(String startTime, String endTime, String description, boolean isRequired)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isRequired = isRequired;
    }

    public int getStartTime() 
    {
        return toMinutes(startTime);
    }

    public int getEndTime() 
    {
        return toMinutes(endTime);
    }

    public String getDescription() 
    {
        return description;
    }

    public boolean isRequired() 
    {
        return isRequired;
    }

    public long getDuration()
    {
        return toMinutes(endTime) - toMinutes(startTime); //duration helps find the time between two events
    }

    //checks if two evetnts are overlapping, and if they are, returns true
    public boolean overlapsWith(Event other)
    {
        int currentEventStart = toMinutes(this.startTime);
        int currentEventEnd = toMinutes(this.endTime);
        int otherEventStart = toMinutes(other.startTime);
        int otherEventEnd = toMinutes(other.endTime);        
        
        return currentEventStart < otherEventEnd && otherEventStart < currentEventEnd;
    }

    @Override
    public String toString()
    {
        return "Start: " + startTime + ", End: " + endTime + ", Description: " + description;
    }

    //private helper method to convert events into time since midnight in minutes
    private  int toMinutes(String time)
    {
        String[] hoursThenMinutes = time.split(":"); //split the time based on the semicoloon and put them into an array
        int hours = Integer.parseInt(hoursThenMinutes[0]); //first piece is the hours, converted by parseInt
        int minutes = Integer.parseInt(hoursThenMinutes[1]); //second part is the minutes, converted by parstInt
        int minutesSinceMidnight = hours * 60 + minutes; //converts it into total minutessincemidght
        return minutesSinceMidnight; 
    }
}
