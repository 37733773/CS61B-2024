import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    int[][] grid;
    int openNumber;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        grid[row][col] = 1;
        openNumber++;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (grid[row][col] == 1 || grid[row][col] == 2 || grid[row][col] == 3) return true;
        return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (!isOpen(row, col)) return false;
        if (grid[row][col] == 2 || row == 0) {
            grid[row][col] = 2;
            return true;
        }
       grid[row][col] = 3;
       if (grid[row - 1][col] != 3 &&  isFull(row - 1, col) ||
               row != grid.length-1 && grid[row + 1][col] != 3 && isFull(row + 1, col) ||
               col != grid.length-1 && grid[row][col + 1] != 3 && isFull(row, col + 1)  ||
               col != 0 && grid[row][col - 1] != 3 && isFull(row, col - 1)  ) {
           grid[row][col] = 2;
           return true;
       }
      grid[row][col] = 1;
       return false;
    }

    public int numberOfOpenSites() {
       return openNumber;
    }

    public boolean percolates() {
       for (int i = 0; i < grid.length; i++) {
           if (isFull(grid.length - 1, i)) return true;
       }
       return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
