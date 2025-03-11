public class Cell {
    public int row;
    public int column;
    public int value;
    public Cell(){

    }
    public Cell(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
    public boolean isSameValues(Cell c){
        if(row == c.row && column == c.column){
            return true;
        }
        return false;
    }
}