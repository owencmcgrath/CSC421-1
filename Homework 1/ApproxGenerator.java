import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that reads in and stores the contents of a text file.
 * @author Dave Reed, Owen McGrath
 * @version 8/20/24
 */
public class ApproxGenerator
{
    private String cleanText;
    private Random randy;

    public ApproxGenerator(String fileName, int order) throws Exception
    {
        this.cleanText = "";
        this.randy = new Random();

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

    /**
    * method that generates a seed based on the input text and order of the seed.
    * @param order -> the number of characters in the seed
    * @return seed -> the generated seed
    */
    public String generateSeed(int order)
    {
        String seed = "";
        int randomInteger = randy.nextInt(cleanText.length() - (order - 1));

        for (int i = randomInteger; i < randomInteger + order; i++)
        {
            seed += cleanText.charAt(i);
        }
        return seed;
    }

   /*
    * method that generates a map based on a seed and the characters that follow it. the seed is the key and the characters that follow it are the values.
    * the characters are stored in a linked list.
    * @param order -> the number of characters in the seed
    * @return mapOfSeedsAndCharacters -> a map of seeds and the characters that follow them
    */
    public Map<String, LinkedList<Character>> generateMap(int order)
    {
        HashMap<String, LinkedList<Character>> mapOfStringsAndCharacters = new HashMap<String, LinkedList<Character>>();

        for (int i = 0; i < cleanText.length() - order; i++) {
            String starterString = cleanText.substring(i, i + order); //gets current index and the next order characters
            char nextChar = cleanText.charAt(i + order); //gets the characters after the seed

            LinkedList<Character> charactersOfSeed = mapOfStringsAndCharacters.getOrDefault(starterString, new LinkedList<>()); //gets the list of characters for the seed. if it doesn't exist, creates a new list (what the getOrDefault method does.)

            charactersOfSeed.add(nextChar); //adds the next character to the list
            mapOfStringsAndCharacters.put(starterString, charactersOfSeed); //puts the seed and the list of characters into the map
        }
        return mapOfStringsAndCharacters;
    }

    /**
    * method that generates a string of characters based on the input text, order of the seed, and characters that follow the seed.
    * @param numChars -> the number of characters to generate
    * @param order -> the number of characters in the seed
    * @return newText -> the generated text
    */
    public String generate(int numChars, int order)
    {
        String seed = this.generateSeed(order); //generate initial seed

        String newText = "";
        while (newText.length() < numChars)
        {
            LinkedList<Character> charactersOfSeed = generateMap(order).get(seed); //gets the list of characters for the seed

            //if the seed doesn't exist in the map, generate a new seed
            if (charactersOfSeed == null)
            {
                seed = this.generateSeed(order);
                charactersOfSeed = generateMap(order).get(seed);
            }

            char nextChar = charactersOfSeed.get(randy.nextInt(charactersOfSeed.size())); //gets a random character from the list
            newText += nextChar; //adds the character to the new text
            seed = seed.substring(1) + nextChar; //updates the seed
        }
        return newText;
    }
}
