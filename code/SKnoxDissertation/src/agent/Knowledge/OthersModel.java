package agent.Knowledge;

import jade.core.AID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agent.Interaction.Pupil;

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
	Map<AID, Integer> Agents = new HashMap<AID, Integer>(); // list of agents
															// within the group

	/**
	 * constructor to set the lists and maps of peers up
	 * 
	 * @param peers
	 */
	public OthersModel(List<AID> peers) {
		this.peers = peers;
		for (AID a : peers) {
			answers.put(a, 0);
			Agents.put(a, Pupil.WORKING);
		}
	}

	/**
	 * incremented for questions asked
	 */
	public void incQuestion() {
		questionsAsked++;
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

			double bayes = (answers.get(a) / (questionsAsked * 1.0) * (1 / (1 + peers
					.size() * 1.0))) / ((1) / (1 + peers.size() * 1.0));
			System.out.println("bayes: " + bayes + "\nAsk:" + Agents.get(a)
					+ "\nQs:" + questionsAsked);
			if (bayes >= BAYES_TRHESHOLD) {
				return a;
			}
		}

		return null;
	}

	/**
	 * method to return the peers a participat has in there world view
	 * 
	 * @return
	 */
	public Map<AID, Integer> getPeers() {
		return Agents;
	}

	public AID getShyPerson() throws NullPointerException {
		for (AID a : Agents.keySet())
			if (Agents.get(a) == Pupil.SHY)
				return a;

		return null;
	}

	public AID getDistracted() throws NullPointerException {
		for (AID a : Agents.keySet())
			if (Agents.get(a) == Pupil.DISTRACTED)
				return a;

		return null;
	}

	public AID getArguing() throws NullPointerException {
		for (AID a : Agents.keySet())
			if (Agents.get(a) == Pupil.ARGUING)
				return a;

		return null;
	}

	public void setVisState(AID sender, int state) {
		Agents.put(sender, state);
	}

	public void lowerFocus(AID sender) {
		answers.put(sender, answers.get(sender) - 1);
	}

	public void incFocus(AID sender) {
		answers.put(sender, answers.get(sender) + 1);
	}

	public AID getDistractable() {
		AID shy = getShyPerson();
		AID distracted = getDistracted();
		if (shy != null)
			return shy;
		else if (distracted != null)
			return distracted;
		else {
			double max = Double.MAX_VALUE;
			AID ans = null;
			for (AID a : peers) {

				double bayes = (answers.get(a) / (questionsAsked * 1.0) * (1 / (peers
						.size() * 1.0)))
						/ ((peers.size() - 1) / (peers.size() * 1.0));

				if (bayes < max) {
					max = bayes;
					ans = a;
				}
			}
			if (ans == null) {
				ans = peers.get((int) (Math.random() * peers.size()));
			}
			return ans;
		}
	}

	public AID getPartner() {
		AID shy = getShyPerson();
		AID distracted = getDistracted();
		if (shy != null)
			return shy;
		else if (distracted != null)
			return distracted;
		else {
			double min = Double.MIN_VALUE;
			AID ans = null;
			for (AID a : peers) {

				double bayes = (answers.get(a) / (questionsAsked * 1.0) * (1 / (1 + peers
						.size() * 1.0))) / ((1) / (1 + peers.size() * 1.0));

				if (bayes > min) {
					min = bayes;
					ans = a;
				}
			}
			return ans;
		}
	}
}
