//package solutions;
//
//import core_algorithms.Minimax;
//import problems.Chess;
//import com.github.bhlangonijr.chesslib.Board;
//import com.github.bhlangonijr.chesslib.move.Move;
//
//import java.util.Scanner;
//
//public class ChessMinimaxPlayer extends Minimax<Board, Move> {
//    private final Scanner scanner;
//    private Board board;
////    private Chess.Player turn;
//
////    public ChessMinimaxPlayer() {
////        super();
////        this.scanner = new Scanner(System.in);
////        this.board = new Board();
////        this.turn = Chess.Player.WHITE; // Assuming Chess.Player has WHITE and BLACK values
////
////        // Initialize the chess board with the starting position
////        game.initializeBoard(board);
////    }
//
//    public void play() {
//        while (!game.isTerminal(board)) {
//            printBoard();
//            if (turn == Chess.Player.WHITE) {
//                Move humanMove = getUserMove();
//                board.doMove(humanMove);
//                turn = Chess.Player.BLACK;
//            } else {
//                Move aiMove = minimaxSearch(board);
//                board.doMove(aiMove);
//                turn = Chess.Player.WHITE;
//            }
//        }
//        printBoard();
//        announceWinner(game.utility(board));
//    }
//
//    private Move getUserMove() {
//        System.out.println("Enter your move (e.g., e2e4):");
//        String moveStr = scanner.nextLine();
//        return new Move(moveStr, board.getSideToMove());
//    }
//
//    private void printBoard() {
//        System.out.println("Current Board:");
//        System.out.println(board.toString()); // Assuming Board class has a toString() method
//    }
//
//    private void announceWinner(int utility) {
//        System.out.println("Final Utility Value: " + utility);
//        if (utility == 1) {
//            System.out.println("Player (WHITE) wins!");
//        } else if (utility == -1) {
//            System.out.println("AI (BLACK) wins!");
//        } else {
//            System.out.println("It's a draw!");
//        }
//    }
//
//    public static void main(String[] args) {
//        ChessMinimaxPlayer player = new ChessMinimaxPlayer();
//        player.play();
//    }
//}
