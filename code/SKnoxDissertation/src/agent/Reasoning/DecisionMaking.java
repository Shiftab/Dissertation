package agent.Reasoning;

import java.util.Random;

/**
 * agents brain
 * 
 * @author shiftab
 * 
 */
public class DecisionMaking {

	private static Random generator = new Random();

	// Multiplier grid
	private static final double[] WAIT_PROB = { -0.1, -0.1, -0.3, 0.2, 0.1 };
	private static final double[] DISTRACT_PROB = { 0, -0.3, 0.2, -0.2, 0.3 };
	private static final double[] AGRESIVE_PROB = { 0, 0.3, 0.1, -0.5, 0.5 };
	private static final double[] TENTITIVE_PROB = { 0, 0.3, 0.1, 0.3, -0.1 };
	private static final double[] IGNORE_PROB = { 0, 0.5, -0.3, -0.1, 0.1 };
	private static final double[] AGREE_PROB = { 0.1, 0.3, 0.1, 0.5, -0.1 };
	private static final double[] DISAGREE_PROB = { 0.1, 0.3, 0.1, -0.5, 0.5 };
	private static final double[] ARGUE_PROB = { 0, -0.1, 0.1, -0.5, 0.5 };
	private static final double[] PLACATE_PROB = { 0, 0.3, 0.3, 0.5, -0.1 };
	private static final double[] CHATTER_PROB = { 0, -0.3, 0.2, 0.2, 0.3 };

	//e-nums for decision case statement
	public static final int WAIT = 1;
	public static final int DISTRACT = 2;
	public static final int AGRESIVE = 3;
	public static final int TENTITIVE = 4;
	public static final int IGNORE = 5;
	public static final int AGREE = 6;
	public static final int DISAGREE = 7;
	public static final int ARGUE = 8;
	public static final int PLACATE = 9;
	public static final int CHATTER = 0;

	/**
	 * method to handle the action decisions based on the personality of the
	 * agent
	 * 
	 * @param decision
	 * @param probability
	 * @param ocean
	 * @return
	 */
	public static boolean decide(int decision, double probability, double[] ocean) {

		double[] question = null;
		switch (decision) {
		case (WAIT):
			question = WAIT_PROB;
			break;
		case (DISTRACT):
			question = DISTRACT_PROB;
			break;
		case (AGRESIVE):
			question = AGRESIVE_PROB;
			break;
		case (TENTITIVE):
			question = TENTITIVE_PROB;
			break;
		case (IGNORE):
			question = IGNORE_PROB;
			break;
		case (AGREE):
			question = AGREE_PROB;
			break;
		case (DISAGREE):
			question = DISAGREE_PROB;
			break;
		case (ARGUE):
			question = ARGUE_PROB;
			break;
		case (PLACATE):
			question = PLACATE_PROB;
			break;
		case (CHATTER):
			question = CHATTER_PROB;
			break;
		}

		double ans = probability;
		for (int x = 0; x < ocean.length; x++)
			ans += (ocean[x] * question[x]);

		if (generator.nextDouble() < ans)
			return true;
		else
			return false;
	}

}
