/**
 * Class that represents a Sudoku Puzzle (with backtracking solver).
 *   @author Dave Reed, Owen McGrath
 *   @version 10/20/24
 */
public class 
Sudoku
extends
Puzzle 
{   
    /**
     * Constructs a grid with contents read in from a file.
     *   @param filename the file name containing the grid
     *   @throws java.io.FileNotFoundException 
     */
    public 
    Sudoku(String filename) 
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
        for (int n = 0; n < this.numCols(); n++) 
        {
            if (col != n && this.grid[row][n].equals(piece)) 
            {
                return true;
            }
        }
        for (int n = 0; n < this.numRows(); n++) 
        {
            if (row != n && this.grid[n][col].equals(piece)) 
            {
                return true;
            }
        }
        return isPieceInSquare(row, col, piece);
    }

    /**
    * Helper method to check the subgrid.
    *   @param row the row where the piece was placed
    *   @param col the column where the piece was placed
    *   @return true if a conflict exists in the subgrid, else false
    */
    private boolean 
    isPieceInSquare(int row, int col, String piece)
    {
        int squareSide = (int) Math.sqrt(this.grid.length);
        int baseRow = squareSide * (row / squareSide);
        int baseCol = squareSide * (col / squareSide);
               
        for (int r = baseRow; r < baseRow + squareSide; r++) 
        {
            for (int c = baseCol; c < baseCol + squareSide; c++) 
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
