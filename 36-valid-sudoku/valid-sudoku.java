class Solution {
    public boolean isValidSudoku(char[][] board) {

        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                char value = board[r][c];

                if (value == '.') {
                    continue;
                }

                int box = (r / 3) * 3 + (c / 3);

                if (rows[r].contains(value)) {
                    return false;
                }

                if (cols[c].contains(value)) {
                    return false;
                }

                if (boxes[box].contains(value)) {
                    return false;
                }

                rows[r].add(value);
                cols[c].add(value);
                boxes[box].add(value);
            }
        }

        return true;
    }
}