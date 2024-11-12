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
        return hasAdjacentBlack(row, col);
    }

    /**
    * Method that checks if the number is valid based on iteration in every direction
    *   @param row the row that is to be checked
    *   @col the column that is to be checked
    *   @return check to see if the number is valid
    */
    private boolean 
    numIsValid(int row, int col) 
    {
        String piece = this.grid[row][col];
        int pieceAsInteger = Integer.parseInt(piece);
        
        int whiteCount = 1; //initailize at one to count the cell itself
        int possible = 0; //possible cells to be checked

        //0 means to not move and > 1 means to move in a certain direction
        int[] leftCount = countSpaces(row, col, 0, -1);
        int[] rightCount = countSpaces(row, col, 0, 1);
        int[] upCount = countSpaces(row, col, -1, 0);
        int[] downCount = countSpaces(row, col, 1, 0);

        //number of white spaces found (zeroth place) and all the possible spaces (first place)
        whiteCount += leftCount[0] + rightCount[0] + upCount[0] + downCount[0];
        possible += leftCount[1] + rightCount[1] + upCount[1] + downCount[1];

        //check taht we determined togehter
        return (whiteCount <= pieceAsInteger && (whiteCount + possible) >= pieceAsInteger);
    }

    /**
    * Helper method that counts the number of spaces
    *   @param row the row that is to be checked
    *   @param col the colum to be checked
    *   @param rowStep iterating the row
    *   @param colStep iterating the col
    *   @return a new array with the white and possible count
    */
    private int[] 
    countSpaces(int row, int col, int rowStep, int colStep) 
    {
        int white = 0;
        int possible = 0;
        boolean foundDash = false;

        //udpates position when checking the next cell, replaces for loops
        row += rowStep;
        col += colStep;

        //while we aren't off the edge and it doesn't equal a black square    
        while (row >= 0 && row < numRows() && col >= 0 && col < numCols() && !grid[row][col].equals("▣")) 
        {
            if (grid[row][col].equals("-")) 
            {
                foundDash = true;
            }

            if (!foundDash) 
            {
                white++;
            } 
            else 
            {
                possible++;
            }

            //continue the iteration
            row += rowStep;
            col += colStep;
        }
        //creates and returns a new int array with the white and possible values stored
        return new int[] {white, possible};
    }

    /**
    * Checks for an adjacent black sqaure
    *   @param row the row to be checked
    *   @param col the col to be chacked
    *   @return true if there is an adjacent black square,else false
    */
    private boolean 
    hasAdjacentBlack(int row, int col) 
    {
    
        if (!grid[row][col].equals("▣")) 
        {
            return false;
        }

        //move left
        if (col > 0 && grid[row][col-1].equals("▣")) 
        {
            return true;
        }

        //move right
        if (col < numCols() - 1 && grid[row][col+1].equals("▣")) 
        {
            return true;
        }

        //move up
        if (row > 0 && grid[row-1][col].equals("▣")) {
            return true;
        }

        //move down
        if (row < numRows() - 1 && grid[row+1][col].equals("▣")) 
        {
            return true;
        }
        return false;
    }
}

