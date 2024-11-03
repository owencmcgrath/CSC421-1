import java.io.File;
import java.util.Scanner;

/**
 * Class that represents a Sudoku Puzzle (with backtracking solver).
 *   @author Dave Reed
 *   @version 10/20/24
 */
public class Sudoku 
{
    private String[][] grid;
    
    /**
     * Constructs a grid with contents read in from a file.
     *   @param filename the file name containing the grid
     *   @throws java.io.FileNotFoundException 
     */
    public Sudoku(String filename) throws java.io.FileNotFoundException 
    {	
        Scanner infile = new Scanner(new File(filename));   
        int gridSize = infile.nextInt();
        this.grid = new String[gridSize][gridSize];

        for (int r = 0; r < gridSize; r++) 
        {
            for (int c = 0; c < gridSize; c++) 
            {
            	this.grid[r][c] = infile.next();
            }
        }
    }
    
    /**
     * Determines the size of the grid (note: assumed to be square).
     *   @return the size (height and width)
     */
    public int size() 
    {
    	return this.grid.length;
    }

    /**
     * Converts the grid to a String for printing.
     *   @return the String representation of the grid
     */
    public String toString() 
    {
        String output = "";
        for (int r = 0; r < this.size(); r++) 
        {
            for (int c = 0; c < this.size(); c++) 
            {
                output += grid[r][c] + " ";
            }
            output += "\n";
        }
        return output;
    }
    
    /**
     * Fills the Sudoku grid using recursive backtracking.
     *   @return true if grid has been filled, else false
     */
    public boolean solve() 
    {
        return this.solve(0, 0);
    }
    private boolean solve(int row, int col) 
    {
        if (row == this.size()) 
        {                      
            return true; // HAVE FILLED ALL THE ROWS, SO DONE
        }
        else if (col == this.size()) 
        {                 
            return this.solve(row+1, 0); // HAVE FILLED THIS ROW, SO WRAP
        }
        else if (!this.grid[row][col].equals("-")) 
        {
            return this.solve(row, col+1);   // SPOT IS ALREADY FILLED, SO MOVE ON
        }
        else 
        {                                        
            for (int i = 1; i <= this.size() ; i++) 
            {
                this.grid[row][col] = ""+i; // NEED TO FILL SPOT
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
    public boolean hasConflict(int row, int col) 
    {
    	String piece = this.grid[row][col];
        for (int n = 0; n < this.size(); n++) 
        {
            if ((col != n && this.grid[row][n].equals(piece)) || 
                (row != n && this.grid[n][col].equals(piece))) 
                {
                return true;
            }
        }
        
        int squareSide = (int)Math.sqrt(this.grid.length);
        int baseRow = squareSide * (row/squareSide);
        int baseCol = squareSide * (col/squareSide);
        
        for (int r = baseRow; r < baseRow+squareSide; r++) 
        {
            for (int c = baseCol; c < baseCol+squareSide; c++) 
            {
                if ((row != r || col != c) && this.grid[r][c].equals(piece)) 
                {
                    return true;
                }
            }
        }
        return false;
    }
}
