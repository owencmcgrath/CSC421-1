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
    System.out.println("Enter the number of tokens: ");
    int tokens = scan.nextInt();

    System.out.println("Enter the number of rounds: ");
    int rounds = scan.nextInt();
    scan.close();

    Evensies evensies = new Evensies(tokens, rounds); 
  }  
}
