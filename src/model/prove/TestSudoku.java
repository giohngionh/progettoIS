package model.prove;
import model.implProblema.Sudoku;

public class TestSudoku {
    public static void main(String[] args) {
        Sudoku s=new Sudoku(5);

        s.risolvi(false);

        //System.out.println(s.getM(2));
    }
}
