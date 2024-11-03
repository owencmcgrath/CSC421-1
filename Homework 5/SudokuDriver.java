import java.util.Scanner;

/**
 * Simple driver for solving a Sudoku puzzle.
 *   @author Dave Reed
 *   @version 10/20/24
 */
public class 
SudokuDriver {
    public static void 
    main(String[] args) throws java.io.FileNotFoundException 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the puzzle file name: ");
        String filename = input.next();
        input.close();
        
        Sudoku sud = new Sudoku(filename);

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
