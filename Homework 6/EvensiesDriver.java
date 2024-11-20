import java.util.Scanner;
public class EvensiesDriver 
{
  public static void main(String[] args) 
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the number of tokens: ");
    int tokens = scan.nextInt();

    System.out.println("Enter the number of rounds: ");
    int rounds = scan.nextInt();
    scan.close();

    Evensies evensies = new Evensies(tokens, rounds); 
    System.out.println("Number of tokens: " + tokens);
    System.out.println("Number of rounds: " + rounds); 
  }  
}
