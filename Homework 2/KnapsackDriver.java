import java.util.*;

public class KnapsackDriver
{
    public static void main(String[] args) throws Exception
    {
        try (Scanner input = new Scanner(System.in))
        {
            Knapsack knapsack = new Knapsack();

            //getting the weight limit
            System.out.println("What is the weight limit of the knapsack?: ");
            int sackWeight = input.nextInt();
            //sackWeight = Knapsack.ensureValidWeight(sackWeight);

            //read file in, cleaning is done here
            System.out.print("Enter the item's file: ");
            String filename = input.next();
            Knapsack.addItem(filename);

            //findinf the optimal values, also printing them here.
            knapsack.findOptimalSubset(sackWeight);
        }
    }
}
