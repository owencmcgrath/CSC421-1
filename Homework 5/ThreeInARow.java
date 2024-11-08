import java.io.File;

public class
ThreeInARow
extends
Puzzle
{
    public ThreeInARow (String filename)
    throws java.io.FileNotFoundException
    {
        super(filename);
    }

    @Override
    public boolean 
    hasConflict(int row, int col)
    {
        //the current symbol that needs to be checked
        String piece = this.grid[row][col];

        //TODO: two methods, one that creates an array from a column and one that checks in an array
        
        //if the cell doesn't have a square, there cannot be a conflict
        if (piece.equals("-"))
        {
            return false;
        }

        //checks the next three columns for three squares that match piece in a row
        //loops three times to avoid going out of bounds
        for (int i = 0; i <= numCols - 3; i++)
        {
            if (grid[row][i].equals(piece) && grid[row][i + 1].equals(piece) && grid[row][i + 2].equals(piece))
            {
                return true; //there are three squares and there is a conflict
            }   
        }

        //checks the next three columns for three squares that match piece in a column
        //loops three times to avoid going out of bounds
        for (int i = 0; i <= numRows - 3; i++)
        {
            if (grid[i][col].equals(piece) && grid[i + 1][col].equals(piece) && grid[i + 2][col].equals(piece))
            {
                return true; //there are three squares that create a conflict
            }   
        }

        //counts how many black or white squares are in a row
        int blackCountRow = 0;
        int whiteCountRow = 0;
        for (int i = 0; i < numCols; i++) 
        {
            if (grid[row][i].equals("▣")) 
            {
                blackCountRow++;
            }
            if (grid[row][i].equals("▢")) 
            {
                whiteCountRow++;
            }
        }
    
        //since there has to be the number of columns (or rows) / 2 number of squares, this checks to see if there are any more.
        //if there are, there must be a conflict    
        if (blackCountRow > numCols / 2 || whiteCountRow > numCols / 2) 
        {
            return true;
        }

        //does the same thing as above, but for the rows
        int blackCountCol = 0;
        int whiteCountCol = 0;
        for (int i = 0; i < numRows; i++) 
        {
            if (grid[i][col].equals("▣")) 
            {
                blackCountCol++;
            }
            if (grid[i][col].equals("▢")) 
            {
                whiteCountCol++;
            }
        } 
        
        if (blackCountCol > numRows / 2 || whiteCountCol > numRows / 2) 
        {
            return true;
        }              
        return false;
    }
}
