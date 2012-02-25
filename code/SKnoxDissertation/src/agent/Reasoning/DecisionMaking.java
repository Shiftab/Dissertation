package agent.Reasoning;

import java.util.Random;

/**
 * agents brain
 * 
 * @author shiftab
 * 
 */
public class DecisionMaking {

	private Random generator = new Random();

	// Multiplier grid
	private final double[] WAIT_PROB = { -0.1, -0.1, -0.5, 0.3, 0.1 };
	private final double[] DISTRACT_PROB = { 0.1, -0.5, 0.3, -0.3, 0.5 };
	private final double[] AGRESIVE_PROB = { 0, 0.3, 0.1, -0.5, 0.5 };
	private final double[] TENTITIVE_PROB = { 0, 0.3, 0.1, 0.3, -0.1 };
	private final double[] IGNORE_PROB = { 0, 0.5, -0.3, -0.1, 0.1 };
	private final double[] AGREE_PROB = { 0.1, 0.3, 0.1, 0.5, -0.1 };
	private final double[] DISAGREE_PROB = { 0.1, 0.3, 0.1, -0.5, 0.5 };
	private final double[] ARGUE_PROB = { 0, -0.1, 0.1, -0.5, 0.5 };
	private final double[] PLACATE_PROB = { 0, 0.3, 0.3, 0.5, -0.1 };
	private final double[] CHATTER_PROB = { 0.1, -0.5, 0.3, 0.3, 0.5 };

	//e-nums for decision case statement
	public final int WAIT = 1;
	public final int DISTRACT = 2;
	public final int AGRESIVE = 3;
	public final int TENTITIVE = 4;
	public final int IGNORE = 5;
	public final int AGREE = 6;
	public final int DISAGREE = 7;
	public final int ARGUE = 8;
	public final int PLACATE = 9;
	public final int CHATTER = 0;

	/**
	 * method to handle the action decisions based on the personality of the
	 * agent
	 * 
	 * @param decision
	 * @param probability
	 * @param ocean
	 * @return
	 */
	public boolean decide(int decision, double probability, double[] ocean) {

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
