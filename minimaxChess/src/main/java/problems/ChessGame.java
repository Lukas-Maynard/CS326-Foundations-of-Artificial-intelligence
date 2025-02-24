package problems;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.List;

public interface ChessGame extends Game<Board, Move> {
    @Override
    default List<Move> actions(Board state) {
        return state.legalMoves();
    }

    @Override
    default Board execute(Move action, Board state) {
        Board newState = state.clone();
        newState.doMove(action);
        return newState;
    }

    @Override
    default Board undo(Move action, Board state) {
        state.undoMove();
        return state;
    }

    @Override
    default boolean isTerminal(Board state) {
        return state.isMated() || state.isDraw();
    }

    @Override
    default int utility(Board state) {
        // You may implement your own utility function for chess
        return 0;
    }
}
