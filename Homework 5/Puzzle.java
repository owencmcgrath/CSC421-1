import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class that represents an abstract Puzzle (with backtracking solver).
 *   @author Dave Reed, Owen McGrath
 *   @version 10/20/24
 */
public abstract class 
Puzzle 
{
    String[][] grid;
    int numCols;
    int numRows;
    ArrayList<String> possibleValues = new ArrayList<String>();
    
    /**
     * Constructs a grid with contents read in from a file.
     *   @param filename the file name containing the grid
     *   @throws java.io.FileNotFoundException 
     */
    public 
    Puzzle(String filename) 
    throws java.io.FileNotFoundException 
    {	
        try (Scanner infile = new Scanner(new File(filename))) {

            //collections is used since addAll cannot accept an array and i do not want to do this with two nested for loops :thumbsup:
            Collections.addAll(possibleValues, infile.nextLine().split(" "));
            this.numCols = infile.nextInt();
            this.numRows = infile.nextInt();
            infile.nextLine();

            this.grid = new String[numRows][numCols];

            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    this.grid[r][c] = infile.next();
                }
            }
        }
    }

    /**
    * Setter for the number of columns the grid has.
    *   @return this.numCols
    */
    public int
    numCols()
    {
        return this.numCols;        
    }

    /**
    * Setter for the number of rows.
    *   @return this.numRows
    */
    public int
    numRows()
    {
        return this.numRows;
    }

    /**
    * Setter for the possible values in the puzzle.
    *   @return this.possibleValues
    */
    public ArrayList<String> getPossibleValues()
    {
        return this.possibleValues;
    }
    
    /**
     * Converts the grid to a String for printing.
     *   @return the String representation of the grid
     */
    @Override
    public String 
    toString() 
    {
        String output = "";
        for (int r = 0; r < this.numRows(); r++) 
        {
            for (int c = 0; c < this.numCols(); c++) 
            {
                output += this.grid[r][c] + " ";
            }
            output += "\n";
        }
        return output;
    }
    
    /**
     * Fills the Sudoku grid using recursive backtracking.
     *   @return true if grid has been filled, else false
     */
    public boolean 
    solve() 
    {
        return this.solve(0, 0);
    }
    
    private boolean 
    solve(int row, int col) 
    {
        if (row == this.numRows()) 
        {                      
            return true;
        }
        else if (col == this.numCols()) 
        {                 
            return this.solve(row+1, 0); 
        }
        else if (!this.grid[row][col].equals("-")) 
        {
            return this.solve(row, col+1);   
        }
        else 
        {                                        
            for (String value : possibleValues)
            {
                this.grid[row][col] = value; 
                if (!this.hasConflict(row, col) && this.solve(row, col+1)) 
                {
                    return true;
                }
                this.grid[row][col] = "-";   
            }
            return false;
        }
    }
    
    /**
     * Determines if there is a conflict after placing piece in (row,col).
     *   @param row the row where the piece was placed
     *   @param col the column where the piece was placed
     *   @return true if that pieces caused a conflict, else false
     */

    public abstract boolean
    hasConflict(int row, int col);
}
