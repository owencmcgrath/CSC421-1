import java.io.File;

public class
Range
extends
Puzzle
{
    public Range (String filename)
    throws java.io.FileNotFoundException
    {
        super(filename);
    }

    @Override
    public boolean 
    hasConflict(int row, int col)
    {
        String piece = this.grid[row][col];

        //if the cell is empty, there cannot be a conflict
        if (piece.equals("-"))
        {
            return false;
        }

        
        
    
        return false;   
    }
}
