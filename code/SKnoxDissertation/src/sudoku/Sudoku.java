package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Library class for solving sudoku
 * 
 * @author shiftab
 * 
 */
public class Sudoku {

	@SuppressWarnings("unchecked")
	private final List<ArrayList<Integer>> CHECK_LISTS = Arrays.asList(
			new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
			new ArrayList<Integer>(Arrays.asList(3, 4, 5)),
			new ArrayList<Integer>(Arrays.asList(6, 7, 8)),
			new ArrayList<Integer>(Arrays.asList(0, 3, 6)),
			new ArrayList<Integer>(Arrays.asList(1, 4, 7)),
			new ArrayList<Integer>(Arrays.asList(2, 5, 8)));
	private List<Zone> rowList = new ArrayList<Zone>();
	private List<Zone> columList = new ArrayList<Zone>();
	private List<Zone> gridList = new ArrayList<Zone>();
	private List<ArrayList<Integer>> gridChecks = new ArrayList<ArrayList<Integer>>(
			CHECK_LISTS);

	public Sudoku(int[][] problem) {
		populateZones(problem);
	}

	public void refresh(int[][] problem) {
		populateZones(problem);
	}

	public void print() {
		for (Zone z : rowList)
			System.out.println(z);
	}

	/**
	 * basic method for solving a whole sudoku problem
	 * 
	 * @param problem
	 * @return problem
	 */
	public int[][] solveSudoku(int[][] problem) {

		long time = System.currentTimeMillis();
		for (Zone z : rowList)
			System.out.println(z);

		System.out.println("\n\n");

		solve(problem);
		for (Zone z : rowList)
			System.out.println(z);

		System.out.println("\n" + (System.currentTimeMillis() - time) / 1000.0
				+ " Seconds");
		for (int y = 0; y < problem.length; y++)
			for (int x = 0; x < problem.length; x++)
				problem[x][y] = rowList.get(y).get(x);

		return problem;
	}

	/**
	 * method to see if the problem has been compleated
	 * 
	 * @return
	 */
	public boolean done() {
		for (Zone z : columList)
			if (!z.getMissing().isEmpty())
				return false;

		for (Zone z : rowList)
			if (!z.getMissing().isEmpty())
				return false;

		for (Zone z : gridList)
			if (!z.getMissing().isEmpty())
				return false;

		return true;
	}

	/**
	 * method for finding errors
	 * 
	 * @return
	 */
	public Coordinate searchErr() {
		Coordinate err = null;
		for (Zone z : columList)
			err = z.getError();

		if (err!=null)
			for (Zone z : rowList)
				err = z.getError();

		if (err!=null)
			for (Zone z : gridList)
				err = z.getError();

		return err;
	}

	/**
	 * method for checking error
	 * 
	 * @param cord
	 * @return
	 */
	public boolean checkErr(Coordinate cord) {
		boolean err = false;
		for (Zone z : columList)
			if (z.contains(cord))
				err = z.isError(cord);

		if (!err)
			for (Zone z : rowList)
				if (z.contains(cord))
					err = z.isError(cord);

		if (!err)
			for (Zone z : gridList)
				if (z.contains(cord))
					err = z.isError(cord);

		return err;
	}

	/**
	 * method for returning the next number solved in a problem
	 * 
	 * @param problem
	 * @return coordinate for solved number
	 */
	public Coordinate nextNumber() {
		Coordinate ans = null;
		int count = 0;
		for (Zone z : gridList) {
			ans = grid(z, count);
			count++;
			if (ans != null) {
				break;
			} else
				ans = null;
		}
		if (ans == null)
			for (Zone z : rowList) {
				ans = row(z);
				if (ans != null) {
					break;
				} else
					ans = null;
			}
		if (ans == null)
			for (Zone z : columList) {
				ans = colum(z);
				if (ans != null) {
					break;
				} else
					ans = null;
			}

		return ans;
	}

	/**
	 * method for checking a number
	 * 
	 */
	public boolean check(Coordinate c) {
		// find zones
		int pos = 0;
		Coordinate checker = new Coordinate(c.getX(), c.getY(), 0);
		for (Zone z : gridList) {
			if (z.contains(checker)) {
				if (checkGrid(z, pos, c))
					return true;
			}
			pos++;
		}
		if (checkRow(rowList.get(c.getY()), c))
			return true;
		if (checkColum(columList.get(c.getX()), c))
			return true;

		return false;
	}

	/**
	 * method used in an iterative implementation to solve a whole sudoku
	 * problem
	 * 
	 * @param problem
	 * @return problem
	 */
	private int[][] solve(int[][] p) {
		populateZones(p);
		workOutGrid(p);
		workOutRows(p);
		workOutColums(p);
		return p;
	}

	/**
	 * method used to solve a grid zone
	 * 
	 * @param problem
	 * @return problem
	 */
	private int[][] workOutGrid(int[][] problem) {
		int pos = 0;
		for (Zone z : gridList) { // for every grid zone
			Coordinate c = grid(z, pos);
			if (c != null) {
				problem[c.getX()][c.getY()] = c.getVal();
				solve(problem);
			}
			pos++; // increment the zones
		}

		return problem;
	}

	private boolean checkGrid(Zone z, int pos, Coordinate c) {
		Coordinate checker = new Coordinate(c.getX(), c.getY(), 0);
		if (z.isMissing(c.getVal()) && z.getBlanks().contains(checker)) {
			boolean vertical = false;
			List<ArrayList<Integer>> check = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> l : gridChecks) {
				// find what zones to check
				if (l.contains(pos))
					check.add(l);
			}

			// make sure they arn't missing it
			checks: for (List<Integer> l : check) {
				for (int i : l) {
					if (i != pos) {
						if (gridList.get(i).isMissing(c.getVal())) {
							continue checks;
						}
						if (i < pos - 2 || i > pos + 2) { // list is vertical
							vertical = true;
							// check the lines are right
							if (c.getX() == gridList.get(i).findVal(c.getVal())
									.getX()) {
								continue checks;
							}
						} else if (c.getY() == gridList.get(i)
								.findVal(c.getVal()).getY()) {
							continue checks;
						}
					}
				}
				int count = 0;
				for (Coordinate blank : z.getBlanks()) {
					if (vertical && c.getX() == blank.getX()
							&& rowList.get(blank.getY()).isMissing(c.getVal())) {
						count++;
					} else if (!vertical
							&& c.getY() == blank.getY()
							&& columList.get(blank.getX())
									.isMissing(c.getVal())) {
						count++;
					}
				}
				// check the other axis blanks can't be it
				if (count == 1)
					return true;
			}
		}
		return false;
	}

	/**
	 * method for solving a single grid zone
	 * 
	 * @param z
	 * @param pos
	 * @return
	 */
	private Coordinate grid(Zone z, int pos) {
		boolean vertical = false;
		List<ArrayList<Integer>> check = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> l : gridChecks) { // find what zones to check
			if (l.contains(pos))
				check.add(l);
		}
		for (int search : z.getMissing()) { // for every number looking for
			for (List<Integer> l : check) { // for every check list
				int count = 0;
				for (int i : l) { // for every zone in a check list
					if (i != pos) {
						if (gridList.get(i).isMissing(search))
							count++;

						if (i < pos - 2 || i > pos + 2) { // list is vertical
							vertical = true;
						} else {
							vertical = false;
						}
					}
				}
				if (count == 0) { // if no other zone is looking
					List<Coordinate> excludePoints = new ArrayList<Coordinate>();
					for (int i : l) {
						if (i != pos) {
							// find the loc of the search point in other zones
							excludePoints.add(gridList.get(i).findVal(search));
						}
					}
					Set<Integer> axisPoints;
					Set<Integer> removePoints = new HashSet<Integer>();
					if (vertical) { // look at x's
						for (Coordinate point : excludePoints)
							removePoints.add(point.getX());
						axisPoints = new HashSet<Integer>(z.getXAxis());
						axisPoints.removeAll(removePoints); // find the row to
															// look at
					} else { // look at y's
						for (Coordinate point : excludePoints)
							removePoints.add(point.getY());
						axisPoints = new HashSet<Integer>(z.getYAxis());
						axisPoints.removeAll(removePoints); // find the colum
					}
					// check each blank along the axis for one missing val
					count = 0;
					Coordinate found = null;
					for (Coordinate c : z.getBlanks()) {
						if (vertical && axisPoints.contains(c.getX())
								&& rowList.get(c.getY()).isMissing(search)) {
							found = c;
							count++;
						} else if (!vertical && axisPoints.contains(c.getY())
								&& columList.get(c.getX()).isMissing(search)) {
							found = c;
							count++;
						}
					}
					if (count == 1) { // only one possibility
						return new Coordinate(found.getX(), found.getY(),
								search);
					}
				}
			}
		}
		return null;
	}

	/**
	 * method for checking to see if a colum coordinate is correct
	 * 
	 * @param z
	 * @param c
	 * @return
	 */
	private boolean checkRow(Zone z, Coordinate c) {
		Coordinate checker = new Coordinate(c.getX(), c.getY(), 0);
		if (z.isMissing(c.getVal()) && z.getBlanks().contains(checker)
				&& columList.get(c.getX()).isMissing(c.getVal())) {
			int count = 0;
			for (Coordinate blank : z.getBlanks()) {
				if (columList.get(blank.getX()).isMissing(c.getVal())) {
					count++;
				}
			}
			if (count == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * method for solving a colum zone
	 * 
	 * @param zone
	 * @return solved coordinate
	 */
	private Coordinate row(Zone z) {
		Set<Integer> miss = z.getMissing();
		for (Integer x : miss) {
			int count = 0;
			Coordinate found = null;
			for (Coordinate c : z.getBlanks()) {
				if (columList.get(c.getX()).isMissing(x)) {
					count++;
					found = c;
				}
			}
			if (count == 1) {
				return new Coordinate(found.getX(), found.getY(), x);
			}
		}
		return null;
	}

	/**
	 * method for checking to see if a row coordinate is correct
	 * 
	 * @param z
	 * @param c
	 * @return
	 */
	private boolean checkColum(Zone z, Coordinate c) {
		Coordinate checker = new Coordinate(c.getX(), c.getY(), 0);
		if (z.isMissing(c.getVal()) && z.getBlanks().contains(checker)
				&& rowList.get(c.getY()).isMissing(c.getVal())) {
			int count = 0;
			for (Coordinate blank : z.getBlanks()) {
				if (rowList.get(blank.getY()).isMissing(c.getVal())) {
					count++;
				}
			}
			if (count == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * method for solving a row zone
	 * 
	 * @param zone
	 * @return solved coordinate
	 */
	private Coordinate colum(Zone z) {
		Set<Integer> miss = z.getMissing();
		for (Integer x : miss) {
			int count = 0;
			Coordinate found = null;
			for (Coordinate c : z.getBlanks()) {
				if (rowList.get(c.getY()).isMissing(x)) {
					count++;
					found = c;
				}
			}
			if (count == 1) {
				return new Coordinate(found.getX(), found.getY(), x);
			}
		}
		return null;
	}

	/**
	 * method for solving all of the row zones
	 * 
	 * @param problem
	 * @return problem
	 */
	private int[][] workOutRows(int[][] problem) {
		boolean changed = false;
		for (Zone z : rowList) {
			Coordinate row = row(z);
			if (row != null) {
				changed = true;
				problem[row.getX()][row.getY()] = row.getVal();
			}
		}

		if (changed)
			solve(problem);

		return problem;
	}

	/**
	 * method for solving all of the colum zones
	 * 
	 * @param problem
	 * @return problem
	 */
	private int[][] workOutColums(int[][] problem) {
		boolean changed = false;
		for (Zone z : columList) {
			Coordinate colum = colum(z);
			if (colum != null) {
				changed = true;
				problem[colum.getX()][colum.getY()] = colum.getVal();
			}
		}

		if (changed)
			solve(problem);

		return problem;
	}

	/**
	 * method for setting up all of the search zones
	 * 
	 * @param problem
	 */
	private void populateZones(int[][] problem) {
		List<Coordinate> colum = new ArrayList<Coordinate>();
		rowList.clear();
		gridList.clear();
		columList.clear();
		for (int z = 0; z < 9; z++) {
			rowList.add(new Zone());
			gridList.add(new Zone());
		}
		int y = 0;
		int x = 0;
		for (; x < problem.length; x++) {
			colum = new ArrayList<Coordinate>();
			for (y = 0; y < problem.length; y++) {
				rowList.get(y).addCoordinate(
						new Coordinate(x, y, problem[x][y]));
				if (y <= 2) {
					// grid 1/2/3
					if (x <= 2) {
						// grid1
						gridList.get(0).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else if (x <= 5) {
						// grid2
						gridList.get(1).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else {
						// grid3
						gridList.get(2).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					}
				} else if (y <= 5) {
					// grid4/5/6
					if (x <= 2) {
						// grid4
						gridList.get(3).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else if (x <= 5) {
						// grid5
						gridList.get(4).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else {
						// grid6
						gridList.get(5).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					}
				} else {
					// grid 7/8/9
					if (x <= 2) {
						// grid7
						gridList.get(6).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else if (x <= 5) {
						// grid8
						gridList.get(7).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					} else {
						// grid9
						gridList.get(8).addCoordinate(
								new Coordinate(x, y, problem[x][y]));
					}
				}

				colum.add(new Coordinate(x, y, problem[x][y]));
			}
			columList.add(new Zone(colum));
		}

		for (Zone z : rowList) {
			z.populateWatch();
		}
		for (Zone z : gridList) {
			z.populateWatch();
		}
	}
}
