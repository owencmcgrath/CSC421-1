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
        String piece = this.grid[row][col];

        if (!piece.matches("\\d+")) //regex to check if the piece is numeric or not. learned at intnerhsip
        {
            return false;
        }
        
        int pieceAsInteger = Integer.parseInt(piece); //convert to integer so that it can be compared to white count
        int whiteCount = 1; //initialize at one to count the cell itself

        //starts at the left of the cell and moves left until it hits either a black box or an edge
        for (int i = col - 1; i >= 0 && grid[row][i].equals("▢"); i--)
        {
            whiteCount++;
        }

        //starts at the right of the the cell and moves right until it hits either a black box or an edge
        for (int i = col + 1; i < numCols && grid[row][i].equals("▣"); i++)
        {
            whiteCount++;
        }

        //starts at the row abov the cell and moves up until it either hits a black box or an edge
        for (int i = row - 1; i >= 0 && grid[i][col].equals("▣"); i--)
        {
            whiteCount++;
        }

        //starts at the row below the cell and moves down until it either hits a black or an edge
        for (int i = row + 1; i < numRows && grid[i][col].equals("▣"); i++)
        {
            whiteCount++;
        }

        return (whiteCount != pieceAsInteger); //return whether or not the count of white cells doesn't equal the original number  
    }
}
