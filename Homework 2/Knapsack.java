import java.io.*;
import java.util.*;

/**
* A class that defines the reads in a file, stores the knapsack information, and returns the most valuable set
* @author Owen McGrath
* @version 9/11/2024
*/
public class Knapsack
{
    private static final List<KnapsackItem> items = new ArrayList<>();

   /**
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

            int value = lineScanner.nextInt();
            int weight = lineScanner.nextInt();
            String descriptor = lineScanner.next().trim();

            items.add(new KnapsackItem(weight, value, descriptor));
            lineScanner.close();
        }
        infile.close();
        return items;
    }

    /**
     * this is a method that find the optimal subset for the knapsack problem
     * @param maxWeight -> the maximum weight of the sack
     * @return optimalItems -> items that are most optimal for the input 
     */
    public Set<KnapsackItem> findOptimalSubset(int maxWeight) 
    {
        Set<KnapsackItem> optimalItems = new HashSet<>();
        int bestValue = 0;
        String lengthOfItems = findStartingLength();
        int numItems = items.size();
        
        while (true) 
        {
            Set<KnapsackItem> currentItems = new HashSet<>();
            int currentWeight = 0;
            int currentValue = 0;
            
            for (int i = numItems - 1; i >= 0; i--) 
            {
                if (lengthOfItems.charAt(i) == '1') 
                {
                    KnapsackItem item = items.get(i);
                    int weightOfItem = item.getWeight();
                    int valueOfItem = item.getValue();

                    if (currentWeight + weightOfItem <= maxWeight) 
                    {
                        currentWeight += weightOfItem;
                        currentValue += valueOfItem;
                        currentItems.add(item);
                    }
                }
            }
            optimalItems = optimalItemsChecker(currentWeight, maxWeight, currentValue, bestValue, currentItems, optimalItems);

            lengthOfItems = bitIterator(lengthOfItems);

            if (lengthOfItems == null)
            {
                break;
            }
        }
        Knapsack.outputOptimalItems(optimalItems);
        return optimalItems;
    }

    /**
     * a method that iterates the current bitmap based on whether or not a zero is found
     * @param lengthOfItems -> the lenght of items that is being used in findOptimalSubset
     * @return new string or null -> if the variable is changed, return it, otherwise return null
     */
    private static String bitIterator(String lengthOfItems)
    {
        StringBuilder stringBuilder = new StringBuilder(lengthOfItems);
        
        for (int i = lengthOfItems.length() - 1; i >= 0; i--) 
        {
            if (stringBuilder.charAt(i) == '0') 
            {
                stringBuilder.setCharAt(i, '1');
                return stringBuilder.toString();
            } 
            else 
            {
                stringBuilder.setCharAt(i, '0');
            }
        }
        return null;
    }

    
    private static Set<KnapsackItem> optimalItemsChecker(int currentWeight, int maxWeight, int currentValue, int bestValue, Set<KnapsackItem> currentItems, Set<KnapsackItem> optimalItems)
    {
        if (currentWeight <= maxWeight && currentValue > bestValue) 
        {
        bestValue = currentValue;
        optimalItems = new HashSet<>(currentItems);
        }
        return optimalItems;
    }

    private static void outputOptimalItems(Set<KnapsackItem> optimalItems)
    {
        int totalWeight = 0;
        int totalValue = 0;

        for (KnapsackItem item : optimalItems) 
        {
            totalWeight += item.getWeight();
            totalValue += item.getValue();
            System.out.println(item.getDescriptor());
        }

        System.out.println("Total weight: " + totalWeight);
        System.out.println("Total value: " + totalValue);
    }

   /**
    * a method that takes the length of the items list and returns a string of zeroes to be added upon
    * return lengthOfItemsStored -> length of the list in zeroes.
    */
    private static String findStartingLength()
    {
        String lengthOfItems = "0";

        for (int i = 0; i < items.size(); i++) 
        {
            lengthOfItems += "0";
        }
        return lengthOfItems;
    }
}
