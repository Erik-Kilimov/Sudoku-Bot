import java.util.*;

public class Solver {
    public static boolean solve(Sudoku s){
        Stack<Cell> stack = new Stack<Cell>();
        Cell c = new Cell();
        // find empty cell
        c = findEmptyCell(s);
        while(c != null) {
            //set acceptable value for the cell, can set 10 if no acceptable values found
            int value = 1;
            setAcceptableValue(c,s, value);
            //add cell to stack
            stack.add(c);
            while(c.value >= 10){
                s.setValue(c.row,c.column, 0);
                stack.pop();
                c = stack.pop();
                value = c.value++;
                setAcceptableValue(c, s, value);
                stack.add(c);
            }
            c = findEmptyCell(s);
        }
        return true;
    }

    // find an empty cell and then sets
    public static Cell findEmptyCell(Sudoku s){
        Cell c = new Cell();
        int [] rowArray;
        for (int i = 0; i < 9; i++) {
            rowArray = s.getRow(i);
            for (int j = 0; j < rowArray.length; j++) {
                if (rowArray[j] == 0) {
                    c.row = i;
                    c.column = j;
                    return c;
                }
            }
        }
        return null;
    }

    // finds a value that complies with Sudoku rules and sets it in the cell
    public static int setAcceptableValue(Cell c, Sudoku s, int value){
        for (int i = 1; i <= 9; i++) {
            if(s.columnHasValue(c.row, c.column, value) ||
                    s.squareHasValue(c.row, c.column, value) ||
                    s.rowHasValue(c.row, c.column, value)){
                value++;
            }
        }
        s.setValue(c.row, c.column, value);
        c.value = value;
        return value;
    }


}
