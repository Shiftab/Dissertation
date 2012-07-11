package control;

import sudoku.Coordinate;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 * class for extending ACLMessage to add some new message types and parameters
 * 
 * Message types: ARGUE DISTRACT REPRISAL FOCUS ERROR
 * 
 * Parameters: Coordinate focus
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class Message extends ACLMessage {

	public static final int ARGUE = -2;
	public static final int DISTRACT = -3;
	public static final int REPRISAL = -4;
	public static final int FOCUS = -5;
	public static final int ERROR = -6;
	public static final int ENCORAGE = -7;

	private Coordinate coordinate = null;
	private AID focus = null;

	public Message(int preform) {
		super(preform);
	}

	/**
	 * set coordinate refered to in message
	 * 
	 * @param coordinate
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * get coordinate refered to in message
	 * 
	 * @return
	 * @throws NullPointerException
	 */
	public Coordinate getCoordinate() throws NullPointerException {
		return coordinate;
	}

	/**
	 * get AID Focus of the message
	 * 
	 * @return
	 * @throws NullPointerException
	 */
	public AID getFocus() throws NullPointerException {
		return focus;
	}

	/**
	 * set AID focus of the message
	 * 
	 * @param focus
	 */
	public void setFocus(AID focus) {
		this.focus = focus;
	}

}
