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
        super(new SlidingTilePuzzle(), new SortedQueue<>(new SlidingTilePuzzle.CompareSumOfDistances(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileGBFSDistanceSum solver = new SlidingTileGBFSDistanceSum();
        solver.search();
    }
}