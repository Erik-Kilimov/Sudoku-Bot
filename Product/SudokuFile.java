import java.util.*;
import java.io.*;

public class SudokuFile {
    public static Sudoku populateSudoku(String file) throws Exception{
        Scanner console = new Scanner(new File(file));
        console.useDelimiter(",");
        Sudoku s = new Sudoku();
        int row = 0;
        int column = 0;
        while (console.hasNext()){
            String next = console.next();
            if(next.startsWith("\r\n")){
                row++;
                next = next.substring(2);
            }
            s.setValue(row, column, Integer.parseInt(next));

            column++;
            if (column == 9){
                column = 0;
            }
        }
        console.close();

        return s;
    }
}
