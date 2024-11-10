import java.util.Scanner;

/**
 * Simple driver for solving a Sudoku puzzle.
 *   @author Dave Reed, Owen McGrath
 *   @version 10/20/24
 */
public class 
PuzzleDriver
{
    /**
    * Driver method that repeatedly prompts the user for the text file and the puzzle solver they want.
    */
    public static void
    main(String[] args) 
    throws 
    java.io.FileNotFoundException
    {

        Scanner input = new Scanner (System.in);
        System.out.println("Enter the puzzle file name: ");
        String filename = input.next();                    
    
        while (true)
        {
            System.out.println("What puzzle would you like solved? (S, 3, R, H) or Q to quit: ");
            String desiredPuzzle = input.next().toUpperCase();

            if (desiredPuzzle.equals("Q"))
            {
                System.out.println("Bye!");
                break;
            }

            Puzzle puzzle = null;
            if (desiredPuzzle.equals("S"))
            {
                System.out.println("You have chosen Sudoku.");
                puzzle = new Sudoku(filename);
            }
            else if (desiredPuzzle.equals("3"))
            {
                System.out.println("You have chosen Three-In-A-Row.");
                puzzle = new ThreeInARow(filename);
            }
            else if (desiredPuzzle.equals("R"))
            {
                System.out.println("You have chosen Range.");
                puzzle = new Range(filename);
            }
            else if (desiredPuzzle.equals("H"))
            {
                System.out.println("You have chosen Hitori.");
                puzzle = new Hitori(filename);
            }
            else
            {
                System.out.println("Your selection was not an option. Please try again.");
                continue;
            }

            if (puzzle != null)
            {
                solvePuzzle(puzzle);
                break;
            }

            input.close();
        }
    }

    /**
    * Helper method that solves the chosen puzzle
    *   @param puzzle the selected puzzle that shoudl be solved
    */
    private static void 
    solvePuzzle(Puzzle puzzle)
    {
        System.out.println(puzzle);

        if (puzzle.solve())
        {
            System.out.println("Solution found:\n" + puzzle);
        }
        else
        {
            System.out.println("No solution has been found.");
        }
    }
}
