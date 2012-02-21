package test;

import control.BasicStart;

public class test {

	/**
	 * test test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new BasicStart();
		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };

		// Sudoku.solveSudoku(problem);
		Coordinate ans = null;
		Sudoku s = new Sudoku(problem);
		do {
			ans = s.nextNumber(null);
			if (ans != null) {
				problem[ans.getX()][ans.getY()] = ans.getVal();
				System.out.println("\n X:" + ans.getX() + " Y:" + ans.getY()
						+ " val:" + ans.getVal() + "\n\n");
				s.refresh(problem);
			} else {
				s.print();
				break;
			}
		} while (ans != null);

	}

}
