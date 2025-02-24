package search_solutions;

import core_search.Node;
import search_problems.Travel;

import java.util.Comparator;

public class CompareEstimates implements Comparator<Node<String, String>> {
    @Override
    public int compare(Node<String, String> o1, Node<String, String> o2) {
        Travel problem =new Travel("RomaniaMap.txt", "RomaniaMapEstimates.txt");
        if(problem.getEstimatedDistance(o1.getState()) < problem.getEstimatedDistance(o2.getState())){
            return -1;
        } else if (problem.getEstimatedDistance(o1.getState()) == problem.getEstimatedDistance(o2.getState())){
            return 0;
        }else {
            return 1;
        }
    }
}