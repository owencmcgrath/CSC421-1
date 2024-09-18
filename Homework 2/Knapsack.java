import java.util.*;

/*
* a class that the adds items, stores the knapsack information, and returns the most valuable set
* @author Owen McGrath
* @version 9/11/2024
*/
public class Knapsack
{
    private final List<KnapsackItem> items = new ArrayList<>();
    private int bestValue = 0;
    private int maxWeight;

   /*
    * constructor to initialize max weight
    * @param maxWeight
    */
    public Knapsack(int maxWeight)
    {
        this.maxWeight = maxWeight;
    }

   /*
    * a method that add as a KnapsackItem to an arraylist of items.
    * @param item -> a knapsack item
    */
    public void addItem(KnapsackItem item)
    {
        items.add(item);
    }

   /*
    * this is a method that find the optimal subset for the knapsack problem
    * @param maxWeight -> the maximum weight of the sack
    * @return optimalItems -> items that are most optimal for the input
    */
    public Set<KnapsackItem> findOptimalSubset()
    {
        Set<KnapsackItem> optimalItems = new HashSet<>();
        String bitmapForNumberOfItems = findStartingLength();

        while (bitmapForNumberOfItems != null)
        {
            Set<KnapsackItem> currentItems = new HashSet<>(); //current items is used to keep track of what we are currently using
            int currentWeight = 0;
            int currentValue = 0;

            for (int i = items.size() - 1; i >= 0; i--)
            {
                if (bitmapForNumberOfItems.charAt(i) == '1') //here, a one in the bit pattern denotes that an item should be "gotten"
                {
                    KnapsackItem item = items.get(i);
                    int weightOfItem = item.getWeight();
                    int valueOfItem = item.getValue();

                    int results[] = weightChecker(currentWeight, weightOfItem, currentValue, valueOfItem, item, currentItems);
                    currentWeight = results[0]; //update the value of weight from the returned array
                    currentValue = results[1]; //update the value of value from the returned array
                }
            }
            optimalItems = optimalItemsChecker(currentWeight, maxWeight, currentValue, currentItems, optimalItems); //an abstracted method for finding the most optimal value
            bitmapForNumberOfItems = bitIterator(bitmapForNumberOfItems); //another abstracted method that is used to iterate through the bit patterns
        }
        return optimalItems;
    }

   /*
    * a method that prints out the optimal items
    * @param optimalItems
    */
    public void outputOptimalItems(Set<KnapsackItem> optimalItems)
    {
        for (KnapsackItem item : optimalItems)
        {
            System.out.println(item.getDescriptor());
        }
    }

   /*
    * a method that takes the length of the items list and returns a string of zeroes to be added upon
    * return bitmapForNumberOfItemsStored -> length of the list in zeroes.
    */
    private String findStartingLength()
    {
        String bitmapForNumberOfItems = "0";

        for (int i = 0; i < items.size(); i++)
        {
            bitmapForNumberOfItems += "0"; //for every item in the items arraylist, add a zero, creating a bitmap
        }
        return bitmapForNumberOfItems;
    }

   /*
    * a helper method that performs validation for the whether or not the addition of a new item puts the weight of the sack over the limit
    * @param currentWeight -> weight of the current items
    * @param weightOfItem -> weight of the new items
    * @param currentValue -> value of the current items
    * @param valueOfItem -> value of the the new item
    * @param item -> the item object
    * @param currentItems -> a set of the current items
    * @return a new array containing the curent weight and the current value
    */
    private int[] weightChecker(int currentWeight, int weightOfItem, int currentValue, int valueOfItem, KnapsackItem item, Set<KnapsackItem> currentItems)
    {
        if (currentWeight + weightOfItem <= maxWeight) //checking that the item that has been gotten does not push the sack over the limit
        {
            currentWeight += weightOfItem;
            currentValue += valueOfItem;
            currentItems.add(item);
        }
        return new int[]{currentWeight, currentValue}; //have to create an array with both of these values so that they can be reutrned together
    }

   /*
    * a method that finds the most optimal items based on information from findOptimalSubset
    * @param currentWeight -> current weight of the current item(s)
    * @param maxWeight -> the maxmium weight that the user input
    * @param currentValue -> the current value of the current item(s)
    * @param currentItems -> a set of all the current items
    * @param optimalItems -> a set of all the optimal items
    * @return optimalItems -> a set of all the optimal items
    */
    private Set<KnapsackItem> optimalItemsChecker(int currentWeight, int maxWeight, int currentValue, Set<KnapsackItem> currentItems, Set<KnapsackItem> optimalItems)
    {
        if (currentWeight <= maxWeight && currentValue > bestValue) //if its less than the sack weight and greater than the best value...
        {
            bestValue = currentValue;
            optimalItems = new HashSet<>(currentItems); //... make the current items the optimal items.
        }
        return optimalItems;
    }

   /*
    * a method that iterates the current bitmap based on whether or not a zero is found
    * @param bitmapForNumberOfItems -> the lenght of items that is being used in findOptimalSubset
    * @return new string or null -> if the variable is changed, return it, otherwise return null
    */
    private String bitIterator(String bitmapForNumberOfItems)
    {
        StringBuilder stringBuilder = new StringBuilder(bitmapForNumberOfItems); //string builder breaks a string down into a mutable series of characters, great for manipulating strings

        for (int i = bitmapForNumberOfItems.length() - 1; i >= 0; i--) //iteratre over the starting bitmap of zeroes, from the back.
        {
            if (stringBuilder.charAt(i) == '0') //if the bitmap shows a zero...
            {
                stringBuilder.setCharAt(i, '1'); //make it a one...
                return stringBuilder.toString(); //converting the stringbuilder back into a string
            }
            else
            {
                stringBuilder.setCharAt(i, '0'); //... if it is a one, set it back to a zero
            }
        }
        return null; //if it is entirely ones, then just return null
    }
}
