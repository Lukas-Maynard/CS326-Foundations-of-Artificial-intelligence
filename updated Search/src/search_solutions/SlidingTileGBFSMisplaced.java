package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

/**
 * Solving the Sliding Tile Puzzle using greedy best-first search
 */
public class SlidingTileGBFSMisplaced extends BaseSearch<List<Integer>, String> {

    public SlidingTileGBFSMisplaced() {
        super(new SlidingTilePuzzle(), new SortedQueue<>(new SlidingTilePuzzle.CompareMisplacedTiles(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileGBFSMisplaced solver = new SlidingTileGBFSMisplaced();
        solver.search();
    }
}