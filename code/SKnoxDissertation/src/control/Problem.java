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
	
	public static void print(){
		for(int y=0;y<problem.length;y++){
			for(int x=0;x<problem.length;x++){
				System.out.print(problem[x][y]+" ");
			}
			System.out.print("\n");
		}
	}
}
