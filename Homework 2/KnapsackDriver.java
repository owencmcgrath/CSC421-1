import java.util.*;

public class KnapsackDriver
{
    public static void main(String[] args) throws Exception
    {
        try (Scanner input = new Scanner(System.in)) 
        {
            //getting the weight limit
            System.out.println("What is the weight limit of the knapsack?: ");
            int sackWeight = input.nextInt();
            
            //read file in, cleaning is done here
            System.out.print("Enter the item's file: ");
            String filename = input.next();
            
            Knapsack knapsack = new Knapsack();
            Knapsack.addItem(filename);
            System.out.println(knapsack.findOptimalSubset(sackWeight));
        }
    }
}
