/**
 * A class that determines the expected # of tokens for the player at the game's end,
 * using a top-down, divide & conquer approach.
 * @author Owen McGrath
 * @version 11/19/2024
 */
public class Evensies 
{

  private int tokens;
  private int rounds;

  public Evensies(int tokens, int rounds)
  {
    this.tokens = tokens;
    this.rounds = rounds;
  }

  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a top-down, divide & conquer approach.
   *    @param tokens the number of tokens currently held by the player
   *    @param rounds the number of rounds left to be played
   */
  public static double expectedTopDown(int tokens, int rounds)   
  {
    //if the player has no tokens or no rounds left, return the number of tokens
    if (tokens == 0 || rounds == 0)
    {
      //the base case has been reached
      return tokens;
    }
    else
    {
      double evens = 0;
      double odds = 0;

      //for each possible result of the dice
      //starts at 1 because the dice can only be 1-6
      for (int die1 = 1; die1 <= 6; die1++)
      {
        for (int die2 = 1; die2 <= 6; die2++)
        {
          if (checkEvensies(die1, die2))
          {
            //the guess was correct
            evens += expectedTopDown(tokens + 1, rounds - 1);
          }
          else
          {  
            //the guess was incorrect
            evens += expectedTopDown(tokens - 1, rounds - 1);
          }

          if (checkOddsies(die1, die2))
          {
            //the guess was correct
            odds += expectedTopDown(tokens + 1, rounds - 1);
          }
          else
          {
            //the guess was incorrect
            odds += expectedTopDown(tokens - 1, rounds - 1);
          }

          if (checkBottomsies(die1, die2))
          {
            //the guess was incorrect and it was a bottomsies
            evens += expectedTopDown(tokens - 1, rounds - 1);
            odds += expectedTopDown(tokens - 1, rounds - 1);
          }
        }
      }
      //the odds and evens are divided by 36 because there are 36 possible combinations of dice
      evens /= 36.0;
      odds /= 36.0;

      //return the maximum of the two
      return Math.max(evens, odds);
    } 
  }

  /**
   * Determines if the sum of the dice is even.
   * @param die1
   * @param die2
   * @return true if the sum of the dice is even, false otherwise
   */
  private static boolean checkEvensies(int die1, int die2)
  {
    //if the results of the die are even, return true
    return (die1 + die2) % 2 == 0;
  }

  /**
   * Determines if the sum of the dice is odd.
   * @param die1
   * @param die2
   * @return true if the sum of the dice is odd, false otherwise
   */
  private static boolean checkOddsies(int die1, int die2)
  {
    //if the results of the die are odd, return true
    return (die1 + die2) % 2 != 0;
  }

  /**
   * Determines if the sum of the dice is 1 or 2.
   * @param die1
   * @param die2
   * @return true if the sum of the dice is 1 or 2, false otherwise
   */
  private static boolean checkBottomsies(int die1, int die2)
  {
    //if the results of the die are 1 or 2, return true 
    return (die1 <= 2 && die2 <= 2);
  }
}