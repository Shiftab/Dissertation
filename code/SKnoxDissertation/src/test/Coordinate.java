package test;

public class Coordinate {
	private int x, y, val=0;

	public Coordinate(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}
}
