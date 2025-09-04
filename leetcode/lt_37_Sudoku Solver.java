class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    for (char d = '1'; d <= '9'; d++) {
                        if (isValid(board, r, c, d)) {
                            board[r][c] = d; // 放入數字
                            if (backtrack(board)) return true;
                            board[r][c] = '.'; // 回溯
                        }
                    }
                    return false; // 9 個數字都不行，返回 false
                }
            }
        }
        return true; // 全部填滿
    }

    private boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            // 檢查 row 和 col
            if (board[row][i] == ch) return false;
            if (board[i][col] == ch) return false;

            // 檢查 3x3 box
            int boxRow = (row / 3) * 3 + i / 3;
            int boxCol = (col / 3) * 3 + i % 3;
            if (board[boxRow][boxCol] == ch) return false;
        }
        return true;
    }
}
