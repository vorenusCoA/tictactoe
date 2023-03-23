package main;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static String[] board = new String[9];
    private static final String PLAYER_X = "Player-X";
    private static final String PLAYER_O = "Player-O";
    private static boolean isPlayerXTurn;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {

        Random random = new Random();
        isPlayerXTurn = random.nextBoolean();
        fillGameBoard();

        System.out.println();
        System.out.println("Welcome to Tic-Tac-Toe game!");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        boolean endGame = false;
        boolean isThereAWinner = false;
        int moves = 1;
        while (!endGame && moves < 10) {

            printBoard();

            System.out.print(isPlayerXTurn ? PLAYER_X : PLAYER_O);
            System.out.println(" it is your turn");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("Select a box: ");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 9) {

                        if (board[choice - 1].equals(String.valueOf(choice))) {
                            board[choice - 1] = isPlayerXTurn ? "X" : "O";
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice.");
                        }

                    } else {
                        System.out.println("Invalid choice.");
                    }

                } else {
                    scanner.next();
                    System.out.println("Invalid choice.");
                }

                System.out.println();
            }

            if (isThereAWinner()) {
                isThereAWinner = true;
                endGame = true;
            } else {
                moves++;
                isPlayerXTurn = !isPlayerXTurn;    
            }
            
        } // game while loop

        printBoard();

        if (isThereAWinner) {
            System.out.print(isPlayerXTurn ? PLAYER_X : PLAYER_O);
            System.out.println(" WINS!");
        } else {
            System.out.println("TIE!");
        }

        System.out.println();

        System.out.println("Would you like to play again?");
        System.out.println("1 - YES");
        System.out.println("2 - NO");

        boolean validChoice = false;
        int choice = 0;
        while (!validChoice) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                scanner.next();
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
            System.out.println();
        }

        if (choice == 1) {
            startGame();
        } else {
            scanner.close();
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }

    private static void fillGameBoard() {
        for (int i = 1; i < 10; i++) {
            board[i - 1] = String.valueOf(i);
        }
    }

    // The board will look like this:
    //  --- --- ---
    // | 1 | 2 | 3 |
    // | 4 | 5 | 6 |
    // | 7 | 8 | 9 |
    //  --- --- ---
    private static void printBoard() {

        System.out.println(" --- --- --- ");

        for (int i = 1; i <= board.length; i++) {

            System.out.print("| " + board[i - 1] + " ");
            if (i % 3 == 0) {
                System.out.println("|");
            }

        }

        System.out.println(" --- --- --- ");
        System.out.println();
    }

    private static boolean isThereAWinner() {

        return (board[0].equals(board[1]) && board[1].equals(board[2])
                || board[3].equals(board[4]) && board[4].equals(board[5])
                || board[6].equals(board[7]) && board[7].equals(board[8])
                || board[0].equals(board[3]) && board[3].equals(board[6])
                || board[1].equals(board[4]) && board[4].equals(board[7])
                || board[2].equals(board[5]) && board[5].equals(board[8])
                || board[0].equals(board[4]) && board[4].equals(board[8])
                || board[6].equals(board[4]) && board[4].equals(board[2]));

    }

}
