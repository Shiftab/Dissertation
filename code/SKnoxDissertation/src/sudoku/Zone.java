package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class for search zones in a sudoku problem
 * 
 * @author shiftab
 * 
 */
public class Zone {

	private List<Coordinate> zone = new ArrayList<Coordinate>();
	private List<Coordinate> blanks = new ArrayList<Coordinate>();
	private Set<Integer> missing = new HashSet<Integer>();
	private List<Coordinate> wasMissing = new ArrayList<Coordinate>();
	private Set<Integer> Xaxis = new HashSet<Integer>();
	private Set<Integer> Yaxis = new HashSet<Integer>();
	private final Set<Integer> NUMS = new HashSet<Integer>(Arrays.asList(1, 2,
			3, 4, 5, 6, 7, 8, 9));

	/**
	 * constructor for a new zone
	 * 
	 * @param zone
	 */
	public Zone(List<Coordinate> zone) {
		this.zone = zone;
		populateWatch(this.zone);
	}

	/**
	 * blank constructor for when you need to create a zone Incrementally
	 */
	public Zone() {
	}

	/**
	 * method for adding a new coordinate to the zone
	 * 
	 * @param c
	 */
	public void addCoordinate(Coordinate c) {
		zone.add(c);
		populateWatch(zone);
	}

	/**
	 * method for finding errors in zones
	 * 
	 * @return
	 */
	public Coordinate getError() {
		Set<Integer> test = new HashSet<Integer>();
		int val = 0;
		for (Coordinate c : zone)
			if (c.getVal() != 0)
				if (!test.add(c.getVal())) {
					val = c.getVal();
					break;
				}

		if (val != 0) {
			for (Coordinate c : wasMissing)
				if (c.getVal() == val)
					return c;
		}

		return null;
	}

	/**
	 * method for checking a zone error
	 * 
	 * @param c
	 * @return
	 */
	public boolean isError(Coordinate c) {
		if (wasMissing.contains(c)) {
			Set<Integer> test = new HashSet<Integer>();
			for (Coordinate cord : zone)
				if (cord.getVal() != 0)
					if (!test.add(cord.getVal()))
						if (cord.getVal() == c.getVal())
							return true;

		}
		return false;
	}

	/**
	 * method for returning the missing numbers for a zone
	 * 
	 * @return
	 */
	public Set<Integer> getMissing() {
		return missing;
	}

	/**
	 * method for returning the coordinate of a value in a zone
	 * 
	 * @param val
	 * @return coordinate
	 */
	public Coordinate findVal(int val) {
		for (Coordinate c : zone)
			if (c.getVal() == val)
				return c;

		return null;
	}

	/**
	 * method for checking if a coordinatae is in a zone
	 * 
	 * @param c
	 * @return
	 */
	public boolean contains(Coordinate c) {
		for (Coordinate x : zone) {
			if (x.equals(c))
				return true;
		}
		return false;
	}

	/**
	 * method to check if a value is missing from a zone
	 * 
	 * @param m
	 * @return boolean
	 */
	public boolean isMissing(int m) {
		if (missing.contains(m))
			return true;
		else
			return false;
	}

	/**
	 * method for getting the coordinates of blank values in a zone
	 * 
	 * @return
	 */
	public List<Coordinate> getBlanks() {
		return blanks;
	}

	/**
	 * method for getting a set of x axis values the zone covers
	 * 
	 * @return
	 */
	public Set<Integer> getXAxis() {
		return Xaxis;
	}

	/**
	 * method for getting a set of y axis values the zone covers
	 * 
	 * @return
	 */
	public Set<Integer> getYAxis() {
		return Yaxis;
	}

	/**
	 * method used to setup a zone
	 */
	public void populateWatch() {
		populateWatch(zone);
	}

	/**
	 * method to check if a zone is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return zone.isEmpty();
	}

	/**
	 * method used to setup the lists/sets of missing blank and axis variables
	 * 
	 * @param zone
	 */
	private void populateWatch(List<Coordinate> zone) {
		Set<Integer> got = new HashSet<Integer>();
		blanks.clear();
		missing.clear();
		missing.addAll(NUMS);

		for (Coordinate c : zone) {
			Xaxis.add(c.getX());
			Yaxis.add(c.getY());
			if (c.getVal() != 0) {
				got.add(c.getVal());
			} else {
				blanks.add(c);
			}
		}

		this.zone = zone;
		missing.removeAll(got);
	}

	/**
	 * method used to return a particular value in a zone via it's place in the
	 * list
	 * 
	 * @param x
	 * @return
	 */
	public int get(int x) {
		return zone.get(x).getVal();
	}

	@Override
	public String toString() {
		String ans = "";
		for (Coordinate c : zone)
			ans += c.toString() + " ";

		return ans;
	}
}
