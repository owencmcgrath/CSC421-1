import java.util.Scanner;

/**
 * Driver for generating strings that have the same statistical distribution of
 * letters and spaces as a specified file.
 *   @author Dave Reed
 *   @version 8/20/24
 */
public class ApproxDriver
{
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file name: ");
        String filename = input.next();

        System.out.println("\nEnter the desired order (>= 1): ");
        int desiredOrder = input.nextInt();

        ApproxGenerator approx = new ApproxGenerator(filename, desiredOrder);

        approx.generateMap(desiredOrder);

        System.out.print("\nEnter the desired string length (negative to quit): ");
        int numChars = input.nextInt();

        //you cannot have a string of length n and seed of n + >1
        //so we must set the desired order to the length of the string
        if (numChars < desiredOrder)
        {
            desiredOrder = numChars;
        }

        while (numChars >= 0)
        {
            System.out.println(approx.generate(numChars, desiredOrder));

            System.out.print("\nEnter the desired string length (negative to quit): ");
            numChars = input.nextInt();
        }
        System.out.println("**DONE**");
        input.close();
    }
}
