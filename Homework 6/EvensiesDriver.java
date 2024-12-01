import java.util.Scanner;

/**
 * This class is the driver class for the Evensies game. It prompts the user to enter the number of tokens and rounds
 * @author Owen McGrath
 * @version 11/19/2024
 */
public class
EvensiesDriver
{
  public static
  void main(String[] args)
  {
    try (Scanner scan = new Scanner(System.in)) 
    {
      System.out.println("Enter the number of tokens: ");
      int tokens = scan.nextInt();

      System.out.println("Enter the number of rounds: ");
      int rounds = scan.nextInt();

      System.out.println("Choose the method to calculate expected tokens:");
      System.out.println("0. All Three");
      System.out.println("1. Top-Down");
      System.out.println("2. Bottom-Up");
      System.out.println("3. Top-Down with Caching");
      int choice = scan.nextInt();

      StopWatch timer = new StopWatch();

      switch (choice)
      {
        case 0 -> 
        {
            timer.start();
            double topDown = Evensies.expectedTopDown(tokens, rounds);
            timer.stop();
            System.out.println("Top-down: " + topDown + " (time: " + timer.getElapsedTime() + " ms)");

            timer.reset();
            timer.start();
            double bottomUp = Evensies.expectedBottomUp(tokens, rounds);
            timer.stop();
            System.out.println("Bottom-up: " + bottomUp + " (time: " + timer.getElapsedTime() + " ms)");

            timer.reset();
            timer.start();
            double caching = Evensies.expectedCaching(tokens, rounds);
            timer.stop();
            System.out.println("Caching: " + caching + " (time: " + timer.getElapsedTime() + " ms)");
        }
        case 1 -> 
        {
            timer.start();
            double topDown = Evensies.expectedTopDown(tokens, rounds);
            timer.stop();
            System.out.println("Top-down: " + topDown + " (time: " + timer.getElapsedTime() + " ms)");
        }
        case 2 -> 
        {
            timer.start();
            double bottomUp = Evensies.expectedBottomUp(tokens, rounds);
            timer.stop();
            System.out.println("Bottom-up: " + bottomUp + " (time: " + timer.getElapsedTime() + " ms)");
        }
        case 3 -> 
        {
            timer.start();
            double caching = Evensies.expectedCaching(tokens, rounds);
            timer.stop();
            System.out.println("Caching: " + caching + " (time: " + timer.getElapsedTime() + " ms)");
        }
        default -> System.out.println("Invalid choice. Please restart the program and choose a valid option.");
      } 
    }
  }
}
