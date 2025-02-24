package optimization_solutions;

import core_algorithms.CSPProblem;
import optimization_problems.Sudoku;
import optimization_solutions.BacktrackingSearch;
import  core_algorithms.Problem;

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

    // Method to check if the values assigned to two squares are valid
    private boolean isDifferentSquare(String head, Integer valueHead, String tail, Integer valueTail) {
        // Implement your logic here to check if the assignment is valid according to Sudoku rules
    }



    /**
     * Implementing the minimum-remaining-values(MRV) ordering heuristic.
     * @return the variable with the smallest domain among all the unassigned variables;
     *         null if all variables have been assigned
     */
    public String selectUnassigned(){
        //TODO
    }

    /**
     * @param args (no command-line argument is needed to run this program)
     */
    public static void main(String[] args) {
        String filename = "./SudokuTestCases/TestCase9.txt";
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
