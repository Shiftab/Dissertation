package agent.Knowledge;

import jade.core.AID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the agents opinon of it's peers
 * 
 * @author shiftab
 * 
 */
public class OthersModel {

	private final double BAYES_TRHESHOLD = 0.9;

	List<AID> peers;
	Map<AID, Integer> answers = new HashMap<AID, Integer>();
	int questionsAsked = 0;

	public OthersModel(List<AID> peers) {
		this.peers = peers;
		for (AID a : peers)
			answers.put(a, 0);
	}

	public void incQuestion() {
		questionsAsked++;
	}

	public void incQuestionAnswered(AID aid) {
		int x = answers.get(aid) + 1;
		answers.put(aid, x);
	}

	/**
	 * method returns the aid of a peer if focus is posible or null if it is not
	 * advised
	 * 
	 * @return
	 */
	public AID focus() {

		if (questionsAsked < 5)
			return null;
		
		for (AID a : peers) {

			double bayes = (answers.get(a) / (questionsAsked * 1.0) * (1 / (peers
					.size() * 1.0)))
					/ ((peers.size() - 1) / (peers.size() * 1.0));
			if (bayes >= BAYES_TRHESHOLD) {
				System.out.println("bayes: " + bayes);
				return a;
			}
		}

		return null;
	}

}
