package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

/**
 * Solving the Sliding Tile Puzzle using greedy best-first search with the "sum of distances" heuristic
 */
public class SlidingTileGBFSDistanceSum extends BaseSearch<List<Integer>, String> {

    public SlidingTileGBFSDistanceSum() {
        super(new SlidingTilePuzzle(), new SortedQueue<>(new CompareSumOfDistances(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileGBFSDistanceSum solver = new SlidingTileGBFSDistanceSum();
        solver.search();
    }

    public static class CompareSumOfDistances implements Comparator<Node<List<Integer>, String>> {

        private final SlidingTilePuzzle problem;

        public CompareSumOfDistances(SlidingTilePuzzle problem) {
            System.out.println("COMPARESUM OF DISTANCES");      // DEBUG
            this.problem = problem;
        }

        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            System.out.println("COMPARE FUNC");     // DEBUG
            if (sumOfDistancesHeuristic(o1.getState()) < sumOfDistancesHeuristic(o2.getState())) {
                return -1;
            } else if (sumOfDistancesHeuristic(o1.getState()) == sumOfDistancesHeuristic(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }

        private int sumOfDistancesHeuristic(List<Integer> state) {
            return problem.sumOfDistances(state);
        }

        private int sumOfDistances(List<Integer> state) {
            System.out.println("sum of distances");
            int sum = 0;
            int size = (int) Math.sqrt(state.size());

            for (int i = 0; i < state.size(); i++) {
                int value = state.get(i);
                if (value != 0) {
                    int goalIndex = state.indexOf(value);
                    int goalRow = goalIndex / size;
                    int goalCol = goalIndex % size;
                    int currentRow = i / size;
                    int currentCol = i % size;

                    sum += Math.abs(goalRow - currentRow) + Math.abs(goalCol - currentCol);
                }
            }
            return sum;
        }
    }
}