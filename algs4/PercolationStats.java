public class PercolationStats {
    private final double[] threshold;
    private final int N;
    private final int T;
    
    public PercolationStats(int n, int t) {
        if (n < 0 || t < 1) {
            throw new IllegalArgumentException();
        }
        threshold = new double[t];
        N = n;
        T = t;
        monteCarloSimulation();
    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    public double confidenceLo() {
    return mean() - (1.96 * stddev() / Math.sqrt(threshold.length));
    }

    public double confidenceHi() {
    return mean() + (1.96 * stddev() / Math.sqrt(threshold.length));
    }
    private void monteCarloSimulation() {
        final int N1 = N;
        final int T1 = T;
        final double totNum = N1*N1;
        
        for (int i = 0; i < T1; i++) {
            double openSites = 0;
            final Percolation percolation = new Percolation(N1);
            
            while (!percolation.percolates()) {
                final int I = StdRandom.uniform(N1) + 1;
                final int J = StdRandom.uniform(N1) + 1;
                
                if (!percolation.isOpen(I, J)) {
                    percolation.open(I, J);
                    openSites++;
                }
            }
            
            // Percolation complete; now record threshold
            final double thres = openSites / totNum;
            threshold[i] = thres;
        }
    }
    
    public static void main(final String[] args) {
        if (args.length < 2) {
            StdOut.println("Two arguments");
        }
        final int n = Integer.valueOf(args[0]);
        final int t = Integer.valueOf(args[1]);
        
        final PercolationStats stats = new PercolationStats(n, t);
        
        final double mean = stats.mean();
        final double stddev = stats.stddev();
        StdOut.println("mean                    = " + mean);
        StdOut.println("stddev                  = " + stddev);
        StdOut.println("95% confidence interval = " + (mean
        - (1.96*stddev/Math.sqrt(t))) + ", " 
        + (mean + (1.96*stddev/Math.sqrt(t))));
    }
}