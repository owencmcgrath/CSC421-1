import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventReader
{
    public static 
    List<Event> readEventsFromFile(String filename) throws IOException
    {
        List<Event> events = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename)); //this reads the entire file contents and stores each newline as a String.

        for (String line : lines)
        {
            Scanner scanner = new Scanner(line);
            
            String startTime = scanner.next();
            String endTime = scanner.next();
            String description = scanner.nextLine().trim();

            //necessary to check if REQ is in the description
            boolean isRequired = false;
            if (description.startsWith("REQ"))
            {
                isRequired = true;
                description = description.substring(3).trim(); //remove "req" from the description
            }

            //create a new event object with the information we have gathered.
            events.add(new Event(startTime, endTime, description, isRequired));
        }
        return events; 
    }    
}
