/**
 * Class that represents a ThreeInARow puzzle by extending the default Puzzle class.
 *   @author Owen McGrath
 *   @version 10/28/24
 */
public class
ThreeInARow
extends
Puzzle
{

    /**
    * Constructs a grid with contents read in from a file.
    *   @param filename the file name containing the grid
    *   @throws java.io.FileNotFoundException 
    */
    public 
    ThreeInARow (String filename)
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
        String[] columnArray = getColumnArray(col);

        if (hasThreeInARow(this.grid[row], piece))
        {
            return true;
        }
        
        if (hasThreeInARow(columnArray, piece))
        {
            return true;
        }

        if (countExceedsLimit(this.grid[row], numCols / 2))
        {
            return true;
        }    

        if (countExceedsLimit(columnArray, numRows / 2))
        {
            return true;
        }
        return false;
    }

    /**
    * Gets each column and converts it into an array.
    *   @param col column index
    *   @return columnArray a column as an array
    */
    private String[] 
    getColumnArray(int col)
    {
        String[] columnArray = new String[numRows]; //creates an array with a limit of the number of rows
        for (int i = 0; i < numRows; i++)
        {
            columnArray[i] = this.grid[i][col];
        }
        return columnArray;
    }

    /**
    * Checks the next three spaces to see if they match 
    *   @param array the array to be checked
    *   @param piece the piece to be checked to see if three are matching
    *   @return true if there three consecutive statements, else false
    */
    private boolean 
    hasThreeInARow(String[] array, String piece)
    {
        for (int i = 0; i <= array.length - 3; i++)
        {
            if (array[i].equals(piece) && array[i + 1].equals(piece) && array[i + 2].equals(piece))
            {
                return true;
            }
        }
        return false;
    }

    /**
    * For each piece of the row/column, a black or white count is incremented
    *   @param array the group of pieces to be checked
    *   @limit the number of columns and rows that are valid
    *   @return true if the limit is exceeded, else false
    */
    private boolean 
    countExceedsLimit(String[] array, int limit)
    {
        int whiteCount = 0;
        int blackCount = 0;

        for (String piece : array)
        {
            if (piece.equals("▢"))
            {
                whiteCount++;
            }
            else if (piece.equals("▣"))
            {
                blackCount++;
            }
        }
        return blackCount > limit || whiteCount > limit;
    }
}
