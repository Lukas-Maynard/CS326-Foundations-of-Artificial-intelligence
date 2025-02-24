package csp_solutions;

import csp_problems.CSPProblem;
import csp_problems.Sudoku;
import core_algorithms.BacktrackingSearch;

import java.util.Iterator;


public class BacktrackingSearch_Sudoku extends BacktrackingSearch<String,Integer>{

    public BacktrackingSearch_Sudoku(Sudoku problem){
        super(problem);
    }

    /**
     * To revise an arc: for each value in tail's domain, there must be a value in head's domain that's different
     *                   if not, delete the value from the tail's domain
     * @param head head of the arc to be revised
     * @param tail tail of the arc to be revised
     * @return true if the tail has been revised (lost some values), false otherwise
     */
    @Override
    public boolean revise(String head, String tail) {
        boolean revised = false;
        CSPProblem.Variable<String, Integer> headVar = getAllVariables().get(head);
        CSPProblem.Variable<String, Integer> tailVar = getAllVariables().get(tail);

        Iterator<Integer> tailDomainIterator = tailVar.domain().iterator();
        while (tailDomainIterator.hasNext()) {
            Integer valueTail = tailDomainIterator.next();
            boolean hasSupport = false;
            for (Integer valueHead : headVar.domain()) {
                if (valueHead != valueTail && isDifferentSquare(head, valueHead, tail, valueTail)) {
                    hasSupport = true;
                    break;
                }
            }
            if (!hasSupport) {
                tailDomainIterator.remove();
                revised = true;
            }
        }
        return revised;
    }

    private boolean isDifferentSquare(String head, Integer valueHead, String tail, Integer valueTail) {
        // row and column from  square names
        int rowHead = Integer.parseInt(head.substring(0, 1));
        int colHead = Integer.parseInt(head.substring(1, 2));
        int rowTail = Integer.parseInt(tail.substring(0, 1));
        int colTail = Integer.parseInt(tail.substring(1, 2));

        // if the values are not equal and are in different rows, columns, or grids
        return !valueHead.equals(valueTail) && (rowHead != rowTail || colHead != colTail || getSubgridIndex(rowHead, colHead) != getSubgridIndex(rowTail, colTail));
    }

    // get the subgrid index based on row and column
    private int getSubgridIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }

    /**
     * Implementing the minimum-remaining-values(MRV) ordering heuristic.
     * @return the variable with the smallest domain among all the unassigned variables;
     *         null if all variables have been assigned
     */
    public String selectUnassigned() {
        String selectedVar = null;
        int minDomainSize = Integer.MAX_VALUE;

        for (String var : getAllVariables().keySet()) {
            if (!assigned(var)) { //  if unassigned
                int domainSize = getAllVariables().get(var).domain().size();
                if (domainSize < minDomainSize) {
                    minDomainSize = domainSize;
                    selectedVar = var;
                }
            }
        }
        return selectedVar;
    }


    /**
     * @param args (no command-line argument is needed to run this program)
     */
    public static void main(String[] args) {
        String filename = "src\\SudokuTestCases\\TestCase1.txt";
        Sudoku problem = new Sudoku(filename);
        BacktrackingSearch_Sudoku agent = new BacktrackingSearch_Sudoku(problem);
        System.out.println("loading puzzle from " + filename + "...");
        problem.printPuzzle(problem.getAllVariables());
        if(agent.initAC3() && agent.search()){
            System.out.println("Solution found:");
            problem.printPuzzle(agent.getAllVariables());
        }else{
            System.out.println("Unable to find a solution.");
        }
    }
}
