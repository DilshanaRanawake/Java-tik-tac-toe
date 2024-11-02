import java.util.Scanner;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        char player = 'X'; // Player is 'X'
        char computer = 'O'; // Computer is 'O'
        String player2;
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (!gameOver) {
            printBoard(board);
            if (player == 'X') {
                // Player's turn
                System.out.print("Enter row and column (0, 1, or 2): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                System.out.println();

                if (board[row][col] == ' ') {
                    board[row][col] = player;
                    gameOver = hasWon(board, player);
                    if (gameOver) {
                        printBoard(board);
                        if(player == 'X') {
                            player2 = "You have";
                        }else{
                            player2 = player + " has";
                        }
                        System.out.println(player2 + " won!");
                    } else {
                        player = computer; // Switch to computer's turn
                    }
                } else {
                    System.out.println("Invalid move. Try again!");
                }
            } else {
                // Computer's turn
                int row, col;
                do {
                    row = random.nextInt(3); // Random row
                    col = random.nextInt(3); // Random column
                } while (board[row][col] != ' '); // Keep trying until an empty cell is found

                board[row][col] = computer;
                System.out.println("Computer placed " + computer + " at (" + row + ", " + col + ")");
                gameOver = hasWon(board, computer);
                if (gameOver) {
                    printBoard(board);
                    System.out.println("Computer has won!");
                } else {
                    player = 'X'; // Switch back to player's turn
                }
            }
        }

        printBoard(board);
        scanner.close();
    }

    public static boolean hasWon(char[][] board, char player) {
        // Check rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
                if (col < board[row].length - 1) {
                    System.out.print(" | "); 
                }
            }
            System.out.println();
            if (row < board.length - 1) {
                System.out.println("---------"); 
            }
        }
    }
}