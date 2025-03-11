import java.awt.event.ActionEvent;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try{
            Tests.runTests();
            new SudokuBoardGUI() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            };
        } catch(Exception e){
            System.out.println(e);
            System.out.println(Arrays.stream(e.getStackTrace()).toList().toString());
        }
    }
}