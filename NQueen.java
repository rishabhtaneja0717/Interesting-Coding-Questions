/*The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.
*/

class NQueen {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        
        if(n <=0) {
            return res;
        }
        
        List<List<Integer>> colList = new ArrayList<List<Integer>>();
        int [][] grid = new int[n][n];
        
        dfs(n, colList, new ArrayList<Integer>(), grid, 0, new HashSet<Integer>());
        
        for(List<Integer> cols : colList) {
            List<String> config = new ArrayList<String>();
            for(Integer val : cols) {
                String row = convertColumnToString(val, n);
                config.add(row);
            }
            
            res.add(config);
        }
        
        return res;
    }
    
    private boolean dfs(int n, List<List<Integer>> res, List<Integer> list, int [][] grid, int row, Set<Integer> colSet) {
        if(list.size() == n) {
            List<Integer> temp = new ArrayList<Integer>(list);
            res.add(temp);
            return true;
        }
        
        boolean isSolvable = false;
        
        for(int j =0; j<n; j++) {
            if(grid[row][j]==0 &&!colSet.contains(j)) {
                mark(grid, row, j, 0, row+1 );
                list.add(j);
                colSet.add(j);
                boolean isCurrentValid = dfs(n, res, list, grid, row+1, colSet);
                
                if(isCurrentValid) {
                    isSolvable = true;
                }
                
                list.remove(list.size() -1);
                mark(grid, row, j, row+1, 0);
                colSet.remove(j);
            }
        }
        
        return isSolvable;
        
    }
    
    private String convertColumnToString(int col, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i<n; i++) {
            if(i==col) {
                sb.append("Q");
            }else {
                sb.append(".");
            }
        }
        
        return sb.toString();
    }
    
        
    
    
    private void mark (int [][] arr, int i, int j, int checkValue, int assignValue) {
        int n = arr.length;
        
        int p = i+1;
        int q = j+1;

        
        if(arr[i][j] == checkValue) {
            arr[i][j] = assignValue;
        }
        
        // lR slant down
        while(p<n && q<n) {
            if(arr[p][q] == checkValue) {
                arr[p][q] = assignValue;
            }
            p++;
            q++;
        }
        
        p = i -1;
        q = j-1;
        
        // lr slant up
        while(p>=0 && q>=0) {
            if(arr[p][q] == checkValue) {
                arr[p][q] = assignValue;
            }
            p--;
            q--;
        }
        
        p = i-1;
        q = j+1;
        
        //rl slant up
        while(p>=0 && q<n) {
            if(arr[p][q] == checkValue) {
                arr[p][q] = assignValue;
            }
            p--;
            q++;
        }
        
        p = i+1;
        q = j -1;
        
        //rl slant down
        while(p<n && q >=0) {
            if(arr[p][q] == checkValue) {
                arr[p][q] = assignValue;
            }
            p++;
            q--;
        }
        
    }
}
