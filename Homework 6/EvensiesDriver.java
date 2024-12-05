import java.util.Scanner;

/**
 * This class is the driver class for the Evensies game.
 * It prompts the user to enter the number of tokens and rounds adn then calculates based on the selected method.
 * @author Owen McGrath
 * @version 11/19/2024
 */
public class
EvensiesDriver
{
    /**
    * Main method that takes the input/output for the program
    * @param args arguments
    */
    public static
    void main
    (String[] args)
    {
        try (Scanner scan = new Scanner(System.in))
        {
            System.out.println("Enter the number of tokens and rounds, respectively: ");
            int tokens = scan.nextInt();
            int rounds = scan.nextInt();

            System.out.println("Choose the method to calculate expected tokens:");
            System.out.println("0. All Three");
            System.out.println("1. Top-Down");
            System.out.println("2. Bottom-Up");
            System.out.println("3. Top-Down with Caching: ");
            int choice = scan.nextInt();

            switch (choice)
            {
                case 0 -> runAllMethods(tokens, rounds);
                case 1 -> runTopDown(tokens, rounds);
                case 2 -> runBottomUp(tokens, rounds);
                case 3 -> runCaching(tokens, rounds);
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /**
     * Runs all three methods
     * @param tokens the number of tokens
     * @param rounds the number of rounds
     * @param timer the timer object
     */
    private static
    void runAllMethods
    (int tokens, int rounds)
    {
        runTopDown(tokens, rounds);
        runBottomUp(tokens, rounds);
        runCaching(tokens, rounds);
    }

    /**
     * Runs the top-down method
     * @param tokens the number of tokens
     * @param rounds the number of rounds
     * @param timer the timer object
     */
    private static
    void runTopDown
    (int tokens, int rounds)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        System.out.println("Top-Down: " + Evensies.expectedTopDown(tokens, rounds));
        timer.stop();
        System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
    }

    /**
     * Runs the bottom-up method
     * @param tokens the number of tokens
     * @param rounds the number of rounds
     * @param timer the timer object
     */
    private static
    void runBottomUp
    (int tokens, int rounds)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        System.out.println("Bottom-Up: " + Evensies.expectedBottomUp(tokens, rounds));
        timer.stop();
        System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
    }

    /**
     * Runs the top-down method with caching
     * @param tokens the number of tokens
     * @param rounds the number of rounds
     * @param timer the timer object
     */
    private static
    void runCaching
    (int tokens, int rounds)
    {
        StopWatch timer = new StopWatch();
        timer.start();
        System.out.println("Top-Down with Caching: " + Evensies.expectedCaching(tokens, rounds));
        timer.stop();
        System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
    }
}
