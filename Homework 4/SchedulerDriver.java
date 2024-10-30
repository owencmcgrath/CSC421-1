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
        Scheduler scheduler = new Scheduler(events);

        //generates each schedule based on the events and the instantiated comparator
        System.out.println("\n Shortest Event First:");
        System.out.println(" ---------------------");
        printSchedule(scheduler.generateSchedule(new EventShortestDurationComparator()));

        System.out.println("\n Longest Event First:");
        System.out.println(" ---------------------");
        printSchedule(scheduler.generateSchedule(new EventLongestDurationComparator()));
        
        System.out.println("\n Earliest Start-Time First:");
        System.out.println(" ---------------------");
        printSchedule(scheduler.generateSchedule(new EventStartTimeComparator()));

        System.out.println("\n Earliest End-Time First:");
        System.out.println(" ---------------------");
        printSchedule(scheduler.generateSchedule(new EventEndTimeComparator()));
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

                    //determines if the description starts with REQ and trims it if it is        
                    boolean isRequired = description.startsWith("REQ");
                    if (isRequired) 
                    {
                        description = description.substring(3).trim();
                    }

                    //creates a new event with the gotten strings
                    events.add(new Event(startTime, endTime, description, isRequired));
                }
            }
        }
        return events;
    }
    
    /**
    * Helper method that prints out each relevant generated schedule in the generatedSchedule method.
    * @param schedule -> the schedule to be printed
    * @author Owen McGrath
    * @version 10/18/2024
    */
    private static void 
    printSchedule(List<Event> schedule) 
    {
        for (Event event : schedule) 
        {
            System.out.println(event);
        }
    }
}
