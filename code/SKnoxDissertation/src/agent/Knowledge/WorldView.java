package agent.Knowledge;

import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

import sudoku.Coordinate;

/**
 * how the agent sees the problem
 * @author shiftab
 *
 */
public class WorldView {
	int problem[][]; // current problem to be solved
	List<AID> Agents = new ArrayList<AID>(); // list of agents within the group

	public WorldView(int[][] p, List<AID> l) {
		problem = p;
		Agents = l;
	}

	public int[][] getProblem() {
		return problem;
	}

	public void edditProblem(Coordinate x) {
		problem[x.getX()][x.getY()] = x.getVal();
	}

	public void setProblem(int[][] p) {
		problem = null;
		problem = p;
	}

	public List<AID> getPeers() {
		return Agents;
	}

	public boolean check(Coordinate c) {
		return problem[c.getX()][c.getY()] == c.getVal();
	}
}
