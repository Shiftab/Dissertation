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
	 * @param coordinate
	 */
	public void addCoordinate(Coordinate coordinate) {
		zone.add(coordinate);
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
	 * method to eddit a zone to add a new coordinate
	 * 
	 * @param coordinate
	 */
	public void eddit(Coordinate coordinate) {
		Coordinate blank = new Coordinate(coordinate.getX(), coordinate.getY(),
				0);
		for(Coordinate c: zone)
			if(c.equals(blank)){
				zone.set(zone.indexOf(c), coordinate);
				break;
			}
		zone.remove(blank);
		wasMissing.add(coordinate);
		populateWatch(zone);
	}

	/**
	 * method for checking a zone error
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean isError(Coordinate coordinate) {
		if (wasMissing.contains(coordinate)) {
			Set<Integer> test = new HashSet<Integer>();
			for (Coordinate cord : zone)
				if (cord.getVal() != 0)
					if (!test.add(cord.getVal()))
						if (cord.getVal() == coordinate.getVal())
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
	 * @param value
	 * @return coordinate
	 */
	public Coordinate findVal(int value) {
		for (Coordinate c : zone)
			if (c.getVal() == value)
				return c;

		return null;
	}

	/**
	 * method for checking if a coordinatae is in a zone
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean contains(Coordinate coordinate) {
		for (Coordinate x : zone) {
			if (x.equals(coordinate))
				return true;
		}
		return false;
	}

	/**
	 * method to check if a value is missing from a zone
	 * 
	 * @param missingValue
	 * @return boolean
	 */
	public boolean isMissing(int missingValue) {
		if (missing.contains(missingValue))
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
		Xaxis.clear();
		Yaxis.clear();
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
	 * @param position
	 * @return
	 */
	public int get(int position) {
		return zone.get(position).getVal();
	}

	@Override
	public String toString() {
		String ans = "";
		for (Coordinate c : zone)
			ans += c.toString() + " ";

		return ans;
	}
}
