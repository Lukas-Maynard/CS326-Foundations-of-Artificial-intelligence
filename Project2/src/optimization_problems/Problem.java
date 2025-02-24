package optimization_problems;

public interface Problem<S> {
    public S generateNewState(S currentState);

    public double cost(S state);

    public S getInitState();
}
