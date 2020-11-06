package prove;

import implProblema.Sudoku;

public class TestSudoku {
	public static void main(String[] args) {
		Sudoku s=new Sudoku(5,5,1);
		s.risolvi();
		//System.out.println(s.getM(2));
	}
}
