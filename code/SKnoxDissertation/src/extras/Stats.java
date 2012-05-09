package extras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jade.core.AID;

/**
 * class for handeling the data pertraning to a particular agents view of his
 * world
 * 
 * @author shiftab
 * 
 */
public class Stats {

	double[] OCEAN;
	AID parent;
	int asked;
	int answered;
	int questions;
	long shyMissed;
	long shyStart;
	long distractions;
	long distractStart = -1;
	List<Long> distractTimes = new ArrayList<Long>();
	List<Long> chatter = new ArrayList<Long>();
	List<Long> shyTimes = new ArrayList<Long>();
	Map<String, List<Long>> focusTimes = new HashMap<String, List<Long>>();
	Map<String, List<Long>> distracted = new HashMap<String, List<Long>>();

	/**
	 * constructor for initiating all of the variables
	 * 
	 * @param peer
	 * @param parent
	 */
	public Stats(AID parent, double[] ocean, List<String> peers) {
		for (String s : peers) {
			focusTimes.put(s, new ArrayList<Long>());
			distracted.put(s, new ArrayList<Long>());
		}
		this.parent = parent;
		OCEAN = ocean;
		asked = 0;
		answered = 0;
		questions = 0;
		shyMissed = 0;
		distractions = 0;
	}

	/**
	 * @return the oCEAN
	 */
	public double[] getOCEAN() {
		return OCEAN;
	}

	/**
	 * @param oCEAN
	 *            the oCEAN to set
	 */
	public void setOCEAN(double[] oCEAN) {
		OCEAN = oCEAN;
	}

	/**
	 * @return the parent
	 */
	public AID getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(AID parent) {
		this.parent = parent;
	}

	/**
	 * @return the questions
	 */
	public int getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(int questions) {
		this.questions = questions;
	}

	/**
	 * @return the shyMissed
	 */
	public long getShyMissed() {
		return shyMissed;
	}

	/**
	 * @param shyMissed
	 *            the shyMissed to set
	 */
	public void setStartShy() {
		shyStart = System.currentTimeMillis();
		shyTimes.add(System.currentTimeMillis());
	}

	public void stopShy() {
		shyMissed += System.currentTimeMillis() - shyStart;
		shyTimes.add(System.currentTimeMillis());
	}

	public void chatter() {
		chatter.add(System.currentTimeMillis());
	}

	public List<Long> getShyTimes() {
		return shyTimes;
	}

	public void focus(String name) {
		List<Long> times = focusTimes.get(name);
		times.add(System.currentTimeMillis());
		focusTimes.put(name, times);
	}

	public Map<String, List<Long>> getFocus() {
		return focusTimes;
	}

	/**
	 * @return the distractions
	 */
	public long getDistractions() {
		return distractions;
	}

	/**
	 * @param distractions
	 *            the distractions to set
	 */
	public void setStartDistractions() {
		if (distractStart != -1)
			distractions += System.currentTimeMillis() - distractStart;
		distractStart = System.currentTimeMillis();
	}

	public void stopDistract() {
		if (distractStart != -1)
			distractions += System.currentTimeMillis() - distractStart;
		distractStart = -1;
	}

	public void addDistract() {
		distractTimes.add(System.currentTimeMillis());
	}

	public Map<String, List<Long>> getDistractedTimes() {
		return distracted;
	}

	public void addDistracted(String name) {
		List<Long> times = distracted.get(name);
		times.add(System.currentTimeMillis());
		distracted.put(name, times);
	}

	public List<Long> getDistractTimes() {
		return distractTimes;
	}

	/**
	 * getter for questions this agent asked
	 * 
	 * @return
	 */
	public int getAsked() {
		return asked;
	}

	/**
	 * setter for questions this agent asked
	 * 
	 * @param asked
	 */
	public void setAsked(int asked) {
		this.asked = asked;
	}

	/**
	 * increment the questions asked by one
	 */
	public void incAsked() {
		asked++;
	}

	/**
	 * getter for questions this agent answered
	 * 
	 * @return
	 */
	public int getAnswered() {
		return answered;
	}

	/**
	 * setter for questions this agent answered
	 * 
	 * @param answered
	 */
	public void setAnswered(int answered) {
		this.answered = answered;
	}

	/**
	 * increment the questions answered by this agent
	 */
	public void incAnswered() {
		answered++;
	}

	public void incQuestions() {
		questions++;
	}

	public void incShyMissed() {
		shyMissed++;
	}

	public void incDistractions() {
		distractions++;
	}

	public void print() {
		System.out.println(parent.getLocalName() + ": asked:" + asked
				+ " answered:" + answered + " Shy:" + shyMissed
				+ " distractions:" + distractions);
	}
}
