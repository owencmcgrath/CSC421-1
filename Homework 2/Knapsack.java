import java.util.*;
import java.io.*;

/*
* a class that defines the reads in a file, stores the knapsack information, and returns the most valuable set
* @author Owen McGrath
* @version 9/11/2024
*/
public class Knapsack
{
    private static final List<KnapsackItem> items = new ArrayList<>();
    private static int bestValue = 0; //best value is a class variable because its state needs to be persisted across methods (findOptimalSubset -> optimalItemsChecker)

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
            Scanner lineScanner = new Scanner(line); //calls the scanner method with the line var

            int value = lineScanner.nextInt();
            int weight = lineScanner.nextInt();
            String descriptor = lineScanner.next().trim();

            items.add(new KnapsackItem(weight, value, descriptor));
            lineScanner.close();
        }
        infile.close();
        return items;
    }

   /*
    * this is a method that find the optimal subset for the knapsack problem
    * @param maxWeight -> the maximum weight of the sack
    * @return optimalItems -> items that are most optimal for the input
    */
    public Set<KnapsackItem> findOptimalSubset(int maxWeight)
    {
        Set<KnapsackItem> optimalItems = new HashSet<>();
        String lengthOfItems = findStartingLength();


        while (true)
        {
            Set<KnapsackItem> currentItems = new HashSet<>(); //current items is used to keep track of what we are currently using
            int currentWeight = 0;
            int currentValue = 0;

            for (int i = items.size() - 1; i >= 0; i--)
            {
                if (lengthOfItems.charAt(i) == '1') //here, a one in the bit pattern denotes that an item should be "gotten"
                {
                    KnapsackItem item = items.get(i);
                    int weightOfItem = item.getWeight();
                    int valueOfItem = item.getValue();

                    if (currentWeight + weightOfItem <= maxWeight) //checking that the item that has been gotten does not push the sack over the limit :(
                    {
                        currentWeight += weightOfItem;
                        currentValue += valueOfItem;
                        currentItems.add(item);
                    }
                }
            }
            optimalItems = optimalItemsChecker(currentWeight, maxWeight, currentValue, currentItems, optimalItems); //an abstracted method for finding the most optimal value
            lengthOfItems = bitIterator(lengthOfItems); //another abstracted method that is used to iterate through the bit patterns

            if (lengthOfItems == null)
            {
                break;
            }
        }
        Knapsack.outputOptimalItems(optimalItems);
        return optimalItems;
    }

    /*
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

    /**
    * a method that finds the most optimal items based on information from findOptimalSubset
    * @param currentWeight -> current weight of the current item(s)
    * @param maxWeight -> the maxmium weight that the user input
    * @param currentValue -> the current value of the current item(s)
    * @param currentItems -> a set of all the current items
    * @param optimalItems -> a set of all the optimal items
    * @return optimalItems -> a set of all the optimal items
    */
    private static Set<KnapsackItem> optimalItemsChecker(int currentWeight, int maxWeight, int currentValue, Set<KnapsackItem> currentItems, Set<KnapsackItem> optimalItems)
    {
        if (currentWeight <= maxWeight && currentValue > bestValue) //if its less than the sack and greater than the best value...
        {
            bestValue = currentValue; //this is the reason that the best value cannot be a local variable
            optimalItems = new HashSet<>(currentItems); //... make it the optimal items
        }
        return optimalItems;
    }

   /*
    * a method that prints out the optimal items
    * @param optimalItems
    */
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

   /*
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

    /*
    public static int ensureValidWeight(int weight)
    {
        Scanner input = new Scanner(System.in);

        while (true) //loops infinetely until the weight and value are valid
        {
            try
            {
                weight = Integer.parseInt(weight);
            }
            catch (Exception e)
            {

            }
            if (weight / 1 != weight)
            {
                System.out.println("The value and the weight both have to be valid integers: ");
                weight = input.nextInt();
            }
            else
            {
                break; //if there is nothing to fix, just retun the original value
            }
        }
        input.close();
        return weight;
    }
    */
}
