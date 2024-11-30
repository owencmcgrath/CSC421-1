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
    Scanner scan = new Scanner(System.in);
    System.out.println("=== Running Test Cases ===");
    runTestCase(1, 1);
    runTestCase(5, 5);
    runTestCase(3, 6);
    runTestCase(6, 3);
    runTestCase(2, 9);
    runTestCase(0, 5);
    runTestCase(-2, 3);    
    runTestCase(10, 1);
    runTestCase(1, 10);
    
    System.out.println("\n=== Test Cases Complete ===\n");
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

    switch (choice) 
    {
      case 0:
      System.out.println("Expected # of tokens at the end of the game with top-down: " + Evensies.expectedTopDown(tokens, rounds));
      System.out.println("Expected # of tokens at the end of the game with bottom-up: " + Evensies.expectedBottomUp(tokens, rounds));
      System.out.println("Expected # of tokens at the end of the game with caching: " + Evensies.expectedCaching(tokens, rounds));
      break;
      case 1:
      System.out.println("Expected # of tokens at the end of the game: " + Evensies.expectedTopDown(tokens, rounds));
      break;
      case 2:
      System.out.println("Expected # of tokens at the end of the game: " + Evensies.expectedBottomUp(tokens, rounds));
      break;
      case 3:
      System.out.println("Expected # of tokens at the end of the game with caching: " + Evensies.expectedCaching(tokens, rounds));
      break;
      default:
      System.out.println("Invalid choice. Please restart the program and choose a valid option.");
      break;
    }
    scan.close();
  }

  private static 
  void runTestCase
  (int tokens, int rounds) 
  {
    System.out.printf("\nTest Case: %d tokens, %d rounds\n", tokens, rounds);
    double topDown = Evensies.expectedTopDown(tokens, rounds);
    double bottomUp = Evensies.expectedBottomUp(tokens, rounds);
    double cached = Evensies.expectedCaching(tokens, rounds);
    System.out.printf("Top-down:  %.4f\n", topDown);
    System.out.printf("Bottom-up: %.4f\n", bottomUp);
    System.out.printf("Cached:    %.4f\n", cached);
    System.out.println("Match? " + (Math.abs(topDown - bottomUp) < 0.0001 && Math.abs(topDown - cached) < 0.0001));
  }
}
