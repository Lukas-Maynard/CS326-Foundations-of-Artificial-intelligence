package search_solutions;

import core_search.BaseSearch;
import core_search.LIFOQueue;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;

public class TravelUCS extends BaseSearch<String, String> {
    public TravelUCS(String mapFile){
        super(new Travel(mapFile),new SortedQueue<String, String>(new ComparePathCost()));
    }

    public static void main(String[] args) {
        TravelUCS t = new TravelUCS("RomaniaMap.txt");
        t.search();
    }


    public static class ComparePathCost implements Comparator<Node<String, String>>{
        @Override
        public int compare(Node<String, String> o1, Node<String, String> o2) {
            if(o1.getPathCost() < o2.getPathCost()){
                return -1;
            } else if (o1.getPathCost() == o2.getPathCost()){
                return 0;
            }else {
            return 1;
            }
        }
    }
}
