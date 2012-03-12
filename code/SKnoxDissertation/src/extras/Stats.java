package extras;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import jade.core.AID;

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

	public String getName() {
		return name;
	}

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

	public AID getAID() {
		return AID;
	}

	public void setAID(AID aid) {
		this.AID = aid;
	}

	public int getAsked() {
		return asked;
	}

	public void setAsked(int asked) {
		this.asked = asked;
	}

	public void incAsked() {
		asked++;
	}

	public int getAnswered() {
		return answered;
	}

	public void setAnswered(int answered) {
		this.answered = answered;
	}

	public void incAnswered() {
		answered++;
	}

	public int getMyAnswer() {
		return myAnswer;
	}

	public void setMyAnswer(int myAnswer) {
		this.myAnswer = myAnswer;
	}

	public void incMyAnswer() {
		myAnswer++;
	}

	public long getLastCom() {
		return lastCom;
	}

	public long getWait(long currentTime) {
		return currentTime - lastCom;
	}

	public void setLastCom(long currentTime) {
		if (lastCom == 0)
			totalWait = 0;
		else
			totalWait += currentTime - lastCom;
		waitCount++;
		this.lastCom = currentTime;
	}

	public long getAvgWait() {
		if (waitCount == 0)
			return -1;
		else
			return totalWait / waitCount;
	}

	public void print() {
		/*System.out.println(name + ": asked=" + asked + " answered=" + answered
				+ "\nmyAnswers=" + myAnswer + " lastWait="
				+ getWait(System.currentTimeMillis()) + " avgWiat="
				+ getAvgWait() + "\n");
*/
		output = asked + "," + answered + "," + myAnswer + ","
				+ getWait(System.currentTimeMillis()) + "," + getAvgWait();
	}

	public void write(boolean answered) {
			if(answered) 
				output+=",1\n";
			else
				output+=",0\n";
			
			try {
				out.write(output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void close(){
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
