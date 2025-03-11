import java.util.*;

public class Tests {
    public static void runTests() throws Exception{
        testSquare1();
        testColumn();
        testRow();
        findEmptyCell();
        testSetAcceptableValue();
        testStack();
        testSolver();
    }
    public static void findEmptyCell() throws Exception{
        Sudoku s = new Sudoku();
        Stack<Cell> stack = new Stack<Cell>();
        Cell c = new Cell();
        s.setValue(0,0,1);
        Cell expected = new Cell(0, 1, 0);
        Cell result = Solver.findEmptyCell(s);
        Assert(result.isSameValues(expected));

        //fill entire grid except 1 cell
        s = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s.setValue(i,j,1);
            }
        }
        s.setValue(5,7,0);
        expected = new Cell(5,7, 0);
        result = Solver.findEmptyCell(s);
        Assert(result.isSameValues(expected));

        // new test - fills entire grid
        s = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s.setValue(i,j,1);
            }
        }
        expected = null;
        result = Solver.findEmptyCell(s);
        Assert(result == expected);
    }
    public static void testSetAcceptableValue() throws Exception{
        Sudoku s = new Sudoku();
        int value = 1;
        int expected = 1;
        Cell c = new Cell(0,0, 0);
        int result =  Solver.setAcceptableValue(c, s, value);
        Assert(result == expected);

        s = new Sudoku();
        expected = 3;
        s.setValue(2,0,1);
        s.setValue(0,2,2);
        c = new Cell(0,0, 0);
        result = Solver.setAcceptableValue(c, s, value);
        Assert(expected == result);

        s = new Sudoku();
        expected = 10;
        c = new Cell(0,0, 0);
        for (int i = 0; i < 9; i++) {
            s.setValue(0,i, i);
        }
        s.setValue(0,0,9);
        c = new Cell (0,1, 0);
        result =  Solver.setAcceptableValue(c, s, value);
        Assert(result == expected);
    }
    public static void testSquare1() throws Exception {
        // setup
        Sudoku s = new Sudoku();
        s.setValue(3, 3, 3);
        boolean expected = false;
        // execute
        boolean result = s.squareHasValue(4, 4, 4);
        // verify
        Assert(result == expected, "Did not find a match.");

        // another variant
        expected = false;
        result = s.squareHasValue(4, 4, 4);
        Assert(result == expected, "Should have not found a match.");

        // new config
        s = new Sudoku();
        s.setValue(1, 6, 3);
        expected = false;
        result = s.squareHasValue(1, 7, 4);
        Assert(result == expected);
    }

    private static void testColumn() throws Exception{
        // new
        Sudoku s = new Sudoku();
        s.setValue(2,5,7);
        boolean expected = false;
        boolean result = s.columnHasValue(3, 5, 4);
        Assert(result == expected);
    }
    private static void testRow() throws Exception{
        // new
        Sudoku s = new Sudoku();
        s.setValue(7,3,4);
        boolean expected = false;
        boolean result = s.rowHasValue(7,5,1);
        Assert(result == expected);
    }
    public static void testStack() throws Exception{
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(5);
        stack.add(4);
        int expected = 4;
        int result = stack.peek();
        Assert(result == expected);

        expected = 4;
        result = stack.pop();
        Assert(result == expected);

        // new
        expected = 5;
        result = stack.pop();
        Assert(result == expected);


    }
    public static void testSolver() throws Exception{
        Sudoku s = new Sudoku();

    }

    private static void Assert(boolean expression) throws Exception{
        Assert(expression, "");
    }

    private static void Assert(boolean expression, String message) throws Exception{
        if (!expression) {
            throw new Exception("Test failed. " + message);
        }
    }
}