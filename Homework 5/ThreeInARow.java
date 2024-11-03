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
        return false;
    }
}
