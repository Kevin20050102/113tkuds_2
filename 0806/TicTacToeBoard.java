import java.util.*;

public class TicTacToeBoard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[3][3];
        for (char[] row : board) Arrays.fill(row, ' ');

        char player = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(board);
            System.out.println("玩家 " + player + " 請輸入列(0-2) 和 行(0-2): ");
            int r = sc.nextInt();
            int c = sc.nextInt();

            if (r < 0 || r > 2 || c < 0 || c > 2 || board[r][c] != ' ') {
                System.out.println("無效位置，請重新輸入！");
                continue;
            }

            board[r][c] = player;

            if (checkWin(board, player)) {
                printBoard(board);
                System.out.println("玩家 " + player + " 獲勝！");
                gameOver = true;
            } else if (isFull(board)) {
                printBoard(board);
                System.out.println("平手！");
                gameOver = true;
            } else {
                player = (player == 'X') ? 'O' : 'X';
            }
        }
    }

    static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    static boolean checkWin(char[][] b, char p) {
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p) return true;
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p) return true;
        }
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
        return false;
    }

    static boolean isFull(char[][] b) {
        for (char[] row : b) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }
}

