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

  /**
   * Prompts the user for input
   * @param scan the scanner object
   * @param prompt the prompt to display
   * @return the user's input
   */
  private static
  int promptForInput
  (Scanner scan, String prompt)
  {
    System.out.println(prompt);
    return scan.nextInt();
  }

  /**
   * Displays the menu and gets the user's choice
   * @param scan the scanner object
   * @return the user's choice
   */
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

  /**
   * Calculates the expected tokens based on the user's choice
   * @param tokens the number of tokens
   * @param rounds the number of rounds
   * @param choice the user's choice
   */
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
      case 3 -> runCaching(tokens, rounds, timer);
      default -> System.out.println("Invalid choice, please try again.");
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
  (int tokens, int rounds, StopWatch timer)
  {
    runTopDown(tokens, rounds, timer);
    runBottomUp(tokens, rounds, timer);
    runCaching(tokens, rounds, timer);
  }

  /**
   * Runs the top-down method
   * @param tokens the number of tokens
   * @param rounds the number of rounds
   * @param timer the timer object
   */
  private static
  void runTopDown
  (int tokens, int rounds, StopWatch timer)
  {
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
  (int tokens, int rounds, StopWatch timer)
  {
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
  (int tokens, int rounds, StopWatch timer)
  {
    timer.start();
    System.out.println("Top-Down with Caching: " + Evensies.expectedCaching(tokens, rounds));
    timer.stop();
    System.out.println("Time: " + timer.getElapsedTime() + " milliseconds");
  }
}