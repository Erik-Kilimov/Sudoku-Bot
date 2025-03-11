public class Sudoku {
    int[][] grid = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public void setValue(int row, int column, int value){
        this.grid[row][column] = value;
    }
    public int getValue(int row, int column){
        return this.grid[row][column];
    }

    public int [] getRow(int row){
        return this.grid[row];
    }
    public boolean columnHasValue(int row, int column, int value){
        for (int i = 0; i < 9; i++) {
            if(grid[i][column] == value){
                return true;
            }
        }
        return false;
    }
    public boolean squareHasValue(int row, int column, int value){
        int rowBounds = row % 3;
        int columnBounds = column % 3;
        int rowMin;
        int rowMax;
        int columnMin;
        int columnMax;
        if (rowBounds == 0) {
            rowMin = row;
            rowMax = row + 2;
        } else if (rowBounds == 1) {
            rowMin = row - 1;
            rowMax = row + 1;
        } else {
            rowMin = row - 2;
            rowMax = row;
        }

        if (columnBounds == 0) {
            columnMin = column;
            columnMax = column + 2;
        } else if (columnBounds == 1) {
            columnMin = column - 1;
            columnMax = column + 1;
        } else {
            columnMin = column - 2;
            columnMax = column;
        }
        for (int i = rowMin; i < rowMax + 1; i++) {
            for (int j = columnMin; j < columnMax + 1; j++) {
                if (grid[i][j] == value && row != i && column != j) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean rowHasValue(int row, int column, int value){
        for (int i = 0; i < 9; i++) {
            if(grid[row][i] == value){
                return true;
            }
        }
        return false;
    }
}
