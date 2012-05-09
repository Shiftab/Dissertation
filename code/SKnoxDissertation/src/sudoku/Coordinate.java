package sudoku;

/**
 * class for coordinates of points in a sudoku problem
 * 
 * @author shiftab
 * 
 */
public class Coordinate {
	private int xPos, yPos, value = 0;

	/**
	 * setup a coordinate
	 * 
	 * @param xPos
	 * @param yPos
	 * @param value
	 */
	public Coordinate(int xPos, int yPos, int value) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.value = value;
	}

	/**
	 * return position on the xPos axis
	 * 
	 * @return
	 */
	public int getX() {
		return xPos;
	}

	/**
	 * return position on the yPos axis
	 * 
	 * @return
	 */
	public int getY() {
		return yPos;
	}

	/**
	 * set value of the point, 0 if blank
	 * 
	 * @param value
	 */
	public void setVal(int value) {
		this.value = value;
	}

	/**
	 * return value of the point, 0 if blank
	 * 
	 * @return
	 */
	public int getVal() {
		return value;
	}

	@Override
	public String toString() {
		return "(" + String.valueOf(xPos) + "," + String.valueOf(yPos) + "):"
				+ String.valueOf(value);
	}

	@Override
	public boolean equals(Object object) {
		Coordinate test = (Coordinate) object;
		if (this.value == test.getVal() && this.xPos == test.getX()
				&& this.yPos == test.getY())
			return true;
		else
			return false;
	}
}
