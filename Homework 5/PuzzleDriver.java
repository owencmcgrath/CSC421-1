import java.util.Scanner;

/**
 * Simple driver for solving a Sudoku puzzle.
 *   @author Dave Reed, Owen McGrath
 *   @version 10/20/24
 */
public class 
PuzzleDriver {
    public static void 
    main(String[] args) throws java.io.FileNotFoundException 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the puzzle file name: ");
        String filename = input.next();
        System.out.println("What puzzle would you like solved? (S, 3, R): ");
        String desiredPuzzle = input.next();
        input.close();

        if (desiredPuzzle.equals("S"))
        {
            System.out.println("You have chosen sudoku.");
            Puzzle sud = new Sudoku(filename);
            System.out.println(sud);
                    
            if (sud.solve()) 
            {
                System.out.println("Solution found:\n" + sud);
            }
            else 
            {
        	System.out.println("No solution possible");
            }
        }
        else if (desiredPuzzle.equals("3"))
        {
            System.out.println("You have chosen three-in-a-row.");
            Puzzle three = new ThreeInARow(filename);
            System.out.println(three);
                    
            if (three.solve()) 
            {
                System.out.println("Solution found:\n" + three);
            }
            else 
            {
        	System.out.println("No solution possible");
            }                                            
        }
        else if (desiredPuzzle.equals("R"))
        {
            System.out.println("You have chosen range.");
            Puzzle range = new Range(filename);
            System.out.println(range);

            if (range.solve())
            {
                System.out.println("Solution found: \n" + range);
            }
            else
            {
                System.out.println("No solution possible");
            }
        }
        else
        {
            System.out.println("Your selection was unrecognized. Please try again.");
        } 
    }    
}
