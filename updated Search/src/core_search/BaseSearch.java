package core_search;

import search_problems.SlidingTilePuzzle;

import java.util.*;

/**
 *  WARNING:
 *     1. This class will NOT work if S (date type of state) is an array type (e.g., int[], String[], etc.)
 *     2. The data type of state must provide the correct equals method
 *
 *  An implementation of the general search algorithm
 *  (AIMA 4e, page 73, Figure 3.7 with some modifications)
 *
 *  To implement a specific search algorithm (such as BFS, DFS, etc.),
 *  extend this class and provide an implementation of the PriorityQueue
 *
 *  S represents the date type of state
 *  A represents the date type of action
 *  */

public class BaseSearch<S,A> {
    private final Problem<S,A> p;
    private final MyPriorityQueue<S,A> frontier;
    private final Set<S> expanded = new HashSet<>();

    public BaseSearch(SlidingTilePuzzle p, MyPriorityQueue<String, String> frontier) {
        this.p = p;
        this.frontier = frontier;
    }

    public boolean search(){
        frontier.add(new Node<>(p.initialState(), null, 0, null));

        while(!frontier.isEmpty()){
            Node<S,A> node = frontier.pop();
            if (!expanded.contains(node.getState())){
               // System.out.println(node.getState()+" expanded");
                if(node.getState().equals(p.goalState())){
                    int pathCost = node.getPathCost();
                    Stack<S> path = new Stack<>();
                    do {
                        path.add(node.getState());
                        node = node.getParent();
                    } while(node!=null);

                    System.out.println("Path (from initial state to goal state): ");
                    while(!path.isEmpty()){
                        p.printState(path.pop());
                        if(!path.isEmpty()) {
                            System.out.println("↓");
                        }
                    }
                    System.out.println("\nPath cost: "+pathCost);
                    return true;
                }
                expanded.add(node.getState());

                for(Tuple<S,A> t : p.execution(node.getState())){
                    Node<S,A> child = new Node<>(t.getState(), t.getAction(),t.getCost()+node.getPathCost(), node);
                    frontier.add(child);
                }
            }
        }
        return false;
    }
}
