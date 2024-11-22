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
  /**
   *  Determines the expected # of tokens for the player at the game's end,
   *  using a top-down, divide & conquer approach.
   *  @param tokens the number of tokens currently held by the player
   *  @param rounds the number of rounds left to be played
   *  @return the expected # of tokens for the player at the game's end
   */
  public static double 
  expectedTopDown(int tokens, int rounds)   
  {
    //if the player has no tokens or no rounds left, return the number of tokens
    if (tokens <= 0 || rounds <= 0)
    {
      //for caching, add this to a literal map
      return tokens; //the base case has been reached
    }
    else
    {
      //everytime it is in the map, just return the value, otherwise calculate it and save it to the map, for caching
      return  /*win*/ (expectedTopDown(tokens + 1, rounds - 1) * 16/36.0) + 
              /*loss*/ (expectedTopDown(tokens - 1, rounds - 1) * 16/36.0) + 
              /*l+b*/ (expectedTopDown(tokens - 2, rounds - 1) * 2/36.0) + 
              /*w+b*/ (expectedTopDown(tokens, rounds - 1) * 2/36.0);
    }  
  }

  /**
   * Calls the expectedCachingCalculator method, which uses caching to determine the expected
   * # of tokens for the player at the game's end. This is done to avoid redeclaring the cache map
   * each time the method is called.
   * @param tokens the number of tokens currently held by the player
   * @param rounds the number of rounds left to be played
   * @return the expected # of tokens for the player at the game's end
   */
  public static double expectedCaching(int tokens, int rounds) 
  {
    return expectedCachingCalculator(tokens, rounds, new HashMap<String, Double>());
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
  double expectedCachingCalculator(int tokens, int rounds, HashMap<String, Double> cache)
  {
    String key = rounds + "," + tokens;

    if (tokens <= 0 || rounds <= 0)
    {
      return tokens; //the base case has been reached
    }

    //if the key has already been found, just return that value
    if (cache.containsKey(key))
    {
      return cache.get(key);
    }

    //otherwise, store it and return the value
    double cachedValue =  /*win*/ (expectedCachingCalculator(tokens + 1, rounds - 1, cache) * 16/36.0) + 
                          /*loss*/ (expectedCachingCalculator(tokens - 1, rounds - 1, cache) * 16/36.0) + 
                          /*l+b*/ (expectedCachingCalculator(tokens - 2, rounds - 1, cache) * 2/36.0) + 
                          /*w+b*/ (expectedCachingCalculator(tokens, rounds - 1, cache) * 2/36.0);
    cache.put(key, cachedValue);
    return cachedValue;
  }
}