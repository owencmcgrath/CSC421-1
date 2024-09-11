import java.util.*;
import java.io.*;

/*
 * A class that defines the reads in a file, stores the knapsack information, and returns the most valuable set
 * @author Owen McGrath
 * @version 9/11/2024
 */
public class Knapsack
{
    private static List<KnapsackItem> items = new ArrayList<>();

   /*
    * a method that reads in a text file and uses the scanner class to add each item and its attributes to an arraylist
    * @param file -> a text file to be read in
    * @return items -> an arraylist of all the items in a text file
    */
    public static List<KnapsackItem> addItem(String filename) throws FileNotFoundException
    {
        Scanner infile = new Scanner(new File(filename)); //infile for reading the file
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

    public Set<KnapsackItem> findOptimalSubset(int maxWeight)
    {
        Set<KnapsackItem> optimalItems = new HashSet<KnapsackItem>();
        int currentWeight = 0;

        while(currentWeight < maxWeight)
        {

        }
        return optimalItems;
    }
}
