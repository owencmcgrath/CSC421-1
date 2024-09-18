import java.util.*;
import java.io.*;

/*
* a class that serves to take input and output for the knapsack class
* @author Owen McGrath
* @version 9/9/2024
*/
public class KnapsackDriver
{
    public static void main(String[] args) throws Exception
    {
        try (Scanner input = new Scanner(System.in))
        {
            //getting the weight limit
            System.out.println("What is the weight limit of the knapsack?: ");
            int sackWeight = input.nextInt();

            while (sackWeight <= 0)
            {
                System.out.println("Please enter a number greater than zero.");
                sackWeight = input.nextInt();
            }

            Knapsack knapsack = new Knapsack(sackWeight);

            //read file in, cleaning is done here
            System.out.print("Enter the item's file: ");
            String filename = input.next();

            while (filename.isEmpty())
            {
                System.out.println("Please try again.");
                filename = input.next();
            }

            Scanner infile = new Scanner(new File(filename));

            //this loops through the entire file and creates a knapsack item with weight, value, and descriptor.
            //from there, the add item is called, adding an item to an arraylist
            while (infile.hasNext())
            {
                String line = infile.nextLine(); //setting up a line so that each line can be scanned
                Scanner lineScanner = new Scanner(line); //calls the scanner method with the line var

                int value = lineScanner.nextInt();
                int weight = lineScanner.nextInt();
                String descriptor = lineScanner.next().trim();

                KnapsackItem item = new KnapsackItem(weight, value, descriptor);
                knapsack.addItem(item);

                lineScanner.close();
            }
            infile.close();

            //findinf the optimal values, also printing them here.
            Set<KnapsackItem> optimalItems = knapsack.findOptimalSubset();

            int totalWeight = 0;
            int totalValue = 0;

            for (KnapsackItem item : optimalItems)
            {
                totalWeight += item.getWeight();
                totalValue += item.getValue();
            }

            knapsack.outputOptimalItems(optimalItems);
            System.out.println("Total weight: " + totalWeight);
            System.out.println("Total value: " + totalValue);


            if (totalWeight == 0 || totalValue == 0 || optimalItems.isEmpty())
            {
                System.out.println("It looks like the final weight or value was zero, or there were no optimal items. Try increasing your max weight or making sure that your input file does not have any negatives.");
            }
        }
    }
}
