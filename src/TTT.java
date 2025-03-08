import java.util.Scanner;

class TTT {
    private char[][] board;
    private final char[] players;
    private int currentPlayerIndex;
    private boolean gameOver;

    public TTT() {
        board = new char[3][3];
        players = new char[2];
        players[0] = 'X';
        players[1] = 'O';
        currentPlayerIndex = 0;
        gameOver = false;
        initializeBoard();
        playGame();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println("---------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("---------------");
        }
    }

    private void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    private boolean isMoveValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private void makeMove(int row, int col) {
        board[row][col] = players[currentPlayerIndex];
    }

    private boolean checkWin() {
        char currentPlayer = players[currentPlayerIndex];

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Input syntax must follow this format: \"1 1\" for example or \"2 3\" test.");
        System.out.println("Have fun!");

        while (!gameOver) {
            printBoard();
            char currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter the row and columns (1-3): ");
            int row = scanner.nextInt();
            row -= 1;
            int col = scanner.nextInt();
            col -= 1;

            if (isMoveValid(row, col)) {
                makeMove(row, col);

                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (checkDraw()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }

        System.out.print("Do you want to play again? (yes/no): ");
        String replayInput = scanner.next();
        if (replayInput.equalsIgnoreCase("yes") || replayInput.equalsIgnoreCase("y")) {
            gameOver = false;
            initializeBoard();
            playGame();
        } else {
            System.out.println("Thank you for playing!");
        }

        scanner.close();
    }
}
