import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 * Class that reads in and stores the contents of a text file.
 * @author Dave Reed
 * @version 8/20/24
 */
public class ApproxGenerator
{
    private String cleanText;
    private Random randy;
    private String seed;

    /**
     * Constructs a FileProcessor object that reads in text from a file. The
     * text is processed to remove non-letters and adjacent spaces, and letters
     * are converted to uppercase.
     * @param fileName the file containing the text
     */
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

        //get a random integer and then get the character at that integer
        int randomInteger = randy.nextInt(cleanText.length() - (order - 1));
        System.out.println(randomInteger);

        for (int i = randomInteger; i < randomInteger + order; i++)
        {
            seed += cleanText.charAt(i);
        }
        System.out.println(seed);

        infile.close();
    }

    /**
     * Generates a string of the specified order and length, matching the
     * statistical distribution of letters and spaces as the specified file.
     * @param numChars the length of the generated string
     * @param order the order for the approximation
     * @return the generated string
     */
    public String generate(int numChars, int order)
    {
        List<String> listOfFollowinCharacters = new LinkedList<>();
        HashMap<String, LinkedList<Character>> orderStorage = new HashMap<String, LinkedList<Character>>();



        String newText = "";
        while (newText.length() < numChars)
        {
            int index = this.randy.nextInt(this.cleanText.length());
            newText += this.cleanText.charAt(index);
        }
        return newText;
    }
}
