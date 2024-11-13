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
    throws 
    java.io.FileNotFoundException 
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

        if (hasDuplicateInRow(row, col, piece) || hasDuplicateInColumn(row, col, piece))
        {
            return true;
        }

        if (hasAdjacentBlackSquares(row, col))
        {
            return true;
        }

        return false;
    }

    /**
     * Checks to see if there is a duplicate value in teh row.
     *   @param row the cell to check
     *   @param col the column to be checked in
     *   @param piece teh value to search for dups for
     *   @return true if a dupe is found and false if not
     */
    private boolean 
    hasDuplicateInRow(int row, int col, String piece)
    {
        for (int column = 0; column < this.numCols; column++)
        {
            //checks to see if there is a duplicate value in the row that is not blacked out
            if (column != col && this.grid[row][column].equals(piece) && !this.grid[row][column].equals("▣"))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if there is a duplicate value in teh column.
     *   @param row the row to check
     *   @param col the column to be checked in
     *   @param piece teh value to search for dups for
     *   @return true if a dupe is found and false if not
     */
    private boolean 
    hasDuplicateInColumn(int row, int col, String piece)
    {
        for (int r = 0; r < this.numRows; r++)
        {
            //checks to see if there is a duplicate value in the column that is not blacked out
            if (r != row && this.grid[r][col].equals(piece) && !this.grid[r][col].equals("▣"))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if there are adjancet black squares
     *   @param row the row to check
     *   @param col the col to check
     *   @return true if there is an adjacent black square, else false
     */
    private boolean 
    hasAdjacentBlackSquares(int row, int col)
    {
        if(!grid[row][col].equals("▣"))
        {
            return false;
        }

        //checks to see if there is a black square to the left
        if (col > 0 && grid[row][col - 1].equals("▣"))
        {
            return true;
        }

        //checks to see if there is a black square to the right
        if (col < numCols - 1 && grid[row][col + 1].equals("▣"))
        {
            return true;
        }

        //checks to see if there is a black square above
        if (row > 0 && grid[row - 1][col].equals("▣"))
        {
            return true;
        }

        //cheks to see if there a black square below
        if (row < numRows - 1 && grid[row + 1][col].equals("▣"))
        {
            return true;
        }        
        return false;
    }

    /**
    * Overrides the toString method to replace values that are conflicts.
    *   @return output the new version of the string with the blacked out values
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
                String piece = this.grid[r][c];
                if (hasConflict(r, c))
                {
                    output += "▣ ";
                }
                else
                {
                    output += piece +  " ";
                }
            }
            output += "\n";
        }
        return output;
    }
}


