package agent.Knowledge;

import jade.core.AID;

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
	
	//taken out for the moment, as is not mision critical, running out of time and causing to meny errors
	private final double DYSLEX_READ = 0;
	private final double NORM_READ = 0;
	private final double DYSLEX_WRITE = 0;
	private final double NORM_WRITE = 0;
	
	int problem[][] = new int[9][9]; // current problem to be solved
	Random r = new Random();
	private boolean dyslexic = false;

	/**
	 * constructor for the world view of an agent
	 * 
	 * @param agentList
	 * @param isDyslexic
	 */
	public WorldView(List<AID> agentList, boolean isDyslexic) {
		int[][] prob = Problem.getProblem();
		passByVALUE(prob);
			
		this.dyslexic = isDyslexic;
	}

	/**
	 * method to copy the value of the volitile paper problem
	 * to a local copy to allow for and create errors based
	 * on agent ability
	 * ability
	 * @param problem
	 */
	private void passByVALUE(int[][] problem) {
		for (int x = 0; x < problem.length; x++) {
			for (int y = 0; y < problem[0].length; y++) {
				if (dyslexic)
					if (r.nextDouble() <= DYSLEX_READ) {
						this.problem[x][y] = swopNum(problem[x][y]);
						System.out.println("err:"+x+","+y+":"+problem[x][y]);
						//TODO: implement actual focus system
					} else
						this.problem[x][y] = problem[x][y];
				else if (r.nextDouble() <= NORM_READ) {
					this.problem[x][y] = swopNum(problem[x][y]);
				} else
					this.problem[x][y] = problem[x][y];
			}
		}
	}

	/**
	 * method to add writen errors
	 * @param number
	 * @return
	 */
	private int writeErr(int number) {
		if (dyslexic) {
			if (r.nextDouble() <= DYSLEX_WRITE) {
				System.out.println("**************write err");
				return swopNum(number);
			}
		} else if (r.nextDouble() <= NORM_WRITE) {
			System.out.println("**************write err");
			return swopNum(number);
		}

		return number;
	}

	/**
	 * method to swop numbers to common number errors in 
	 * dyslexics
	 * @param number
	 * @return
	 */
	private int swopNum(int number) {
		switch (number) {
		case 0:
			return number;
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
			return number;
		}
	}

	/**
	 * method to return the current view of the problem
	 * @return
	 */
	public int[][] getProblem() {
		return problem;
	}

	/**
	 * method to refresh the view of the problem by looking at
	 * the actual paper version
	 */
	public void refresh() {
		int[][] prob = Problem.getProblem();
		passByVALUE(prob);

	}

	/**
	 * method to eddit the problem in the persons individual interpretation
	 * @param coordinate
	 */
	public void edditProblem(Coordinate coordinate) {
		int ans = writeErr(coordinate.getVal());
		problem[coordinate.getX()][coordinate.getY()] = ans;
		//TODO: fix the writen errors
		// not how it's done atm because it's used to check if somthings already
		// been writen probably should change this
		// Problem.edditProblem(new Coordinate(x.getX(), x.getY(), ans));
	}

	/**
	 * method to check if a coordinate is in the view of the
	 * problem
	 * @param coordinate
	 * @return
	 */
	public boolean check(Coordinate coordinate) {
		return problem[coordinate.getX()][coordinate.getY()] == coordinate.getVal();
	}


}
