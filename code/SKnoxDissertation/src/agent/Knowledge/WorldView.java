package agent.Knowledge;

import jade.core.AID;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import control.Problem;

import sudoku.Coordinate;

/**
 * how the agent sees the problem
 * 
 * @author shiftab
 * 
 */
public class WorldView {
	int problem[][] = new int[9][9]; // current problem to be solved
	List<AID> Agents = new ArrayList<AID>(); // list of agents within the group
	Random r = new Random();
	private boolean dyslexic = false;

	public WorldView(List<AID> l, boolean dys) {
		int[][] prob = Problem.getProblem();
		passByVALUE(prob);
		Agents = l;
		this.dyslexic = dys;
		readErr();
	}

	private void passByVALUE(int[][] prob) {
		for (int x = 0; x < prob.length; x++) {
			for (int y = 0; y < prob[0].length; y++) {
				if (dyslexic)
					if (r.nextDouble() <= 0.001) {
						problem[x][y] = swopNum(prob[x][y]);
						System.out.println("**************(" + x + "," + y
								+ ")=" + prob[x][y]);
					} else
						problem[x][y] = prob[x][y];
				else if (r.nextDouble() <= 0.00001) {
					problem[x][y] = swopNum(prob[x][y]);
					System.out.println("**************(" + x + "," + y + ")="
							+ prob[x][y]);
				} else
					problem[x][y] = prob[x][y];
			}
		}
	}

	private void readErr() {
		for (int x = 0; x < problem.length; x++) {
			for (int y = 0; y < problem[0].length; y++) {
				if (dyslexic)
					if (r.nextDouble() <= 0.001) {
						problem[x][y] = swopNum(problem[x][y]);
						System.out.println("**************(" + x + "," + y
								+ ")=" + problem[x][y]);
					} else if (r.nextDouble() <= 0.00001) {
						problem[x][y] = swopNum(problem[x][y]);
						System.out.println("**************(" + x + "," + y
								+ ")=" + problem[x][y]);
					}
			}
		}
	}

	private int writeErr(int x) {
		if (dyslexic) {
			if (r.nextDouble() <= 0.0001) {
				System.out.println("**************write err");
				return swopNum(x);
			}
		} else if (r.nextDouble() <= 0.00001) {
			System.out.println("**************write err");
			return swopNum(x);
		}

		return x;
	}

	private int swopNum(int x) {
		switch (x) {
		case 0:
			return x;
		case 1:
			if (r.nextBoolean())
				return 7;
			else
				return 2;
		case 2:
			if (r.nextBoolean())
				return 3;
			else
				return 7;
		case 3:
			if (r.nextBoolean())
				return 8;
			else
				return 6;
		case 4:
			return 7;
		case 5:
			return 6;
		case 6:
			if (r.nextBoolean())
				return 8;
			else
				return 9;
		case 7:
			if (r.nextBoolean())
				return 4;
			else
				return 2;
		case 8:
			if (r.nextBoolean())
				return 9;
			else
				return 6;
		case 9:
			if (r.nextBoolean())
				return 8;
			else
				return 6;
		default:
			System.out.println("ERRRRRRROR");
			return x;
		}
	}

	public int[][] getProblem() {
		return problem;
	}

	public void refresh() {
		int[][] prob = Problem.getProblem();
		passByVALUE(prob);

	}

	public void edditProblem(Coordinate x) {
		int ans = writeErr(x.getVal());
		problem[x.getX()][x.getY()] = ans;
		// not how it's done atm because it's used to check if somthings already
		// been writen probably should change this
		// Problem.edditProblem(new Coordinate(x.getX(), x.getY(), ans));
	}

	public List<AID> getPeers() {
		return Agents;
	}

	public boolean check(Coordinate c) {
		return problem[c.getX()][c.getY()] == c.getVal();
	}
}
