package test;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 }, 
							{ 0, 9, 5, 6, 0, 3, 7, 0, 0 },
							{ 4, 1, 0, 0, 0, 0, 0, 6, 3 },
							{ 0, 0, 9, 4, 7, 0, 5, 8, 0 },
							{ 0, 7, 0, 0, 9, 0, 0, 1, 0 },
							{ 0, 4, 1, 0, 6, 8, 3, 0, 0 },
							{ 9, 5, 0, 0, 0, 0, 0, 3, 8 },
							{ 0, 0, 2, 9, 0, 6, 4, 5, 0 },
							{ 0, 0, 0, 0, 0, 2, 0, 0, 6 } };
	
		Sudoku s = new Sudoku(problem);
	}

}
