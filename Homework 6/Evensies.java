import java.util.HashMap;

/**
 * A class that determines the expected # of tokens for the player at the game's end,
 * using a top-down, divide & conquer approach.
 * @author Owen McGrath
 * @version 11/19/2024
 */
public class
Evensies
{
    private static final double WIN_PROB = 16/36.0;
    private static final double LOSS_PROB = 16/36.0;
    private static final double BOTTOM_WIN_PROB = 2/36.0;
    private static final double BOTTOM_LOSS_PROB = 2/36.0;
  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a top-down, divide & conquer approach.
   *  @param tokens the number of tokens currently held by the player
   *  @param rounds the number of rounds left to be played
   *  @return the expected # of tokens for the player at the game's end
   */
    public static
    double expectedTopDown
    (int tokens, int rounds)
    {
        //if the player has no tokens or no rounds left, return the number of tokens
        if (rounds <= 0)
        {
            return tokens;
        }
        else
        {
            //return the expected # of tokens for the player at the game's end
            return  /*win*/ (expectedTopDown(tokens + 1, rounds - 1) * WIN_PROB)          +
                    /*loss*/(expectedTopDown(tokens - 1, rounds - 1) * LOSS_PROB)         +
                    /*l+b*/ (expectedTopDown(tokens - 2, rounds - 1) * BOTTOM_WIN_PROB)   +
                    /*w+b*/ (expectedTopDown(tokens, rounds - 1) * BOTTOM_LOSS_PROB);
        }
    }

    /**
     * Determines the expected # of tokens for the player at the game's end,
     * using a bottom-up, dynamic programming approach.
     * @param tokens the number of tokens currently held by the player
     * @param rounds the number of rounds left to be played
     * @return the expected # of tokens for the player at the game's end
     */
    public static 
    double expectedBottomUp
    (int tokens, int rounds) 
    {
        //if the player has no tokens or no rounds left, return the number of tokens, imilar to the base case of the top-down approach
        if (rounds <= 0)
        {
            return tokens;
        }
    
        //array calcualtions and setup
        int minTokens = tokens - (2 * rounds); //the worst case scenario where the player loses every round and hits a bottomsies
        int maxTokens = tokens + (2 * rounds); //the best case scenario where the player wins every round and avoids a bottomsies
        int arraySize = maxTokens - minTokens + 1; //the array needs to account for both extremes, represents range from minimum possible tokens to the maximum possible tokens
        double[][] expected = new double[arraySize][rounds + 1]; //rows are all of the possible tokens, columns are the rounds left to be played + plua one for the pesky base case
    
        //fill the base case of the table with the first column at zero
        //while t has less than total possible tokens, fill the table with the current tokens
        for (int t = 0; t < arraySize; t++) 
        {
            expected[t][0] = minTokens + t; //this maps the array info to the actual token values (i.e, if there is a negative token, store the negative value, if there are possitive tokens, store the positive value)
        }
    
        //fill the rest of the table
        for (int r = 1; r <= rounds; r++) //for each round
        {
            for (int t = 0; t < arraySize; t++) //for each token value
            {
                int currentTokens = minTokens + t; //maps the array index back to the actual token count where minTokens is the worse case scenario and adding t gives us the actial token count

                //if the player has no tokens, there's no need to calculate anything
                expected[t][r] = currentTokens; //store the current token value
    
                double win, loss, bottomWin; //this is so cool?!
                //if the player has more tokens than the minimum possible, check if adding another token is possible
                //if it is, use the previous round's value, otherwise, use the current token value + 1
                if (t + 1 < arraySize)
                {
                    win = expected[t + 1][r-1];
                } 
                else 
                {
                    win = currentTokens + 1;
                }

                //if the player has more tokens than the minimum possible, check if subtracting a token is possible
                //if it is, use the previous round's value, otherwise, use the current token value - 1
                if (t - 1 >= 0) 
                {
                    loss = expected[t - 1][r-1];
                } 
                else 
                {
                    loss = currentTokens - 1;
                }

                //if the player has more tokens than the minimum possible, check if subtracting two tokens is possible
                //if it is, use the previous round's value, otherwise, use the current token value - 2
                if (t - 2 >= 0) 
                {
                    bottomWin = expected[t - 2][r-1];
                } 
                else 
                {
                    bottomWin = currentTokens - 2;
                }

                //if the player has more tokens than the minimum possible, check if adding two tokens is possible
                double bottomLoss = expected[t][r-1];

                //store the expected # of tokens for the player at the game's end
                expected[t][r] = win * WIN_PROB              + 
                                 loss * LOSS_PROB            + 
                                 bottomWin * BOTTOM_WIN_PROB + 
                                 bottomLoss * BOTTOM_LOSS_PROB;
            }
        }
        return expected[tokens - minTokens][rounds];
    }

  /**
   * Calls the expectedCachingCalculator method, which uses caching to determine the expected
   * # of tokens for the player at the game's end. This is done to avoid redeclaring the cache map
   * each time the method is called.
   * @param tokens the number of tokens currently held by the player
   * @param rounds the number of rounds left to be played
   * @return the expected # of tokens for the player at the game's end
   */
    public static
    double expectedCaching
    (int tokens, int rounds)
    {
        return expectedCachingCalculator(tokens, rounds, new HashMap<>());
    }

   /**
    * Determines the expected # of tokens for the player at the game's end,
    * using a top-down, divide and conquer approach, combined with caching.
    * @param tokens the number of tokens currently held by the player
    * @param rounds the number of rounds left to be played
    * @param cache a map that stores the expected # of tokens for each round and token value
    * @return the expected # of tokens for the player at the game's end
    */
    public static
    double expectedCachingCalculator
    (int tokens, int rounds, HashMap<String, Double> cache)
    {

        //if the player has no tokens or no rounds left, return the number of tokens
        if (rounds <= 0)
        {
            return tokens;
        }

        String key = rounds + "," + tokens; //key for the cache map

        //if the key has already been found, just return that value
        if (cache.containsKey(key))
        {
            return cache.get(key);
        }

        //otherwise, store it and return the value
        double cachedValue = /*win*/ (expectedCachingCalculator(tokens + 1, rounds - 1, cache) * WIN_PROB) +
                            /*loss*/(expectedCachingCalculator(tokens - 1, rounds - 1, cache) * LOSS_PROB) +
                            /*l+b*/ (expectedCachingCalculator(tokens - 2, rounds - 1, cache) * BOTTOM_WIN_PROB) +
                            /*w+b*/ (expectedCachingCalculator(tokens, rounds - 1, cache) * BOTTOM_LOSS_PROB);
        cache.put(key, cachedValue);
        return cachedValue;
    }

    // public static void printTable(double[][] array)
    // {
    //     System.out.print("      ");
    //     for (int col = 0; col < array[0].length; col++)
    //     {
    //         System.out.printf("%8d ", col);
    //     }

    //     System.out.println("\n");

    //     for (int row = 0; row < array.length; row++)
    //     {
    //         System.out.printf("[%2d] ", row);
    //         for (int col = 0; col < array[row].length; col++)
    //         {
    //             System.out.printf("%8.2f ", array[row][col]);
    //         }
    //         System.out.println();
    //     }
    // }
}
