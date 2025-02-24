package search_solutions;

import core_search.BaseSearch;
import core_search.LIFOQueue;
import search_problems.Travel;

public class TravelDFS extends BaseSearch<String, String> {
    public TravelDFS(String mapFile) {
        super(new Travel(mapFile), new LIFOQueue<>());
    }

    public static void main(String[] args) {
        TravelDFS t = new TravelDFS("RomaniaMap.txt");
        t.search();
    }
}
