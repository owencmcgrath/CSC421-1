import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
* Driver class that reads in file input and generates the schedules.
* @author Owen McGrath
* @version 10/18/2024
*/
public class
SchedulerDriver 
{
    public static 
    void main(String[] args) throws IOException 
    {
        //reads in file information
        System.out.print("Enter the file name: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();
        input.close();

        List<Event> events = readEventsFromFile(filename);
        List<Event> requiredEvents = new ArrayList<>();
        List<Event> optionalEvents = new ArrayList<>();

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

        Scheduler scheduler = new Scheduler();

        //generates each schedule based on the events and the temporary comparator
        System.out.println("\nShortest Event First:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventShortestDurationComparator()));

        System.out.println("\nLongest Event First:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventLongestDurationComparator()));
        
        System.out.println("\n Earliest Start-Time First:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventStartTimeComparator()));

        System.out.println("\n Earliest End-Time First:");
        printSchedule(scheduler.generateSchedule(requiredEvents, optionalEvents, new EventEndTimeComparator()));
    }

    /**
    * Reads in the file information, seperates it based on spacing, and determines what is required.
    * @param filename -> the file to be read in 
    * @return events -> list of created Event objects
    * @author Owen McGrath
    * @version 10/18/2024
    */
    private static 
    List<Event> readEventsFromFile(String filename) throws IOException 
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
    /**
    * A helper method that prints out each relevant the generated schedule in the generatedSchedule method.
    * @param schedule -> the schedule to be printed
    * @author Owen McGrath
    * @version 10/18/2024
    */
    private static void 
    printSchedule(List<Event> schedule) 
    {
        for (Event event : schedule) {
            System.out.println(event);
        }
    }
}
