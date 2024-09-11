import java.util.*;
import java.io.*;

/*
 * A class that defines the KnapsackItem type
 * @author Owen McGrath
 * @version 9/9/2024
 */
public class KnapsackItem
{
    private int weight;
    private int value;
    private String descriptor;

    public KnapsackItem(int weight, int value, String descriptor)
    {
        this.weight = weight;
        this.value = value;
        this.descriptor = descriptor;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getValue()
    {
        return value;
    }

    public String getDescriptor()
    {
        return descriptor;
    }

   /*
    * a method that reads in a text file and uses the scanner class to add each item and its attributes to an arraylist
    * @param file -> a text file to be read in
    * @return items -> an arraylist of all the items in a text file
    */
    public static List<KnapsackItem> readList(String file) throws FileNotFoundException
    {
        Scanner infile = new Scanner(new File(file)); //infile for reading the file
        List<KnapsackItem> items = new ArrayList<>();

        while (infile.hasNext())
        {
            String line = infile.nextLine(); //setting up a line so that each line can be scanned
            Scanner lineScanner = new Scanner(line); //sets a variable so that each line is scanned

            int weight = lineScanner.nextInt();
            int value = lineScanner.nextInt();
            String descriptor = lineScanner.next().trim();

            items.add(new KnapsackItem(weight, value, descriptor));
            lineScanner.close();
        }
        infile.close();

        return items;
    }
}
