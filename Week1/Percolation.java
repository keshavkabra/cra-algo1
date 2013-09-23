public class Percolation {
    private int size, totSize;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF bw;
    
    public Percolation(int N)             
    {
        size = N;
        totSize = N * N + 2;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(totSize);
        bw = new WeightedQuickUnionUF(totSize);
   /*     for(int i = 1 ; i <= N ; i++)
        {
            uf.union(0, i);
            uf.union(totSize - 1, totSize - 1 - i);
        }*/
    }
        
    public void open(int i, int j)  
    {
        check(i, j);
        if (!grid[i-1][j-1])
        {
            grid[i-1][j-1] = true;
            if (i != 1 && grid[i-2][j-1]) {
                uf.union(map(i, j), map(i-1, j));
                bw.union(map(i, j), map(i-1, j));
            }
            if (i != size && grid[i][j-1]) {
                uf.union(map(i, j), map(i+1, j));
                bw.union(map(i, j), map(i+1, j));
            }
            if (j != 1 && grid[i-1][j-2]) {
                uf.union(map(i, j), map(i, j-1));
                bw.union(map(i, j), map(i, j-1));
            }
            if (j != size && grid[i-1][j]) {
                uf.union(map(i, j), map(i, j+1));
                bw.union(map(i, j), map(i, j+1));
            }
            if (i == 1) {
                uf.union(0, map(i, j));
                bw.union(0, map(i, j));
            }
            if (i == size) {
                uf.union(size * size + 1, map(i, j));
            }
        }
    }
        
    public boolean isOpen(int i, int j)    
    {
        check(i, j);
        return grid[i - 1 ][j - 1];   
    }

    public boolean isFull(int i, int j)    
    {
        check(i, j);
        return bw.connected(0, map(i, j));
    }
    public boolean percolates()            
    {
        return uf.connected(0, totSize-1);
    }

    private int map(int i, int j)
    {
        return (i - 1) * size + j;
    }

    private void check(int i, int j)
    {
        if (i < 1 || j < 1 || i > size || j > size)
            throw new IndexOutOfBoundsException();
    }
}

