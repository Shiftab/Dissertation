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
	long distractStart;
	List<Long> distractTimes = new ArrayList<Long>();
	List<Long> shyTimes = new ArrayList<Long>();
	List<focus> focusTimes = new ArrayList<focus>();

	/**
	 * constructor for initiating all of the variables
	 * 
	 * @param peer
	 * @param parent
	 */
	public Stats(AID parent, double[] ocean, List<String> peers) {
		for(String s: peers){
			focusTimes.add(new focus(s));
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
	public void setStartShy(){
		shyStart = System.currentTimeMillis();
		shyTimes.add(System.currentTimeMillis());
	}
	
	public void stopShy(){
		shyMissed += System.currentTimeMillis()-shyStart;
		shyTimes.add(System.currentTimeMillis());
	}
	
	public List<Long> getShyTimes(){
		return shyTimes;
	}
	
	public void startFocus(String name){
		for(focus f: focusTimes){
			if(f.equals(name)){
				f.newStart();
			}
		}
	}
	
	public void stopFocus(String name){
		for(focus f: focusTimes){
			if(f.equals(name)){
				f.newEnd();
			}
		}
	}
	
	public Map<String, List<long[]>> getFocus(){
		Map<String, List<long[]>> foc = new HashMap<String, List<long[]>>();
		for(focus f: focusTimes){
			if(!f.isEmpty()){
				List<Long> times = f.getTimes();
				List<long[]> pares = new ArrayList<long[]>();
				for(int x = 0; x<f.getNumEvents();x+=2 ){
					pares.add(new long[]{times.get(x), times.get(x+1)});
				}
				foc.put(f.name(), pares);
			}
		}
		
		return foc;
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
		distractStart = System.currentTimeMillis();
		distractTimes.add(System.currentTimeMillis());
	}
	
	public void stopDistract(){
		distractions += System.currentTimeMillis()-distractStart;
		distractTimes.add(System.currentTimeMillis());
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
	
	private class focus{
		String name;
		List<Long> times = new ArrayList<Long>();
		
		public focus(String name){
			this.name = name;
		}
		
		public void newStart(){
			times.add(System.currentTimeMillis());
		}
		public void newEnd(){
			times.add(System.currentTimeMillis());
		}
		
		public String name(){
			return name;
		}
		
		public int getNumEvents(){
			return times.size()/2;
		}
		
		public List<Long> getTimes(){
			return times;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object arg0) {
			// TODO Auto-generated method stub
			return name.equals((String)arg0);
		}
		
		public boolean isEmpty(){
			if(times.isEmpty())
				return true;
			return false;
		}
	}
}

