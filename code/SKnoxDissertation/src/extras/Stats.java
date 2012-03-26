package extras;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import jade.core.AID;

/**
 * class for handeling the data pertraning to a particular agents
 * view of his world
 * @author shiftab
 *
 */
public class Stats {

	FileWriter fstream;
	BufferedWriter out;
	String name, output;
	AID AID, parent;
	int asked;
	int answered;
	int myAnswer;
	long lastCom;
	long totalWait;
	int waitCount;

	/**
	 * constructor for initiating all of the variables
	 * 
	 * @param peer
	 * @param parent
	 */
	public Stats(AID peer, AID parent) {
		this.parent=parent;
		setAID(peer);
		setName(peer.getLocalName());
		asked = 0;
		answered = 0;
		myAnswer = 0;
		lastCom = 0;
		totalWait = 0;
		waitCount = 0;
	}

	/**
	 * getter for subject agents name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for subject agents name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		try {
			fstream = new FileWriter(parent.getLocalName()+"_"+name + ".csv");

			out = new BufferedWriter(fstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * getter for subject agents AID
	 * @return
	 */
	public AID getAID() {
		return AID;
	}

	/**
	 * setter for subject agents AID
	 * @param aid
	 */
	public void setAID(AID aid) {
		this.AID = aid;
	}

	/**
	 * getter for questions this agent asked
	 * @return
	 */
	public int getAsked() {
		return asked;
	}

	/**
	 * setter for questions this agent asked
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
	 * @return
	 */
	public int getAnswered() {
		return answered;
	}

	/**
	 * setter for questions this agent answered
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
	 * getter for the number questions by the parent this
	 *  agent answered
	 * @return
	 */
	public int getMyAnswer() {
		return myAnswer;
	}

	/**
	 * setter for the number of questions by the parent
	 * this agent answered
	 * @param myAnswer
	 */
	public void setMyAnswer(int myAnswer) {
		this.myAnswer = myAnswer;
	}

	/**
	 * incrementer for the number of questions by the
	 * parent this agent answered
	 */
	public void incMyAnswer() {
		myAnswer++;
	}

	/**
	 * getter for the last time this agent was heard
	 * from
	 * @return
	 */
	public long getLastCom() {
		return lastCom;
	}

	/**
	 * getter for the current wait time since this agent
	 * was last heard from
	 * @param currentTime
	 * @return
	 */
	public long getWait(long currentTime) {
		return currentTime - lastCom;
	}

	/**
	 * setter for the last time this agent communicated
	 * @param currentTime
	 */
	public void setLastCom(long currentTime) {
		if (lastCom == 0)
			totalWait = 0;
		else
			totalWait += currentTime - lastCom;
		waitCount++;
		this.lastCom = currentTime;
	}

	/**
	 * getter for the averiage wait time for this
	 * agent
	 * @return
	 */
	public long getAvgWait() {
		if (waitCount == 0)
			return -1;
		else
			return totalWait / waitCount;
	}

	/**
	 * update method for the output
	 */
	public void update() {
		/*System.out.println(name + ": asked=" + asked + " answered=" + answered
				+ "\nmyAnswers=" + myAnswer + " lastWait="
				+ getWait(System.currentTimeMillis()) + " avgWiat="
				+ getAvgWait() + "\n");
*/
		output = asked + "," + answered + "," + myAnswer + ","
				+ getWait(System.currentTimeMillis()) + "," + getAvgWait();
	}

	/**
	 * method to write a stats event out to the storige file
	 * @param answered
	 */
	public void write(boolean answered, int totalAsked) {
			if(answered) 
				output+=","+totalAsked+",1\n";
			else
				output+=","+totalAsked+",0\n";
			
			try {
				out.write(output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * method to close the writer
	 */
	public void close(){
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
