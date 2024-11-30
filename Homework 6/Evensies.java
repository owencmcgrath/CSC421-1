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
        if (tokens <= 0 || rounds <= 0)
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

        public static double expectedBottomUp(int tokens, int rounds) 
    {
        // Match top-down base case exactly
        if (tokens <= 0 || rounds <= 0)
        {
            return tokens;
        }
    
        // Increase array size to handle all possible states
        int minTokens = tokens - (2 * rounds);
        int maxTokens = tokens + (2 * rounds); // Match recursive win case potential
        int arraySize = maxTokens - minTokens + 1;
        double[][] expected = new double[arraySize][rounds + 1];
    
        // Initialize base cases matching recursive base case
        for (int t = 0; t < arraySize; t++) 
        {
            int currentTokens = minTokens + t;
            expected[t][0] = currentTokens <= 0 ? currentTokens : currentTokens;
        }
    
        // Fill DP table matching recursive state transitions
        for (int r = 1; r <= rounds; r++) 
        {
            for (int t = 0; t < arraySize; t++) 
            {
                int currentTokens = minTokens + t;
                if (currentTokens <= 0)
                {
                    expected[t][r] = currentTokens;
                    continue;
                }
    
                // Match recursive calls exactly
                double win = (t + 1 < arraySize) ? expected[t + 1][r-1] : (currentTokens + 1);
                double loss = (t - 1 >= 0) ? expected[t - 1][r-1] : (currentTokens - 1);
                double bottomWin = (t - 2 >= 0) ? expected[t - 2][r-1] : (currentTokens - 2);
                double bottomLoss = expected[t][r-1];
    
                expected[t][r] = win * WIN_PROB + 
                                loss * LOSS_PROB + 
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
    if (tokens <= 0 || rounds <= 0)
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
    double cachedValue =    /*win*/ (expectedCachingCalculator(tokens + 1, rounds - 1, cache) * WIN_PROB)         +
                            /*loss*/(expectedCachingCalculator(tokens - 1, rounds - 1, cache) * LOSS_PROB)        +
                            /*l+b*/ (expectedCachingCalculator(tokens - 2, rounds - 1, cache) * BOTTOM_LOSS_PROB) +
                            /*w+b*/ (expectedCachingCalculator(tokens, rounds - 1, cache) * BOTTOM_WIN_PROB);
    cache.put(key, cachedValue);
    return cachedValue;
    }

    public static void printTable(double[][] array)
    {
        System.out.print("      ");
        for (int col = 0; col < array[0].length; col++)
        {
            System.out.printf("%8d ", col);
        }

        System.out.println("\n");

        for (int row = 0; row < array.length; row++)
        {
            System.out.printf("[%2d] ", row);
            for (int col = 0; col < array[row].length; col++)
            {
                System.out.printf("%8.2f ", array[row][col]);
            }
            System.out.println();
        }
    }
}
