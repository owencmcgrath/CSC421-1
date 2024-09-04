import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/*
 * Class that reads in, stores, and cleans the contents of a text file.
 * It also generates a seed and a map of seeds to create simple text generation.
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
        HashMap<String, LinkedList<Character>> mapOfStringsAndCharacters = new HashMap<String, LinkedList<Character>>(); //creates a map of strings as keys and lists of characters as values

        for (int i = 0; i < cleanText.length() - order; i++)
        {
            String starterString = cleanText.substring(i, i + order); //NOT THE SEED. builds a string based on the current index and the next order characters.
            char nextChar = cleanText.charAt(i + order); //gets the characters after the seed

            if (!mapOfStringsAndCharacters.containsKey(starterString))
            {
                mapOfStringsAndCharacters.put(starterString, new LinkedList<Character>()); //if the seed doesn't exist in the map, creates a new list
            }

            mapOfStringsAndCharacters.get(starterString).add(nextChar); //adds the character to the list
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
        String seed = this.generateSeed(order); //generate initial seed

        String newText = "";
        while (newText.length() < numChars)
        {
            LinkedList<Character> charactersOfSeed = generateMap(order).get(seed); //gets the list of characters for the seed

            // if the seed doesn't exist in the map (i.e, there is nothing left at the end), or has no characters generate a new seed
            if (charactersOfSeed == null || charactersOfSeed.size() == 0)
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

/////////////////////////////////////////////////////////////////User Input Error Checking////////////////////////////////////////////////////////////////////////

    /*
     * method that ensures the order is valid.
     * @param order -> the order to check
     * @param input -> the scanner object to read input
     * @return order -> the valid order
     */
    public int ensureValidOrder(int order, Scanner input)
    {
        while (true) //loops infinetely until the order is valid
        {
            if (order > cleanText.length() || order < 1)
            {
                System.out.println("The order cannot be larger than the length of the text or less than zero. Please enter a valid order: ");
                order = input.nextInt();
            }
            else
            {
                break; //if there is nothing to fix, just retun the original order
            }
        }
        System.out.println("The order has been set to " + order + ".");
        return order;
    }

    /*
     * method that ensures the number of characters is valid.
     * @param numChars -> the number of characters to check
     * @param order -> the order of the seed
     * @param input -> the scanner object to read input
     * @return numChars -> the valid number of characters
     */
    public int ensureValidNumChars(int numChars, int order, Scanner input)
    {
        while (true) //loops infinetely until the order is valid
        {
            if (numChars < 1 ||numChars < order || numChars > cleanText.length())
            {
                System.out.println("The number of characters cannot be less than zero, less than the order you have chosen, or greater than the length of the text. Please enter a valid number of characters: ");
                numChars = input.nextInt();
            }
            else
            {
                break; //if there is nothing to fix, just retun the original order
            }
        }
        System.out.println("The number of characters has been set to " + numChars + ".");
        return numChars;
    }
}
