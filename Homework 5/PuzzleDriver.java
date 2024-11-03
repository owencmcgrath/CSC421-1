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
    }    
}
