package control;

import sudoku.Coordinate;
import jade.lang.acl.ACLMessage;

public class Message extends ACLMessage {

	public static final int ARGUE = -2;
	public static final int DISTRACT = -3;
	public static final int REPRISAL = -4;
	public static final int FOCUS = -5;
	private Coordinate coordinate=null;
	
	public Message(int preform){
		super(preform);
	}
	
	public void setCoordinate(Coordinate c){
		coordinate = c;
	}
	
	public Coordinate getCoordinate() throws NullPointerException{
		return coordinate;
	}
	
}
