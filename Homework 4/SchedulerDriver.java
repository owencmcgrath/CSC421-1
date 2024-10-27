import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class SchedulerDriver 
{
    public static 
    void main(String[] args) throws IOException 
    {
        System.out.print("Enter the file name: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();
        input.close();

        List<Event> events = readEventsFromFile(filename);
        List<Event> requiredEvents = new ArrayList<>();
        List<Event> optionalEvents = new ArrayList<>();

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

        Scheduler scheduler = new Scheduler();

        System.out.println("\nSchedule sorted by Start Time:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventStartTimeComparator()));

        System.out.println("\nSchedule sorted by End Time:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventEndTimeComparator()));

        System.out.println("\nSchedule sorted by Shortest Duration:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventShortestDurationComparator()));

        System.out.println("\nSchedule sorted by Longest Duration:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventLongestDurationComparator()));
    }
        
    private static List<Event> readEventsFromFile(String filename) throws IOException 
    {
        List<Event> events = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(filename)))
        {
            while (fileReader.hasNextLine()) {                        
                String line = fileReader.nextLine();
                try (Scanner lineScanner = new Scanner(line)) 
                {
                    String startTime = lineScanner.next();
                    String endTime = lineScanner.next();
                    String description = lineScanner.nextLine().trim();

                    boolean isRequired = description.startsWith("REQ");
                    if (isRequired) 
                    {
                        description = description.substring(3).trim();
                    }

                    events.add(new Event(startTime, endTime, description, isRequired));
                }
            }
        }
        return events;
    }

    private static void printSchedule(List<Event> schedule) 
    {
        for (Event event : schedule) {
            System.out.println(event);
        }
    }
}
