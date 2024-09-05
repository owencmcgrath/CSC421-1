import java.util.Scanner;

/*
 * Driver for generating strings that have the same statistical distribution of
 * letters and spaces as a specified file.
 * @author Dave Reed, Owen McGrath
 * @version 8/20/24
 */
public class ApproxDriver
{
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);

        //read file in, cleaning is done here
        System.out.print("Enter the file name: ");
        String filename = input.next();
        ApproxGenerator approx = new ApproxGenerator(filename);

        // get the order
        System.out.println("\nEnter the desired order (>= 1): ");
        int desiredOrder = input.nextInt();
        desiredOrder = approx.ensureValidOrder(desiredOrder, input); //make sure that the user entrs a valid order

        //get the number of characters
        System.out.print("\nEnter the desired string length (negative to quit): ");
        int numChars = input.nextInt();
        numChars = approx.ensureValidNumChars(numChars, desiredOrder,input); //make sure that the user enters a valid number of characters

        approx.generateMap(desiredOrder);

        while (numChars >= 0)
        {
            System.out.println(approx.generate(numChars, desiredOrder));

            System.out.print("\nEnter the desired string length (negative to quit): ");
            numChars = input.nextInt();
            numChars = approx.ensureValidNumChars(numChars, desiredOrder,input); //make sure that the user enters a valid number of characters. this is called again to ensure that, if after the initial getting of the number of characters the user enters an invalid number, they iwll be reprompted.
        }
        System.out.println("**DONE**");
        input.close();
    }
}
