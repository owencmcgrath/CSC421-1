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

        if (!Character.isDigit(piece.charAt(0)) || piece.equals("-"))
        {
            return false; //not a cell with a number, so dont check
        }

        int whiteCount = 1; //gotta count the cell itself, playa
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
        return (whiteCount != numberPiece); 
    }
}
