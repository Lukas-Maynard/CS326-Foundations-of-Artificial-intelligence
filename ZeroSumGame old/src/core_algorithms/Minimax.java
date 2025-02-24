package core_algorithms;
import problems.Game;

// TODO
// ADD ALPHA BETA PRUNING

public class Minimax<State, Action> {
    private final Game<State, Action> game;
    private final boolean pruning;

    public record Best<Action>(int value, Action action){};

    public Minimax(Game<State, Action> game, boolean pruning) {
        this.game = game;
        this.pruning = pruning;
    }

    public Action minimaxSearch(State state){
        Best<Action> max = maxValue(state);
        return max.action();
    }

    public Best<Action> maxValue(State state){
        int maxValue = Integer.MIN_VALUE;
        Action maxAction = null;
        if (game.isTerminal(state)){
            maxValue = game.utility(state);
        }else{
            for (Action action : game.actions(state)){
                State newState = game.execute(action, state);
                Best<Action> min = minValue(newState);
                if (min.value() > maxValue){
                    maxValue = min.value();
                    maxAction = action;
                }
                game.undo(action, newState);
            }
        }
        return new Best<>(maxValue, maxAction);
    }

    public Best<Action> minValue(State state){
        int minValue = Integer.MAX_VALUE;
        Action minAction = null;
        if (game.isTerminal(state)){
            minValue = game.utility(state);
        }else{
            for (Action action : game.actions(state)) {
                State newState = game.execute(action, state);
                Best<Action> max = maxValue(newState);
                if (max.value() < minValue) {
                    minValue = max.value();
                    minAction = action;
                }
                game.undo(action, newState);
            }
        }
        return new Best<>(minValue, minAction);
    }
}