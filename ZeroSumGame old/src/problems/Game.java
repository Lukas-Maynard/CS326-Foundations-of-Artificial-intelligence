package problems;
import java.util.List;

public interface Game<State, Action> {
    List<Action> actions(State state);
    int utility(State state);
    boolean isTerminal(State state);
    State execute(Action action, State state);
    State undo(Action action, State state);
}
