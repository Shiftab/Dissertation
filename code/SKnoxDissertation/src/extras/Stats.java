package extras;

import java.io.FileWriter;
import java.io.IOException;
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
	int shyMissed;
	int distractions;

	/**
	 * constructor for initiating all of the variables
	 * 
	 * @param peer
	 * @param parent
	 */
	public Stats(AID parent, double[] ocean) {
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
	public int getShyMissed() {
		return shyMissed;
	}

	/**
	 * @param shyMissed
	 *            the shyMissed to set
	 */
	public void setShyMissed(int shyMissed) {
		this.shyMissed = shyMissed;
	}

	/**
	 * @return the distractions
	 */
	public int getDistractions() {
		return distractions;
	}

	/**
	 * @param distractions
	 *            the distractions to set
	 */
	public void setDistractions(int distractions) {
		this.distractions = distractions;
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
