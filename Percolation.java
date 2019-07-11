import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int objectNum;
    private int gridWidth;
    private int[] openCount;
    private int openSum=0;

    // creates n-by-n grid, with all sites initially blocked java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull() is outside its prescribed range
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("grid size out of range!");
        }else{
            this.gridWidth = n;
            this.objectNum = n * n;
            openCount = new int[objectNum+2];
            uf = new WeightedQuickUnionUF(objectNum + 2);
            for (int i = 1; i < n + 1; i++) {
                uf.union(0, i);
                uf.union(objectNum + 1 , objectNum + 1 - i);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    // union
    public void open(int row, int col) {
        if (row < 0 || row > gridWidth || col < 0 || col > gridWidth) {
            throw new IllegalArgumentException("index is not between 0 and grid width!");
        }else{
            int siteNum = (row-1)*gridWidth+col;
            openCount[siteNum] = 1;
            openSum++;
            validateAndOpen(row,col-1,siteNum);
            validateAndOpen(row,col+1,siteNum);
            validateAndOpen(row+1,col,siteNum);
            validateAndOpen(row-1,col,siteNum);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 0 || row > gridWidth || col < 0 || col > gridWidth) {
            throw new IllegalArgumentException("index is not between 0 and grid width!");
        }else {
            return openCount[(row - 1) * gridWidth + col] > 0;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 0 || row > gridWidth || col < 0 || col > gridWidth) {
            throw new IllegalArgumentException("index is not between 0 and grid width!");
        }else {
            if(isOpen(row, col)){
                return  uf.find((row - 1) * gridWidth + col) == objectNum + 1 || uf.find((row - 1) * gridWidth + col) == 0;
            }else {
                return false;
            }
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSum;
    }

    // does the system percolate?
    public boolean percolates(){
        return (uf.find(0)==uf.find(objectNum+1));
    }

    // validate that p is a valid index
    private void validateAndOpen(int row,int col, int origin) {
        if(row > 0 && row <= gridWidth && col > 0 && col <= gridWidth){
            int siteNum = (row-1)*gridWidth+col;
            if(isOpen(row, col)){
                uf.union(siteNum,origin);
            }
        }
    }

    // test client (optional)
    public static void main(String[] args){

    }
}
