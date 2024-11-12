import java.io.File;

/**
 * Class that represents a Range puzzle by extending the default Puzzle class.
 *   @author Owen McGrath
 *   @version 10/28/24
 */
public class
Range
extends
Puzzle
{
    /**
     * Constructs a grid with contents read in from a file.
     *   @param filename the file name containing the grid
     *   @throws java.io.FileNotFoundException 
     */
    public 
    Range (String filename)
    throws 
    java.io.FileNotFoundException
    {
        super(filename);
    }

    /**
     * Determines if there is a conflict after placing piece in (row,col).
     *   @param row the row where the piece was placed
     *   @param col the column where the piece was placed
     *   @return true if that piece caused a conflict, else false
     */
    @Override
    public boolean 
    hasConflict(int row, int col)
    {
        for (int i = 0; i < numRows(); i++) 
        {
            for (int j = 0; j < numCols(); j++) 
            {
                if (!grid[i][j].equals("-") && !grid[i][j].equals("▣") && !grid[i][j].equals("▢")) 
                {
                    if (!numIsValid(i, j)) 
                    {
                        return true;
                    }
                } 
            }  
        }

        if (grid[row][col].equals("▣")) 
        {
            if (col > 0 && grid[row][col-1].equals("▣"))
            {
                return true;
            }
            if (col < numCols() - 1 && grid[row][col+1].equals("▣"))
            {
                return true;
            }
            if (row > 0 && grid[row-1][col].equals("▣"))
            {
                return true;
            }
            if (row < numRows() - 1 && grid[row+1][col].equals("▣"))
            {
                return true;
            }
        }
        return false;
    }

    /**
    * Method that checks if      
    *
    *
    */
    public boolean
    numIsValid(int row, int col)
    {
        String piece = this.grid[row][col];
        int pieceAsInteger = Integer.parseInt(piece); //convert to integer so that it can be compared to white count
        
        int whiteCount = 1; //initialize at one to count the cell itself
        int possible = 0;
        boolean foundDash = false;
        
        //starts at the left of the cell and moves left until it hits either a black box or an edge
        for (int i = col - 1; i >= 0 && !grid[row][i].equals("▣"); i--)
        {
            if (grid[row][i].equals("-"))
            {
                foundDash = true;
            }
            if (foundDash == false)
            {
                whiteCount++;   
            }
            else
            {
                possible++;
            }
        }

        //starts at the right of the the cell and moves right until it hits either a black box or an edge
        foundDash = false;
        for (int i = col + 1; i < numCols && !grid[row][i].equals("▣"); i++)
        {
            if (grid[row][i].equals("-"))
            {
                foundDash = true;
            }
            if (foundDash == false)
            {
                whiteCount++;   
            }
            else
            {
                possible++;
            }
        }

        foundDash = false;
        //starts at the row abov the cell and moves up until it either hits a black box or an edge
        for (int i = row - 1; i >= 0 && !grid[i][col].equals("▣"); i--)
        {
            if (grid[i][col].equals("-"))
            {
                foundDash = true;
            }
            
            if (foundDash == false)
            {
                whiteCount++;   
            }
            else
            {
                possible++;
            }
        }

        foundDash = false;
        //starts at the row below the cell and moves down until it either hits a black or an edge
        for (int i = row + 1; i < numRows && !grid[i][col].equals("▣"); i++)
        {
            if (grid[i][col].equals("-"))
            {
                foundDash = true;
            }
            
            if (foundDash == false)
            {
                whiteCount++;   
            }
            else
            {
                possible++;
            }
        }
        return (whiteCount <= pieceAsInteger && (whiteCount + possible) >= pieceAsInteger);
    }
}
