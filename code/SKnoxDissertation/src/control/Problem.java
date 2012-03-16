package control;

import sudoku.Coordinate;

/**
 * paper version of the problem shared by all participants
 * 
 * @author shiftab
 *
 */
public final class Problem {

	private static volatile int[][] problem;

	/**
	 * method to set the paper problem up
	 * @param inputProblem
	 */
	public synchronized static void setProblem(int[][] inputProblem){
		problem = inputProblem;
	}
	
	/**
	 * method to get a copy of the problem
	 * @return
	 */
	public synchronized static int[][] getProblem(){
		return problem;
	}
	
	/**
	 * method to eddid the paper problem
	 * @param coordinate
	 * @return
	 */
	public synchronized static boolean edditProblem(Coordinate coordinate){
		if(problem[coordinate.getX()][coordinate.getY()]!=0)
			return false;
		else{
			problem[coordinate.getX()][coordinate.getY()] = coordinate.getVal();
			return true;
		}
	}
	
	/**
	 * method to print out the current problem to the out
	 */
	public static void print(){
		for(int y=0;y<problem.length;y++){
			for(int x=0;x<problem.length;x++){
				System.out.print(problem[x][y]+" ");
			}
			System.out.print("\n");
		}
	}
}
