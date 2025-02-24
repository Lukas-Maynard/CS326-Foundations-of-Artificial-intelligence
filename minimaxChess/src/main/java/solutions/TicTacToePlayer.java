//package solutions;
//import core_algorithms.Minimax;
//import problems.TicTacToe;
//
//import java.util.Scanner;
//
//public class TicTacToePlayer extends Minimax<char[][], int[]> {
//    private final Scanner scanner;
//    private char[][] board;
//    private TicTacToe.Marks turn;
//    private final int boardSize;
//
//    public TicTacToePlayer(int size) {
//        super(new TicTacToe(size, TicTacToe.Marks.X), true);
//        this.scanner = new Scanner(System.in);
//        this.boardSize = size;
//        this.board = new char[size][size];
//        this.turn = TicTacToe.Marks.X;
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                this.board[i][j] = ' ';
//            }
//        }
//    }
//
//    public void play() {
//        while (!game.isTerminal(board)) {
//            printBoard();
//            if (turn == TicTacToe.Marks.X) {
//                int[] humanMove = getUserMove();
//                board = game.execute(humanMove, board);
//                turn = turn == TicTacToe.Marks.X ? TicTacToe.Marks.O : TicTacToe.Marks.X;
//            } else {
//                int[] aiMove = minimaxSearch(board);
//                board = game.execute(aiMove, board);
//                turn = turn == TicTacToe.Marks.X ? TicTacToe.Marks.O : TicTacToe.Marks.X;
//            }
//        }
//        printBoard();
//        announceWinner(game.utility(board));
//    }
//
//
//
//    private int[] getUserMove() {
//        int[] move = new int[2];
//        System.out.println("Enter row and column (0-indexed) separated by space:");
//        move[0] = scanner.nextInt();
//        move[1] = scanner.nextInt();
//        return move;
//    }
//
//    private boolean isValidMove(int[] move) {
//        boolean isWithinBounds = move[0] >= 0 && move[0] < boardSize && move[1] >= 0 && move[1] < boardSize;
//
//        boolean isPositionUnoccupied = isWithinBounds && board[move[0]][move[1]] == ' ';
//
//        return isPositionUnoccupied;
//    }
//
//
//
//
//    private void printBoard() {
//        System.out.println("Current Board:");
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.print(board[i][j]);
//                if (j < board[i].length - 1) {
//                    System.out.print(" | ");
//                }
//            }
//            System.out.println();
//            if (i < board.length - 1) {
//                System.out.println("---+---+---");
//            }
//        }
//    }
//
//
//    private void announceWinner(int utility) {
//        System.out.println("Final Utility Value: " + utility);
//        if (utility == 1) {
//            System.out.println("Player (X) wins!");
//        } else if (utility == -1) {
//            System.out.println("AI (O) wins!");
//        } else {
//            System.out.println("It's a draw!");
//        }
//    }
//
//    public static void main(String[] args) {
//        TicTacToePlayer player = new TicTacToePlayer(3);
//        player.play();
//    }
//}
