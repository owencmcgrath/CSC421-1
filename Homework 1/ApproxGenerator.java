import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Class that reads in and stores the contents of a text file.
 * @author Dave Reed, Owen McGrath
 * @version 8/20/24
 */
public class ApproxGenerator
{
    private String cleanText;
    private Random randy;
    private String seed;

    public ApproxGenerator(String fileName, int order) throws Exception
    {
        this.cleanText = "";
        this.randy = new Random();
        this.seed = "";

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
        System.out.println(cleanText);

        //length - order - 1 ensures that there is always space for the seed
        int randomInteger = randy.nextInt(cleanText.length() - (order - 1));

        for (int i = randomInteger; i < randomInteger + order; i++)
        {
            seed += cleanText.charAt(i);
        }

        infile.close();
    }

    public Map<String, LinkedList<Character>> generateMap(int order)
    {
        HashMap<String, LinkedList<Character>> mapOfSeedsAndCharacters = new HashMap<String, LinkedList<Character>>();

        for (int i = 0; i < cleanText.length() - order; i++) {
            String seed = cleanText.substring(i, i + order); //gets current index and the next order characters
            char nextChar = cleanText.charAt(i + order); //gets the character after the seed

            LinkedList<Character> charactersOfSeed = mapOfSeedsAndCharacters.getOrDefault(seed, new LinkedList<>()); //gets the list of characters for the seed, if it doesn't exist, creates a new list
            charactersOfSeed.add(nextChar); //adds the next character to the list
            mapOfSeedsAndCharacters.put(seed, charactersOfSeed); //puts the seed and the list of characters into the map

            String nextSeed = seed.substring(1) + nextChar; //creates the next seed by removing the first character of the current seed and adding the next character
        }

        return mapOfSeedsAndCharacters;
    }


    public String generate(int numChars, int order)
    {
        String newText = "";
        while (newText.length() < numChars)
        {
            this.generateMap(order);
        }
        return newText;
    }
}
