import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF UnionGrid;
    private int[][] grid;
    private int numberOfOpenSites;


    public Percolation(int N) {
        // TODO: Fill in this constructor.
        UnionGrid = new WeightedQuickUnionUF(N*N);
        grid = new int[N][N];
        numberOfOpenSites= 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || col < 0 || row > grid.length || col > grid.length) throw new IndexOutOfBoundsException();
        grid[row][col] = 1;
        numberOfOpenSites++;
        int number = row*grid.length+col;
        if (row != 0 && isOpen(row-1, col)) UnionGrid.union(number-grid.length, number);
        if (row != grid.length-1 && isOpen(row+1, col)) UnionGrid.union(number, number+grid.length);
        if (col != 0 && isOpen(row, col-1)) UnionGrid.union(number, number-1);
        if (col != grid.length-1 && isOpen(row, col+1)) UnionGrid.union(number, number+1);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || col < 0 || row > grid.length || col > grid.length) throw new IndexOutOfBoundsException();
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || col < 0 || row > grid.length || col > grid.length) throw new IndexOutOfBoundsException();
        if (isOpen(row, col) && row == 0) return true;
        for (int i = 0; i < grid.length; i++) {
            if (row != 0 && UnionGrid.connected(row*grid.length+col, i)) return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSites;

    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int i = 0; i < grid.length; i++) {
            if (isFull(grid.length-1, i)) return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
