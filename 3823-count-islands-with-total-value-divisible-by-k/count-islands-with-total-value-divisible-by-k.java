import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{i, j});

                    long total = 0;

                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int r = cell[0];
                        int c = cell[1];

                        if (r < 0 || r >= m || c < 0 || c >= n)
                            continue;

                        if (grid[r][c] == 0)
                            continue;

                        total += grid[r][c];
                        grid[r][c] = 0;

                        stack.push(new int[]{r + 1, c});
                        stack.push(new int[]{r - 1, c});
                        stack.push(new int[]{r, c + 1});
                        stack.push(new int[]{r, c - 1});
                    }

                    if (total % k == 0)
                        count++;
                }
            }
        }

        return count;
    }
}