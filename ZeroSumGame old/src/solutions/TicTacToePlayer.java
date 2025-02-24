package solutions;

import core_algorithms.Minimax;
import problems.TicTacToe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToePlayer extends Minimax<char[][], int[]> {
    private final TicTacToe game;
    private final Scanner scanner;

    public TicTacToePlayer(int size) {
        super(new TicTacToe(size, TicTacToe.Marks.O), false);
        game = new TicTacToe(size, TicTacToe.Marks.O);
        scanner = new Scanner(System.in);
    }

    public void play() {
        while (!game.isTerminal(game.getBoard())) {
            printBoard(game.getBoard());
            if (game.getTurn() == TicTacToe.Marks.X) {

                System.out.println("1"+Arrays.deepToString(game.getBoard()));

                int[] humanMove = getUserMove();
                game.execute(humanMove, game.getBoard());
            } else {

                System.out.println("2"+Arrays.deepToString(game.getBoard()));

                //TODO
                // ITS WIPING IT SOMEWHERE

//                char[][] copy = game.getBoard().clone();
//                int[] aiMove = minimaxSearch(game.getBoard());

                char[][] currentBoard = copyBoard(game.getBoard());
                int[] aiMove = minimaxSearch(currentBoard);
                System.out.println("AI Move: " + Arrays.toString(aiMove));
                System.out.println("3"+Arrays.deepToString(game.getBoard()));
                game.execute(aiMove, currentBoard);


//                game.execute(aiMove, game.getBoard());
            }

            System.out.println("4"+Arrays.deepToString(game.getBoard()));
            System.out.println(Arrays.deepToString(game.getBoard()));
        }
        printBoard(game.getBoard());
        announceWinner(game.utility(game.getBoard()));
    }

    private int[] getUserMove() {
        int[] move = new int[2];
        System.out.println("Enter row and column (0-indexed) separated by space:");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();
        return move;
    }

    private void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private void announceWinner(int utility) {
        if (utility == 1) {
            System.out.println("AI wins!");
        } else if (utility == -1) {
            System.out.println("Human wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private char[][] copyBoard(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    public static void main(String[] args) {
        TicTacToePlayer player = new TicTacToePlayer(3);
        player.play();
    }
}
