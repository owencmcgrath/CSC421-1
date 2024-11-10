import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class that represents a Hitori Puzzle (with backtracking solver).
 *   @author Owen McGrath
 *   @version 10/20/24
 */
public class 
Hitori
extends
Puzzle 
{   
    /**
     * Constructs a grid with contents read in from a file.
     *   @param filename the file name containing the grid
     *   @throws java.io.FileNotFoundException 
     */
    public 
    Hitori(String filename) 
    throws java.io.FileNotFoundException 
    {	
        super(filename);
    }

    /**
     * Determines if there is a conflict after placing piece in (row,col).
     *   @param row the row where the piece was placed
     *   @param col the column where the piece was placed
     *   @return true if that pieces caused a conflict, else false
     */

    @Override
    public boolean 
    hasConflict(int row, int col)
    {
        String piece = this.grid[row][col];

        int whiteCount = 1; 
        int numberPiece = Integer.parseInt(piece);

        //starts at the left of the cell and moves left until it hits either a black box or an edge
        for (int i = col - 1; i >= 0; i--)
        {
            if (grid[row][i].equals("▣"))
            {
                break;
            }
            whiteCount++;
        }

        //starts at the right of the the cell and moves right until it hits either a black box or an edge
        for (int i = col + 1; i < numCols; i++)
        {
            if (grid[row][i].equals("▣"))
            {
                break;
            }
            whiteCount++;
        }

        //starts at the row abov the cell and moves up until it either hits a black box or an edge
        for (int i = row - 1; i >= 0; i--)
        {
            if (grid[i][col].equals("▣"))
            {
                break;
            }
            whiteCount++;
        }

        //starts at the row below the cell and moves down until it either htis a black or an edge
        for (int i = row + 1; i < numRows; i++)
        {
            if (grid[i][col].equals("▣"))
            {
                break;
            }
            whiteCount++;
        }

        //return whether or not the count of white celsl doesn't equal the number 
        if (whiteCount != numberPiece)
        {
            return true;
        } 

        //TODO: helper method that checks for black squares
        //TODO: only check all rows above it in the same column and all the columns to the left in the same row
        if (col > 0) //put an and here to check for the same col check
        {
            if (grid[row][col].equals("▣") && grid[row][col - 1].equals("▣"))
            {
                return true;   
            }
        }

        if (col < numCols - 1)
        {
            if (grid[row][col].equals("▣") && grid[row][col + 1].equals("▣"))
            {
                return true;   
            }
        }
        
        if (row > 0)
        {
            if (grid[row][col].equals("▣") && grid[row - 1][col].equals("▣"))
            {
                return true;   
            }
        }

        if (row < numRows - 1)
        {
            if (grid[row][col].equals("▣") && grid[row + 1][col].equals("▣"))
            {
                return true;   
            }
        }
                
        return false;
    }
}
