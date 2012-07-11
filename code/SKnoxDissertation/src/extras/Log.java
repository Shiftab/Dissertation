package extras;

import java.util.HashMap;
import java.util.Map;

import jade.core.AID;

/**
 * old stats class used for testing
 * @author Steven Knox
 * @deprecated
 */
public class Log {
	public static Map<AID, Stats> pupilStats;
	public static int[][] sudokuProblem;
	private static Map<String, Integer> goodQuestions = new HashMap<String, Integer>();
	private static Map<String, Integer> goodAnswers = new HashMap<String, Integer>();

	public static void init(Map<AID, Stats> stats, int[][] problem) {
		pupilStats = stats;
		sudokuProblem = problem;
	}

	public static void update(AID pupil, Stats stats) {
		pupilStats.put(pupil, stats);
	}
	
	public static void incGoodQuestions(String name){
		goodQuestions.put(name, goodQuestions.get(name)+1);
	}
	
	public static void incGoodAnswers(String name){
		goodAnswers.put(name, goodAnswers.get(name)+1);
	}
}
