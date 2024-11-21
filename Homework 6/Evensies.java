/**
 * A class that determines the expected # of tokens for the player at the game's end,
 * using a top-down, divide & conquer approach.
 *    @author Owen McGrath
 *    @version 11/19/2024
 */
public class 
Evensies 
{

  private int tokens;
  private int rounds;

  public 
  Evensies(int tokens, int rounds)
  {
    this.tokens = tokens;
    this.rounds = rounds;
  }

  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a top-down, divide & conquer approach.
   *    @param tokens the number of tokens currently held by the player
   *    @param rounds the number of rounds left to be played
   *    @return the expected # of tokens for the player at the game's end
   */
  public static double 
  expectedTopDown(int tokens, int rounds)   
  {
    //if the player has no tokens or no rounds left, return the number of tokens
    //not sure how you'd have no tokens and still be playing the game. maybe you traded your car keys
    if (tokens <= 0 || rounds <= 0)
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
          int tokenChange = 0;

          //evens strategy
          tokenChange = calculateChangeForEvensies(die1, die2);
          if (tokens + tokenChange >= 0) 
          {
            evens += expectedTopDown(tokens + tokenChange, rounds - 1);
          }

          //odds strategy
          tokenChange = calculateChangeForOddsies(die1, die2);
          if (tokens + tokenChange >= 0) 
          {
            odds += expectedTopDown(tokens + tokenChange, rounds - 1);
          }
        }
      }
      //divide by 36 because there are 36 possible outcoms of the dice
      evens /= 36.0;
      odds /= 36.0;

      //which strategy is better
      if (evens > odds)
      { return evens; }
      else
      { return odds; }
    }  
  }

  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a bottom-up, dynamic-programming approach.
   *    @param tokens the number of tokens currently held by the player
   *    @param rounds the number of rounds left to be played
   *    @return the expected # of tokens for the player at the game's end
   */
  public static double 
  expectedBottomUp(int tokens, int rounds)
  {
    //if the player has no tokens or no rounds left, return the number of tokens
    //not sure how you'd have no tokens and still be playing the game. maybe you traded your car keys
    if (tokens <= 0 || rounds <= 0)
    {
      //the base case has been reached
      return tokens;
    }

    //the most amount of tokens the player can have is the number of tokens they started with + the number of rounds since you can only gain one token per round
    int maxTokens = tokens + rounds;
    double[][] map = new double[maxTokens][rounds];

    //initialize the map
    for (int t = 0; t < maxTokens; t++) 
    {
      map[t][0] = t;
    }

    for (int r = 1; r < rounds; r++)
    {
      for (int mt = 1; mt < maxTokens; mt++)
      {
        double evens = 0;
        double odds = 0;

        //for each possible result of the dice
        //starts at 1 because the dice can only be 1-6
        for (int die1 = 1; die1 <= 6; die1++)
        {
          for (int die2 = 1; die2 <= 6; die2++)
          {
            int tokenChange = 0;

            //evens strategy
            tokenChange = calculateChangeForEvensies(die1, die2); //returns the change in tokens if the guess was evensies
            if (mt + tokenChange < maxTokens)
            {
              evens += map[mt + tokenChange][r - 1]; //fill in the map with the expected value of the next round
            }

            //odds strategy
            tokenChange = calculateChangeForOddsies(die1, die2); //returns the change in tokens if the guess was oddsies
            if (mt + tokenChange < maxTokens)
            {
              odds += map[mt + tokenChange][r - 1]; //fill in the map with the expected value of the next round
            }
          }
        }

        //divide by 36 because there are 36 possible outcoms of the dice
        evens /= 36.0;
        odds /= 36.0;

        //which strategy is better
        if (evens > odds)
        {
          map[mt][r] = evens;
        }
        else
        {
          map[mt][r] = odds;
        }
      }
    }
    return map[tokens][rounds - 1]; //return the expected value of the last round
  }

  /**
   * Determines the change in tokens if the guess was evensies
   *    @param die1
   *    @param die2
   *    @return the change in tokens if the guess was evensies
   */
  private static 
  int calculateChangeForEvensies(int die1, int die2)
  {
    int tokenChange = 0;
    if ((die1 + die2) % 2 == 0)
    {
      tokenChange = 1; //the guess was correct
    }
    else
    {
      tokenChange = -1; //the guess was incorrect
    }

    if (die1 <= 2 && die2 <= 2)
    {
      tokenChange--; //the guess was correct
    }
    return tokenChange;
  }

  /**
   * Determiens the change in tokens if the guess was oddsies
   *    @param die1
   *    @param die2
   *    @return the change in tokens if the guess was oddsies
   */
  private static 
  int calculateChangeForOddsies(int die1, int die2)
  {
    int tokenChange = 0;
    if ((die1 + die2 % 2) != 0)
    {
      tokenChange = 1; //the guess was correct
    }
    else
    {
      tokenChange = -1; //the guess was correct
    }

    if (die1 <= 2 && die2 <= 2)
    {
      tokenChange--; //the guess was incorrect and it was a bottomsies
    }
    return tokenChange;
  }
}