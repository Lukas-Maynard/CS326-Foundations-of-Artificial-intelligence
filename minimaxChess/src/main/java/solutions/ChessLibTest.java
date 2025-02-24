package solutions;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ChessLibTest {
    //TODO
    // add check for AI's piece color, then implement ai (HOW)
    // create checks for stalemate and other edge cases?
    // load from puzzle situation for testing?


    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Decide users piece color
        Side userSide;
        System.out.println("Are you playing as white or black? (1=White, 2=Black)\nEnter 1 or 2:");
        int userPieceChoice = Integer.parseInt(scanner.nextLine());
        if (userPieceChoice == 1){
            userSide = Side.WHITE;
        } else if (userPieceChoice == 2){
            userSide = Side.BLACK;
        } else {
            System.out.println("Error, Invalid choice when choosing piece color.");
            return;
        }

        // Create a new chess board
        Board board = new Board();

        // Print the initial board state
        System.out.println("Initial Board:");
        System.out.println(board);


        // Start the game loop
        while (!board.isMated() || !board.isDraw()) {
            // check if ai's turn
            if (board.getSideToMove() != userSide){
                // AI MOVE
                System.out.println("AI move (not yet implemented)");
                //TODO
                // implement ai move choice, validation, and move action

                Random random = new Random();
                int randInt = random.nextInt(board.legalMoves().size());    // random int in range of legal move count
                String randMove = String.valueOf(board.legalMoves().get(randInt));      // get move with random int
                Move move = new Move(randMove, board.getSideToMove());      // pass in random move

                // make move (place holder, doesn't need validation, cant make invalid move)
                board.doMove(move);
            } else {
                // USER MOVE
                // Get user input for the move in SAN notation
                System.out.println("Enter your move in SAN notation (e.g., e2e4):");
                String sanMove = scanner.nextLine();

                // Convert SAN notation to a Move object
                Move move = new Move(sanMove, board.getSideToMove());

                // Validate the move
                List<Move> legalMoves = board.legalMoves();
                if (!legalMoves.contains(move)) {
                    System.out.println("Invalid move! Try again.");
                    continue; // Skip to the next iteration of the loop to try again
                }

                // Make the move on the board
                board.doMove(move);
            }

            // Print the board after the move
            System.out.println("Board after "+ (board.getSideToMove() != userSide ? "USER" : "AI") +" move:");  // condensed if else to display current "mover"
            System.out.println(board);
        }
        scanner.close();

        // Check if the game is in checkmate
        if (board.isMated()) {
            System.out.println("Checkmate! Game over.");
        } else if(board.isDraw()) {
            System.out.println("Game ended in a draw.");
        }


        // Undo the last move (for reference)
//        board.undoMove();
//        System.out.println("Board after undoing the last move:");
//        System.out.println(board);
    }
}

//    // minimax completed in class but adapted for chess
//    public class MoveEvaluation {
//        private Move move;
//        private int evaluationValue;
//
//        public MoveEvaluation(Move move, int evaluationValue) {
//            this.move = move;
//            this.evaluationValue = evaluationValue;
//        }
//
//        public Move getMove() {
//            return move;
//        }
//
//        public int getEvaluationValue() {
//            return evaluationValue;
//        }
//    }
//
//    public MoveEvaluation minimax(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
//        if (depth == 0 || board.isMated() || board.isDraw()) {
//            System.out.println("Input to minimax function is a special case");
//            return null; // Implement an evaluation function here if needed
//        }
//
//        List<Move> legalMoves = board.legalMoves();
//        MoveEvaluation bestMoveEvaluation = null;
//
//        if (maximizingPlayer) {
//            int maxEval = Integer.MIN_VALUE;
//            for (Move move : legalMoves) {
//                if (validateMove(board, move)) {
//                    Board copyBoard = board.clone(); // Create a copy of the board for simulation
//                    copyBoard.doMove(move); // Simulate the move
//                    int eval = evaluateMove(copyBoard, move); // Evaluate the move using your evaluation function
//                    int newEval = minimax(copyBoard, depth - 1, alpha, beta, false).getEvaluationValue();
//                    eval += newEval;
//                    if (eval > maxEval) { // Update maxEval and bestMoveEvaluation
//                        maxEval = eval;
//                        bestMoveEvaluation = new MoveEvaluation(move, maxEval);
//                    }
//                    alpha = Math.max(alpha, eval); // Update alpha
//                    if (beta <= alpha) {
//                        break; // Beta cut-off
//                    }
//                }
//            }
//            return bestMoveEvaluation;
//        } else {
//            int minEval = Integer.MAX_VALUE;
//            for (Move move : legalMoves) {
//                if (validateMove(board, move)) {
//                    Board copyBoard = board.clone(); // Create a copy of the board for simulation
//                    copyBoard.doMove(move); // Simulate the move
//                    int eval = evaluateMove(copyBoard, move); // Evaluate the move using your evaluation function
//                    int newEval = minimax(copyBoard, depth - 1, alpha, beta, true).getEvaluationValue();
//                    eval += newEval;
//                    if (eval < minEval) { // Update minEval and bestMoveEvaluation
//                        minEval = eval;
//                        bestMoveEvaluation = new MoveEvaluation(move, minEval);
//                    }
//                    beta = Math.min(beta, eval); // Update beta
//                    if (beta <= alpha) {
//                        break; // Alpha cut-off
//                    }
//                }
//            }
//            return bestMoveEvaluation;
//        }
//    }

