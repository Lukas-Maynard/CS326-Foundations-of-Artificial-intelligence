package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

/**
 * Solving the Sliding Tile Puzzle problem using greedy best-first search
 * with the sum of distances heuristic
 */
public class SlidingTileGBFSSumOfDistances extends BaseSearch<List<Integer>, String> {

    public SlidingTileGBFSSumOfDistances() {
        super(new SlidingTilePuzzle(), new SortedQueue<>(new CompareEstimates(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileGBFSSumOfDistances agent = new SlidingTileGBFSSumOfDistances();
        agent.search();
    }

    public static class CompareEstimates implements Comparator<Node<List<Integer>, String>> {

        private final SlidingTilePuzzle problem;

        public CompareEstimates(SlidingTilePuzzle problem) {
            this.problem = problem;
        }

        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if (problem.sumOfDistancesHeuristic(o1.getState()) <
                    problem.sumOfDistancesHeuristic(o2.getState())) {
                return -1;
            } else if (problem.sumOfDistancesHeuristic(o1.getState()) ==
                    problem.sumOfDistancesHeuristic(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
