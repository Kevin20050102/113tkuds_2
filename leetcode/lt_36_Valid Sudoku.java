class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 9 rows, 9 cols, 9 boxes
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue; // 空格直接跳過
                
                int num = ch - '1'; // '1'~'9' 轉成 0~8
                
                // 計算 box index
                int boxIndex = (r / 3) * 3 + (c / 3);
                
                // 如果已經出現過，無效
                if (rows[r][num] || cols[c][num] || boxes[boxIndex][num]) {
                    return false;
                }
                
                // 標記已出現
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        
        return true;
    }
}
