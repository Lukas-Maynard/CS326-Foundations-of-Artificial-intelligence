package search_solutions;

import core_search.BaseSearch;
import core_search.FIFOQueue;
import search_problems.*;

public class TravelBFS extends BaseSearch<String, String> {
    public TravelBFS(String mapFile) {
        super(new Travel(mapFile), new FIFOQueue<>());
    }


    public static void main(String[] args) {
        TravelBFS t = new TravelBFS("RomaniaMap.txt");
        t.search();
    }
}