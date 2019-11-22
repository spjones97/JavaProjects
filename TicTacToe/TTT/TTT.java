package TTT;

public class TTT {
    private char[][] board;
    private char currentPlayerMark;

    // Initialize board and current player
    public TTT() {
        this.board = new char[3][3];
        this.currentPlayerMark = 'x';
    }

    // Reset the board
    public void initializeBoard() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board[x][y] = '-';
            }
        }
    }

    // Print out board
    public void printBoard() {
        System.out.println("--------------");

        for (int x = 0; x < 3; x++) {
            System.out.print("| ");
            for (int y = 0; y < 3; y++) {
                System.out.print(board[x][y] + " | ");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

    // Check to see if board it full
    public boolean isBoardFull() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for win using row, col, and diagonal methods
    public boolean checkForWin() {
        if (checkRowsWin() || checkColumnsWin() || checkDiagonalsWin()) {
            return true;
        }
        return false;
    }

    private boolean checkRowsWin() {
        for (int x = 0; x < 3; x++) {
            if (checkRowCol(board[x][0], board[x][1], board[x][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsWin() {
        for (int y = 0; y < 3; y++) {
            if (checkRowCol(board[0][y], board[1][y], board[2][y])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    // Check to see if three spots are contained by the same player
    private boolean checkRowCol(char c1, char c2, char c3) {
        if (c1 == '-' || c2 == '-' || c3 == '-') {
            return false;
        }
        if (c1 == c2 && c2 == c3) {
            return true;
        }
        return false;
    }

    // Return the current player
    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    // Change turns
    public void changePlayer() {
        if (currentPlayerMark == 'x') {
            currentPlayerMark = 'o';
        } else {
            currentPlayerMark = 'x';
        }
    }

    // Place players spot on the board
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3) {
            if (col >= 0 && col < 3) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
        return false;
    }
}
