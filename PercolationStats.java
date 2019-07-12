import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private static final double FACTOR = 1.96;
    private final double[] successNum;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("grid size out of range!");
        } else {
            this.successNum = new double[trials];
            for (int i = 0; i < trials; i++) {
                Percolation perc = new Percolation(n);
                while (!perc.percolates()) {
                    int row = StdRandom.uniform(1, n + 1);
                    int col = StdRandom.uniform(1, n + 1);
                    if (!perc.isOpen(row, col)) {
                        perc.open(row, col);
                    }
                }
                this.successNum[i] = (perc.numberOfOpenSites() / (double) (n * n));
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(successNum);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(successNum);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(successNum) - FACTOR * StdStats.stddev(successNum) / Math.sqrt(successNum.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(successNum) + FACTOR * StdStats.stddev(successNum) / Math.sqrt(successNum.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 10;
        int trials = 20000;
        if (args.length == 2) n = Integer.parseInt(args[0]);
        if (args.length == 2) trials = Integer.parseInt(args[1]);
        PercolationStats perc = new PercolationStats(n, trials);
        StdOut.println("mean:" + perc.mean());
        StdOut.println("stddev:" + perc.stddev());
        StdOut.println("hi:" + perc.confidenceHi());
        StdOut.println("lo:" + perc.confidenceLo());
    }

}
