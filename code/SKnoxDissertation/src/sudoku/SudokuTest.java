/**
 * 
 */
package sudoku;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author shiftab
 * 
 */
public class SudokuTest {

	int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
			{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
			{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
			{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };

	int[][] answer = { { 6, 2, 3, 1, 8, 7, 9, 4, 5 },
			{ 8, 9, 5, 6, 4, 3, 7, 2, 1 }, { 4, 1, 7, 5, 2, 9, 8, 6, 3 },
			{ 3, 6, 9, 4, 7, 1, 5, 8, 2 }, { 2, 7, 8, 3, 9, 5, 6, 1, 4 },
			{ 5, 4, 1, 2, 6, 8, 3, 7, 9 }, { 9, 5, 6, 7, 1, 4, 2, 3, 8 },
			{ 1, 8, 2, 9, 3, 6, 4, 5, 7 }, { 7, 3, 4, 8, 5, 2, 1, 9, 6 } };

	Sudoku sudoku;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sudoku = new Sudoku(problem);
	}

	/**
	 * Test method for {@link sudoku.Sudoku#Sudoku(int[][])}.
	 */
	@Test
	public void testSudoku() {
		assertNotNull("Constructor", sudoku = new Sudoku(problem));
		assertArrayEquals("problem set", problem, sudoku.getProblem());
	}

	/**
	 * Test method for {@link sudoku.Sudoku#nextNumber()}.
	 */
	@Test
	public void testNextNumber() {
		List<Coordinate> answerList = new Sudoku(problem).rightAnswers(problem);
		assertTrue("answer is in answer List",
				answerList.contains(sudoku.nextNumber()));
	}

	/**
	 * Test method for {@link sudoku.Sudoku#refresh(int[][])}.
	 */
	@Test
	public void testRefresh() {
		int[][] problem2 = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 3, 0, 1, 2, 0, 0, 0 } };

		assertArrayEquals("start test", problem, sudoku.getProblem());
		sudoku.refresh(problem2);
		assertArrayEquals("after refresh", problem2, sudoku.getProblem());
	}

	/**
	 * Test method for {@link sudoku.Sudoku#solveSudoku(int[][])}.
	 */
	@Test
	public void testSolveSudoku() {
		assertArrayEquals("start test", problem, sudoku.getProblem());
		assertArrayEquals("solve test", answer, sudoku.solveSudoku(problem));
	}

	/**
	 * Test method for {@link sudoku.Sudoku#done()}.
	 */
	@Test
	public void testDone() {
		int[][] ans;
		assertArrayEquals("start test", problem, sudoku.getProblem());
		assertFalse("non-compleat check", sudoku.done());
		assertArrayEquals("solve test", answer,
				ans = sudoku.solveSudoku(problem));
		assertTrue("answer check", sudoku.done());
	}

	/**
	 * Test method for {@link sudoku.Sudoku#searchErr()}.
	 */
	@Test
	public void testSearchErr() {
		int[][] problem2 = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 5, 0, 0, 0, 2, 0, 0, 0 } };
		assertArrayEquals("start test", problem, sudoku.getProblem());
		assertNull("no err", sudoku.searchErr());
		sudoku.refresh(problem2);
		assertArrayEquals("new array", problem2, sudoku.getProblem());
		assertTrue("error", new Coordinate(8, 1, 5).equals(sudoku.searchErr()));

	}

	/**
	 * Test method for {@link sudoku.Sudoku#checkErr(sudoku.Coordinate)}.
	 */
	@Test
	public void testCheckErr() {
		int[][] problem2 = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 1, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 5, 0, 0, 0, 2, 0, 0, 0 } };
		assertArrayEquals("start test", problem, sudoku.getProblem());
		assertNull("no err", sudoku.searchErr());
		sudoku.refresh(problem2);
		assertArrayEquals("new array", problem2, sudoku.getProblem());
		assertTrue("error", new Coordinate(8, 1, 5).equals(sudoku.searchErr()));
		assertTrue("error is true", sudoku.checkErr(new Coordinate(8, 1, 5)));
		assertTrue("error is already thing",
				!sudoku.checkErr(new Coordinate(0, 0, 6)));
		assertTrue("error is a correct thing",
				!sudoku.checkErr(new Coordinate(3, 5, 1)));
	}

	/**
	 * Test method for {@link sudoku.Sudoku#check(sudoku.Coordinate)}.
	 */
	@Test
	public void testCheck() {
		assertArrayEquals("start test", problem, sudoku.getProblem());
		assertTrue("first num correct", sudoku.check(new Coordinate(3, 5, 1)));
		assertTrue("already exist wrong",
				!sudoku.check(new Coordinate(0, 0, 6)));
		assertTrue("wrong num wrong", !sudoku.check(new Coordinate(8, 1, 5)));
	}

}
