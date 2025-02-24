package search_solutions;

import core_search.BaseSearch;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.List;

/**
 * Solving the Sliding Tile Puzzle problem using greedy best-first search
 */
public class SlidingTileGBFSMisplacedTiles extends BaseSearch<List<Integer>, String> {

    public SlidingTileGBFSMisplacedTiles() {
        super(new SlidingTilePuzzle(), new SortedQueue<>(new SlidingTilePuzzle.CompareMisplacedTiles(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        SlidingTileGBFSMisplacedTiles agent = new SlidingTileGBFSMisplacedTiles();
        agent.search();
    }
}
