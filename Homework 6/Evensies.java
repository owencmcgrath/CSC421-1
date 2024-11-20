public class Evensies 
{
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
      evens /= 36.0;
      odds /= 36.0;
    
    return Math.max(evens, odds);
    } 
  }

  private static boolean checkEvensies(int die1, int die2)
  {
    //if the results of the die are even, return true
    return (die1 + die2) % 2 == 0;
  }

  private static boolean checkOddsies(int die1, int die2)
  {
    //if the results of the die are odd, return true
    return (die1 + die2) % 2 != 0;
  }

  private static boolean checkBottomsies(int die1, int die2)
  {
    //if the results of the die are 1 or 2, return true 
    return (die1 <= 2 && die2 <= 2);
  }
}