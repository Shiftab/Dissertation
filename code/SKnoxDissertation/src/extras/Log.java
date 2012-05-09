package extras;

import java.util.Map;

import jade.core.AID;

public class Log {
	public static Map<AID, Stats> pupilStats;
	public static int[][] sudokuProblem;

	public static void init(Map<AID, Stats> stats, int[][] problem) {
		pupilStats = stats;
		sudokuProblem = problem;
	}

	public static void update(AID pupil, Stats stats) {
		pupilStats.put(pupil, stats);
	}
}
