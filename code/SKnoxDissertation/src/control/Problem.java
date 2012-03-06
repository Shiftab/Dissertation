package control;

import sudoku.Coordinate;

public final class Problem {

	private static volatile int[][] problem;

	public synchronized static void setProblem(int[][] prob){
		problem = prob;
	}
	
	public synchronized static int[][] getProblem(){
		return problem;
	}
	
	public synchronized static boolean edditProblem(Coordinate x){
		if(problem[x.getX()][x.getY()]!=0)
			return false;
		else{
			problem[x.getX()][x.getY()] = x.getVal();
			return true;
		}
	}
}
