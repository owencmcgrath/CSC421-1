import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * Class that reads in, stores, and cleans the contents of a text file.
 * @author Dave Reed, Owen McGrath
 * @version 8/20/24
 */
public class ApproxGenerator
{
    private String cleanText;
    private Random random;

    public ApproxGenerator(String fileName) throws Exception
    {
        this.cleanText = "";
        this.random = new Random();

        Scanner infile = new Scanner(new File(fileName));

        while (infile.hasNextLine())
        {
            String nextLine = infile.nextLine().toUpperCase();
            for (int i = 0; i < nextLine.length(); i++)
            {
                char ch = nextLine.charAt(i);
                if (Character.isLetter(ch) || ch == ' ')
                {
                    this.cleanText += ch;
                }
            }
            this.cleanText += " ";
        }
        this.cleanText = this.cleanText.trim().replaceAll("\\s+", " ");

        infile.close();
    }

    /*
     * method that checks for errors in the input values and adjusts them if necessary.
     * @param numChars -> the number of characters to generate
     * @param order -> the number of characters in the seed
     * @return -> an array of the adjusted values
     */
     public int[] errorChecking(int numChars, int order)
     {
        //if the order is greater than the number of characters, set the order to the number of characters
        if (order > numChars)
        {
            order = numChars;
            System.out.println("The order is too large for the number of characters. The order has been set to " + order + ".");
        }

        //if the number of characters is greater than the length of the text, set the number of characters to the length of the text
        if (numChars > cleanText.length())
        {
            numChars = cleanText.length();
            System.out.println("The number of characters is too large. The number of characters has been set to " + numChars + ".");
        }
        return new int[]{numChars, order}; //since we need to return multiple variables, they are added to an array
    }

   /*
    * method that generates a seed based on the input text and order of the seed.
    * @param order -> the number of characters in the seed
    * @return seed -> the generated seed
    */
    public String generateSeed(int order)
    {
        String seed = "";
        int randomInteger = random.nextInt(cleanText.length() - (order - 1));

        for (int i = randomInteger; i < randomInteger + order; i++)
        {
            seed += cleanText.charAt(i);
        }
        return seed;
    }

   /*
    * method that generates a map based on a seed and the characters that follow it.
    * the seed is the key and the characters that follow it are the stored values.
    * @param order -> the number of characters in the seed
    * @return mapOfSeedsAndCharacters -> a map of seeds and the characters that follow them
    */
    public Map<String, LinkedList<Character>> generateMap(int order)
    {
        HashMap<String, LinkedList<Character>> mapOfStringsAndCharacters = new HashMap<String, LinkedList<Character>>();

        for (int i = 0; i < cleanText.length() - order; i++) {
            String starterString = cleanText.substring(i, i + order); //builds a string based on the current index and the next order characters
            char nextChar = cleanText.charAt(i + order); //gets the characters after the seed

            LinkedList<Character> charactersOfSeed = mapOfStringsAndCharacters.getOrDefault(starterString, new LinkedList<>()); //gets the list of characters for the seed. if it doesn't exist, creates a new list (what the getOrDefault method does.)

            charactersOfSeed.add(nextChar); //adds the next character to the list
            mapOfStringsAndCharacters.put(starterString, charactersOfSeed); //puts the seed and the list of characters into the map
        }
        return mapOfStringsAndCharacters;
    }

   /*
    * method that generates a string of characters based on the input
    * text, order of the seed, and characters that follow the seed.
    * @param numChars -> the number of characters to generate
    * @param order -> the number of characters in the seed
    * @return newText -> the generated text
    */
    public String generate(int numChars, int order)
    {
        int[] possiblyAdjustedValues = this.errorChecking(numChars, order);
        numChars = possiblyAdjustedValues[0]; //sets the values to the adjusted values, if they were adjusted
        order = possiblyAdjustedValues[1]; //sets the values to the adjusted values, if they were adjusted

        String seed = this.generateSeed(order); //generate initial seed

        String newText = "";
        while (newText.length() < numChars)
        {
            LinkedList<Character> charactersOfSeed = generateMap(order).get(seed); //gets the list of characters for the seed

            //if the seed doesn't exist in the map (i.e, there is nothing left at the end), generate a new seed
            if (charactersOfSeed == null)
            {
                seed = this.generateSeed(order);
                charactersOfSeed = generateMap(order).get(seed);
            }

            char nextChar = charactersOfSeed.get(random.nextInt(charactersOfSeed.size())); //gets a random character from the list
            newText += nextChar; //adds the character to the new text
            seed = seed.substring(1) + nextChar; //updates the seed with the new character
        }
        return newText;
    }
}
