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
      int tokens = promptForInput(scan, "Enter the number of tokens: ");
      int rounds = promptForInput(scan, "Enter the number of rounds: ");
      int choice = displayMenuAndGetChoice(scan);

      calculate(tokens, rounds, choice);
    }
  }
  private static
  int promptForInput
  (Scanner scan, String prompt)
  {
    System.out.println(prompt);
    return scan.nextInt();
  }

  private static
  int displayMenuAndGetChoice
  (Scanner scan)
  {
    System.out.println("Choose the method to calculate expected tokens:");
    System.out.println("0. All Three");
    System.out.println("1. Top-Down");
    System.out.println("2. Bottom-Up");
    System.out.println("3. Top-Down with Caching: ");
    return scan.nextInt();
  }

  private static 
  void calculate
  (int tokens, int rounds, int choice)
  {
    StopWatch timer = new StopWatch();
    switch (choice)
    {
      case 0 -> runAllMethods(tokens, rounds, timer);
      case 1 -> runTopDown(tokens, rounds, timer);
      case 2 -> runBottomUp(tokens, rounds, timer);
      case 3 -> runAllMethods(tokens, rounds, timer);
      default -> System.out.println("Invalid choice, please try again.");
    }
  }

  private static
  void runAllMethods
  (int tokens, int rounds, StopWatch timer)
  {
    runTopDown(tokens, rounds, timer);
    runBottomUp(tokens, rounds, timer);
    runCaching(tokens, rounds, timer);
  }

  private static
  void runTopDown
  (int tokens, int rounds, StopWatch timer)
  {
    timer.start();
    System.out.println("Top-Down: " + Evensies.expectedTopDown(tokens, rounds));
    timer.stop();
    System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
  }

  private static
  void runBottomUp
  (int tokens, int rounds, StopWatch timer)
  {
    timer.start();
    System.out.println("Bottom-Up: " + Evensies.expectedBottomUp(tokens, rounds));
    timer.stop();
    System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
  }
  
  private static
  void runCaching
  (int tokens, int rounds, StopWatch timer)
  {
    timer.start();
    System.out.println("Top-Down with Caching: " + Evensies.expectedCaching(tokens, rounds));
    timer.stop();
    System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
  }
}