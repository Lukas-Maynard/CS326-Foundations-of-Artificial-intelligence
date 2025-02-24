package search_solutions;

import core_search.BaseSearch;
import core_search.SortedQueue;
import search_problems.Travel;

public class TravelAStar extends BaseSearch<String, String> {
    public TravelAStar(String map, String estimates) {
        super(new Travel(map, estimates), new SortedQueue<String, String>(new CompareSum()));
    }

    public static void main(String[] args) {
        TravelAStar agent = new TravelAStar("RomaniaMap.txt", "RomaniaMapEstimates.txt");
        agent.search();
    }
}

