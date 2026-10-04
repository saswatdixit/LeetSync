class Solution {
    public int totalNQueens(int n) {
        int[] count = {0};

        boolean[] cols = new boolean[n];
        boolean[] diagonals1 = new boolean[2 * n - 1];
        boolean[] diagonals2 = new boolean[2 * n - 1];

        backtrack(0, n, cols, diagonals1, diagonals2, count);

        return count[0];
    }

    private void backtrack(int row, int n,
                           boolean[] cols,
                           boolean[] diagonals1,
                           boolean[] diagonals2,
                           int[] count) {

        if (row == n) {
            count[0]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diagonals1[d1] || diagonals2[d2]) {
                continue;
            }

            cols[col] = true;
            diagonals1[d1] = true;
            diagonals2[d2] = true;

            backtrack(row + 1, n, cols, diagonals1, diagonals2, count);

            cols[col] = false;
            diagonals1[d1] = false;
            diagonals2[d2] = false;
        }
    }
}