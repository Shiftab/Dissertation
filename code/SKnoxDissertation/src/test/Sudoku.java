package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {

	List<Zone> rowList = new ArrayList<Zone>();
	List<Zone> columList = new ArrayList<Zone>();
	List<Zone> gridList = new ArrayList<Zone>();
	List<ArrayList<Integer>> gridChecks = new ArrayList<ArrayList<Integer>>();

	public Sudoku(int[][] problem) {
		initChecks();
		long time = System.currentTimeMillis();
		populateZones(problem);
		for (Zone z : rowList)
			System.out.println(z);
				
		System.out.println("\n\n");

		solve(problem);
		for (Zone z : rowList)
			System.out.println(z);
		
		System.out.println("\n"+(System.currentTimeMillis()-time)/1000.0+" Seconds");
	}

	private int[][] solve(int[][] p) {
		populateZones(p);
		workOutGrid(p);
		workOutRows(p);
		workOutColums(p);
		return p;
	}

	private int[][] workOutGrid(int[][] problem) {
		int pos = 0;
		boolean vertical;
		for (Zone z : gridList) {
			List<ArrayList<Integer>> check = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> l : gridChecks) {
				if (l.contains(pos))
					check.add(l);
			}

			for (int search : z.getMissing()) {
				for (List<Integer> l : check) {
					int count = 0;
					vertical = false;
					for (int i : l) {
						if (i != pos) {

							if (gridList.get(i).isMissing(search))
								count++;
							else
								continue;

							if (i < pos - 2 || i > pos + 2) {
								vertical = true;
							}
						}
					}
					
					if (count==0) {
						List<Coordinate> excludePoints = new ArrayList<Coordinate>();
						for (int i : l) {
							if (i != pos) {
								excludePoints.add(gridList.get(i).findVal(
										search));
							}
						}

						Set<Integer> axisPoints;
						Set<Integer> removePoints = new HashSet<Integer>();
						if (vertical) { // look at x's
							for (Coordinate point : excludePoints)
								removePoints.add(point.getX());
							axisPoints = new HashSet<Integer>(z.getXAxis());
							axisPoints.removeAll(removePoints);
						} else { // look at y's
							for (Coordinate point : excludePoints)
								removePoints.add(point.getY());
							axisPoints = new HashSet<Integer>(z.getYAxis());
							axisPoints.removeAll(removePoints);
						}

						// check each blank along the axis for one missing val
						count = 0;
						Coordinate found = null;
						for (Coordinate c : z.getBlanks()) {
							if (vertical && axisPoints.contains(c.getX())
									&& rowList.get(c.getY()).isMissing(search)) { //SEARCH!!
								found = c;
								count++;
							} else if (!vertical
									&& axisPoints.contains(c.getY())
									&& columList.get(c.getX())
											.isMissing(search)) {
								found = c;
								count++;
							}

						}
						if(count==1){
							problem[found.getX()][found.getY()] = search;
							solve(problem);
							return problem;
						}
					}
				}
			}
			pos++;
		}

		return problem;
	}

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

	public int[][] workOutColums(int[][] problem) {
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

	private void initChecks() {
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(3, 4, 5)));
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(6, 7, 8)));
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(0, 3, 6)));
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(1, 4, 7)));
		gridChecks.add(new ArrayList<Integer>(Arrays.asList(2, 5, 8)));
	}
}
