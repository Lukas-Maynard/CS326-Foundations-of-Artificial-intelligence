package problems;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe implements Game<char[][], int[]>{
    private final int BOARD_SIZE;
    private char[][] board;
    private final boolean[][] marked;
    private Marks turn;

    public enum Marks{X,O}


    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Marks getTurn() {
        return turn;
    }

    public boolean[][] getMarked() {
        return marked;
    }

    // constructor
    public TicTacToe(int BOARD_SIZE, Marks turn) {
        this.BOARD_SIZE = BOARD_SIZE;
        this.turn = turn;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.marked = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int row=0; row<BOARD_SIZE; row++){
            for (int column=0; column<BOARD_SIZE; column++){
                board[row][column] = ' ';
                marked[row][column] = false;
            }
        }
    }

    public boolean isTerminal(char[][] board){
        int utility = utility(board);
        if (utility == 1 || utility == -1) {
            return true;
        }

        // check if board full
        for (int row=0; row<BOARD_SIZE; row++){
            for (int column=0; column<BOARD_SIZE; column++){
                if(!marked[row][column]){
                    return false;
                }
            }
        }
        // should not happen
        return false;
    }

    public char[][] execute(int[] position, char[][] board){
        if (turn == Marks.X){
            board[position[0]][position[1]] = Marks.X.toString().charAt(0);
        } else {
            board[position[0]][position[1]] = Marks.O.toString().charAt(0);
        }
        marked[position[0]][position[1]] = true;
        switchTurn();
        return board;
    }

    public char[][] undo(int[] position, char[][] board){
        board[position[0]][position[1]] = ' ';
        marked[position[0]][position[1]] = false;
        switchTurn();
        return board;
    }

    public void switchTurn(){
        if (turn == Marks.X){
            turn = Marks.O;
        }else{
            turn = Marks.X;
        }
    }

    public int utility(char[][] board) {
        // MOVE SET TO ZEROS IN FIRST LOOP
        int rowSum = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            rowSum = 0;
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == Marks.X.toString().charAt(0)) {
                    rowSum++;
                } else if (board[row][column] == Marks.O.toString().charAt(0)) {
                    rowSum--;
                }
            }
        }
        int colSum = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            colSum = 0;
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == Marks.X.toString().charAt(0)) {
                    colSum++;
                } else if (board[row][column] == Marks.O.toString().charAt(0)) {
                    colSum--;
                }
            }
        }

        if (colSum == BOARD_SIZE) {
            return 1;
        } else if (colSum == 0-BOARD_SIZE) {
            return -1;
        }
        //check diagonal
        int diaSum = 0;
        for (int d = 0; d < BOARD_SIZE; d++) {
            if (board[d][d] == Marks.X.toString().charAt(0)) {
                diaSum++;
            } else if (board[d][d] == Marks.O.toString().charAt(0)) {
                diaSum--;
            }
        }
        if (diaSum == BOARD_SIZE) {
            return 1;
        } else if (diaSum == 0-BOARD_SIZE) {
            return -1;
        }

        diaSum = 0;
        for (int d = 0; d < BOARD_SIZE; d++) {
            if (board[d][BOARD_SIZE - 1 - d] == Marks.X.toString().charAt(0)) {
                diaSum++;
            } else if (board[d][BOARD_SIZE - 1 - d] == Marks.O.toString().charAt(0)) {
                diaSum--;
            }

            if (diaSum == BOARD_SIZE) {
                return 1;
            } else if (diaSum == 0 - BOARD_SIZE) {
                return -1;
            } else {
                return 0;
            }
        }
        //should not happen
        return 0;
    }

    public List<int[]> actions(char[][] board) {
        List<int[]> results = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (!marked[row][column]) {
                    int[] position = new int[2];
                    position[0] = row;
                    position[1] = column;
                    results.add(position);
                }
            }
        }
        return results;
    }
}