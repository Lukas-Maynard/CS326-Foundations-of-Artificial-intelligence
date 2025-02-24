package search_solutions;

import core_search.BaseSearch;
import core_search.SortedQueue;
import search_problems.Travel;

public class TravelGFS extends BaseSearch<String, String> {
    public TravelGFS(String map, String estimates){
        super(new Travel(map, estimates), new SortedQueue<String, String>(new CompareEstimates()));
    }

    public static void main(String[] args) {
        TravelGFS agent = new TravelGFS("RomaniaMap.txt", "RomaniaMapEstimates.txt");
        agent.search();
    }



}
