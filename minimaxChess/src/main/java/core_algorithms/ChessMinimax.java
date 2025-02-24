package core_algorithms;


import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import problems.Game;

import java.util.List;

public class ChessMinimax {
    public final Game<Board, Move> game;
    private final boolean pruning;

    public record Best<A>(int value, A action) {};

    public ChessMinimax(Game<Board, Move> game, boolean pruning) {
        this.game = game;
        this.pruning = pruning;
    }

    public Move minimaxSearch(Board board) {
        if (pruning) {
            Best<Move> max = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return max.action();
        } else {
            Best<Move> max = maxValue(board);
            return max.action();
        }
    }

    public Best<Move> maxValue(Board board) {
        return maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Best<Move> maxValue(Board board, int alpha, int beta) {
        int maxValue = Integer.MIN_VALUE;
        Move maxAction = null;
        if (game.isTerminal(board)) {
            maxValue = game.utility(board);
        } else {
            List<Move> moves = board.legalMoves();
            for (Move move : moves) {
                board.doMove(move);
                Best<Move> min = minValue(board, alpha, beta);
                if (min.value() > maxValue) {
                    maxValue = min.value();
                    maxAction = move;
                }
                board.undoMove();

                // Alpha-beta pruning
                if (maxValue >= beta) {
                    break; // β cut-off
                }
                alpha = Math.max(alpha, maxValue);
            }
        }
        return new Best<>(maxValue, maxAction);
    }

    public Best<Move> minValue(Board board) {
        return minValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Best<Move> minValue(Board board, int alpha, int beta) {
        int minValue = Integer.MAX_VALUE;
        Move minAction = null;
        if (game.isTerminal(board)) {
            minValue = game.utility(board);
        } else {
            List<Move> moves = board.legalMoves();
            for (Move move : moves) {
                board.doMove(move);
                Best<Move> max = maxValue(board, alpha, beta);
                if (max.value() < minValue) {
                    minValue = max.value();
                    minAction = move;
                }
                board.undoMove();

                // Alpha-beta pruning
                if (minValue <= alpha) {
                    break; // α cut-off
                }
                beta = Math.min(beta, minValue);
            }
        }
        return new Best<>(minValue, minAction);
    }
}
