import java.util.Scanner;

/**
 * Driver for generating strings that have the same statistical distribution of
 * letters and spaces as a specified file.
 *   @author Dave Reed, Owen McGrath
 *   @version 8/20/24
 */
public class ApproxDriver
{
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the file name: ");
        String filename = input.next();
        ApproxGenerator approx = new ApproxGenerator(filename);


        System.out.println("\nEnter the desired order (>= 1): ");
        int desiredOrder = input.nextInt();
        desiredOrder = approx.ensureValidOrder(desiredOrder, input); //make sure that the user entrs a valid order

        System.out.print("\nEnter the desired string length (negative to quit): ");
        int numChars = input.nextInt();
        numChars = approx.ensureValidNumChars(numChars, desiredOrder,input); //make sure that the user enters a valid number of characters

        approx.generateMap(desiredOrder);

        while (numChars >= 0)
        {
            System.out.println(approx.generate(numChars, desiredOrder));

            System.out.print("\nEnter the desired string length (negative to quit): ");
            numChars = input.nextInt();
            numChars = approx.ensureValidNumChars(numChars, desiredOrder,input); //make sure that the user enters a valid number of characters
        }
        System.out.println("**DONE**");
        input.close();
    }
}
