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

    private static final double WIN_PROB = 16 / 36.0;
    private static final double LOSS_PROB = 16 / 36.0;
    private static final double BOTTOM_WIN_PROB = 2 / 36.0;
    private static final double BOTTOM_LOSS_PROB = 2 / 36.0;

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
        if (rounds <= 0 || tokens <= 0)
        {
            return tokens;
        }
        else
        {
            return
                (expectedTopDown(tokens + 1, rounds - 1) * WIN_PROB)         +
                (expectedTopDown(tokens - 1, rounds - 1) * LOSS_PROB)        +
                (expectedTopDown(tokens, rounds - 1) * BOTTOM_WIN_PROB)      +
                (expectedTopDown(tokens - 2, rounds - 1) * BOTTOM_LOSS_PROB);
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
        if (rounds <= 0 || tokens <= 0)
        {
            return tokens;
        }

        double[][] expected = new double[rounds + tokens + 1][rounds + 1];

        for (int t = 0; t < expected.length; t++)
        {
            expected[t][0] = t;
        }

        for (int r = 0; r < expected[0].length; r++)
        {
            expected[0][r] = 0;
        }

        for (int r = 1; r <= rounds; r++)
        {
            for (int t = 1; t < expected.length - r; t++)
            {
                expected[t][r] = expected[t + 1][r - 1] * WIN_PROB;

                if (t - 1 >= 0)
                {
                    expected[t][r] += expected[t - 1][r - 1] * LOSS_PROB;
                }
                else
                {
                    expected[t][r] -= LOSS_PROB;
                }

                expected[t][r] += expected[t][r - 1] * BOTTOM_WIN_PROB;

                if (t - 2 >= 0)
                {
                    expected[t][r] += expected[t - 2][r - 1] * BOTTOM_LOSS_PROB;
                }
                else
                {
                    expected[t][r] -= BOTTOM_LOSS_PROB;
                }
            }
        }
        return expected[tokens][rounds];
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
        if (rounds <= 0 || tokens <= 0)
        {
            return tokens;
        }

        String key = tokens + "," + rounds;
        if (cache.containsKey(key))
        {
            return cache.get(key);
        }

        double cachedValue =
                (expectedCachingCalculator(tokens + 1, rounds - 1, cache) * WIN_PROB)         +
                (expectedCachingCalculator(tokens - 1, rounds - 1, cache) * LOSS_PROB)        +
                (expectedCachingCalculator(tokens, rounds - 1, cache) * BOTTOM_WIN_PROB)      +
                (expectedCachingCalculator(tokens - 2, rounds - 1, cache) * BOTTOM_LOSS_PROB);

        cache.put(key, cachedValue);
        return cachedValue;
    }
}
