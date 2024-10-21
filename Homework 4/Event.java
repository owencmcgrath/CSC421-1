import java.time.Duration;

public class Event
{
    private final String startTime;
    private final String endTime;
    private final String description;
    private final String isRequired; 

    public Event(String startTime, String, endTime, String description, boolean isRequired)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.isRequired = isRequired;

        if (startTime.isAfter(endTime))
        {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }

    public LocalTime getStartTime() 
    {
        return startTime;
    }

    public LocalTime getEndTime() 
    {
        return endTime;
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
        return Duration.between(startTime, endTime).toMinutes(); //duration helps find the time between two events
    }
}
