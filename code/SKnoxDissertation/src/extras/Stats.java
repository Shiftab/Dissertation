package extras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jade.core.AID;

/**
 * class for handeling the data for the pupils
 * 
 * @author Steven Knox
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
	 * method to return the personality traits of a pupil
	 * 
	 * @return the OCEAN
	 */
	public double[] getOCEAN() {
		return OCEAN;
	}

	/**
	 * method to set the personality traits of a pupil
	 * 
	 * @param OCEAN
	 */
	public void setOCEAN(double[] oCEAN) {
		OCEAN = oCEAN;
	}

	/**
	 * method to return the AID of the pupil who owns this stats class
	 * 
	 * @return the parent
	 */
	public AID getParent() {
		return parent;
	}

	/**
	 * method to set the owner AID
	 * 
	 * @param parent
	 */
	public void setParent(AID parent) {
		this.parent = parent;
	}

	/**
	 * method to get the number of questions that there have been in this
	 * simulation
	 * 
	 * @return the questions
	 */
	public int getQuestions() {
		return questions;
	}

	/**
	 * method to set the number of questions that have been asked in this
	 * simulation
	 * 
	 * @param questions
	 */
	public void setQuestions(int questions) {
		this.questions = questions;
	}

	/**
	 * method to return the time wasted due to introversion
	 * 
	 * @return the shyMissed
	 */
	public long getShyMissed() {
		return shyMissed;
	}

	/**
	 * method to start an introversion time
	 * 
	 * @param shyMissed
	 */
	public void setStartShy() {
		shyStart = System.currentTimeMillis();
		shyTimes.add(System.currentTimeMillis());
	}

	/**
	 * method to stop an introversion time
	 */
	public void stopShy() {
		shyMissed += System.currentTimeMillis() - shyStart;
		shyTimes.add(System.currentTimeMillis());
	}

	/**
	 * method to start a discussion time
	 */
	public void chatter() {
		chatter.add(System.currentTimeMillis());
	}

	/**
	 * method to return the times of introversion events
	 * 
	 * @return
	 */
	public List<Long> getShyTimes() {
		return shyTimes;
	}

	/**
	 * method to return the times of focus events with a particual peer
	 * 
	 * @param name
	 */
	public void focus(String name) {
		List<Long> times = focusTimes.get(name);
		times.add(System.currentTimeMillis());
		focusTimes.put(name, times);
	}

	/**
	 * method to return all focus times
	 * 
	 * @return
	 */
	public Map<String, List<Long>> getFocus() {
		return focusTimes;
	}

	/**
	 * method to return the time spent distracted
	 * 
	 * @return the distractions
	 */
	public long getDistractions() {
		return distractions;
	}

	/**
	 * method to start a distraction timer
	 * 
	 * @param distractions
	 */
	public void setStartDistractions() {
		if (distractStart != -1)
			distractions += System.currentTimeMillis() - distractStart;
		distractStart = System.currentTimeMillis();
	}

	/**
	 * method to stop a distraction timer
	 */
	public void stopDistract() {
		if (distractStart != -1)
			distractions += System.currentTimeMillis() - distractStart;
		distractStart = -1;
	}

	/**
	 * method to add a distraction time
	 */
	public void addDistract() {
		distractTimes.add(System.currentTimeMillis());
	}

	/**
	 * method to return the distraction times for each peer
	 * 
	 * @return
	 */
	public Map<String, List<Long>> getDistractedTimes() {
		return distracted;
	}

	/**
	 * method to create a distraction disccusion time with a particual peer
	 * 
	 * @param name
	 */
	public void addDistracted(String name) {
		List<Long> times = distracted.get(name);
		times.add(System.currentTimeMillis());
		distracted.put(name, times);
	}

	/**
	 * method to return the time spend distracted
	 * 
	 * @return
	 */
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

	/**
	 * incromerter for questions in the simulation
	 */
	public void incQuestions() {
		questions++;
	}

	/**
	 * incromenter for questions missed due to introversion
	 */
	public void incShyMissed() {
		shyMissed++;
	}

	/**
	 * incromenter for questions missed due to distraction
	 */
	public void incDistractions() {
		distractions++;
	}

	/**
	 * method to printout stat details to system out
	 */
	public void print() {
		System.out.println(parent.getLocalName() + ": asked:" + asked
				+ " answered:" + answered + " Shy:" + shyMissed
				+ " distractions:" + distractions);
	}

	/**
	 * method to clear the lists and values from stats
	 */
	public void clear() {
		asked = 0;
		answered = 0;
		questions = 0;
		shyMissed = 0;
		shyStart = 0;
		distractions = 0;
		distractStart = -1;
		distractTimes.clear();
		chatter.clear();
		shyTimes.clear();
		focusTimes.clear();
		distracted.clear();
	}
}
