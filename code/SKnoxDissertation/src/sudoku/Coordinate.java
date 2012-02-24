package sudoku;

/**
 * class for coordinates of points in a sudoku problem
 * 
 * @author shiftab
 * 
 */
public class Coordinate {
	private int x, y, val = 0;

	/**
	 * setup a coordinate
	 * 
	 * @param x
	 * @param y
	 * @param val
	 */
	public Coordinate(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}

	/**
	 * return position on the x axis
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * return position on the y axis
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * set value of the point, 0 if blank
	 * 
	 * @param val
	 */
	public void setVal(int val) {
		this.val = val;
	}

	/**
	 * return value of the point, 0 if blank
	 * 
	 * @return
	 */
	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	@Override
	public boolean equals(Object obj) {
		Coordinate test = (Coordinate) obj;
		if (this.val == test.getVal() && this.x == test.getX()
				&& this.y == test.getY())
			return true;
		else
			return false;
	}
}
