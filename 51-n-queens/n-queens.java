class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diagonals1 = new boolean[2 * n - 1];
        boolean[] diagonals2 = new boolean[2 * n - 1];

        backtrack(0, n, board, cols, diagonals1, diagonals2, result);

        return result;
    }

    private void backtrack(int row, int n, char[][] board,
                           boolean[] cols,
                           boolean[] diagonals1,
                           boolean[] diagonals2,
                           List<List<String>> result) {

        if (row == n) {
            List<String> solution = new ArrayList<>();

            for (char[] r : board) {
                solution.add(new String(r));
            }

            result.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diagonals1[d1] || diagonals2[d2]) {
                continue;
            }

            board[row][col] = 'Q';
            cols[col] = true;
            diagonals1[d1] = true;
            diagonals2[d2] = true;

            backtrack(row + 1, n, board, cols, diagonals1, diagonals2, result);

            board[row][col] = '.';
            cols[col] = false;
            diagonals1[d1] = false;
            diagonals2[d2] = false;
        }
    }
}