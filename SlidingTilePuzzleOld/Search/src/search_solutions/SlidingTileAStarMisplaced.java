package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class SlidingTileAStarMisplaced extends BaseSearch<List<Integer>, String> {

    public SlidingTileAStarMisplaced() {
        super(new SlidingTilePuzzle(), new SortedQueue<>(new CompareMisplacedTiles(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileAStarMisplaced solver = new SlidingTileAStarMisplaced();
        solver.search();
    }

    public static class CompareMisplacedTiles implements Comparator<Node<List<Integer>, String>> {

        private final SlidingTilePuzzle problem;

        public CompareMisplacedTiles(SlidingTilePuzzle problem) {
            this.problem = problem;
        }

        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            int f1 = problem.misplacedTilesHeuristic(o1.getState()) + o1.getPathCost();
            int f2 = problem.misplacedTilesHeuristic(o2.getState()) + o2.getPathCost();

            return Integer.compare(f1, f2);
        }
    }
}
