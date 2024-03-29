package agent.Interaction;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.Set;

import control.Message;

import sudoku.Coordinate;

/**
 * class used to construct and send messages to agents
 * 
 * @author Steven Knox
 * 
 */
public class Messages {

	/**
	 * send a query about a new value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void query(Coordinate coordinate, Set<AID> send,
			Agent sender, AID focus) {
		Message msg = new Message(ACLMessage.QUERY_IF);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		if (focus != null)
			msg.setFocus(focus);

		msg.setContent("Would the number at (" + coordinate.getX() + ","
				+ coordinate.getY() + ") be " + coordinate.getVal() + "?");
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * send a query about a posible error
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void errorQuery(Coordinate coordinate, Set<AID> send,
			Agent sender) {
		Message msg = new Message(Message.ERROR);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}

		msg.setContent("I don't think the number at (" + coordinate.getX()
				+ "," + coordinate.getY() + ") should be "
				+ coordinate.getVal());
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * send a confirm message agreing to the existance of an error
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void agreeError(Coordinate coordinate, Set<AID> send,
			Agent sender) {
		Message msg = new Message(Message.ERROR);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}

		msg.setContent("I agree,the number at (" + coordinate.getX() + ","
				+ coordinate.getY() + ") shouldn't be " + coordinate.getVal());
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method used to agree with a query about a new value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void agree(Coordinate coordinate, Set<AID> send, Agent sender) {
		Message msg = new Message(ACLMessage.INFORM);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setContent("Yes, I think " + coordinate.getVal() + " would be at ("
				+ coordinate.getX() + "," + coordinate.getY() + ").");
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to disagree with a value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void disagree(Coordinate coordinate, Set<AID> send,
			Agent sender) {
		Message msg = new Message(ACLMessage.INFORM);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setContent("No, I don't think " + coordinate.getVal()
				+ " would be at (" + coordinate.getX() + ","
				+ coordinate.getY() + ").");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		msg.setCoordinate(coordinate);
		
		sender.send(msg);
	}

	/**
	 * method for changing the values
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void change(Coordinate coordinate, Set<AID> send,
			Agent sender, AID focus) {
		Message msg = new Message(ACLMessage.INFORM);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setFocus(focus);
		
		msg.setContent("Changing value to " + coordinate.getVal() + " at ("
				+ coordinate.getX() + "," + coordinate.getY() + ").");
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to argue about the existence of a value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void argue(Coordinate coordinate, Set<AID> send,
			Agent sender, AID focus) {
		Message msg = new Message(Message.ARGUE);
		
		for (AID s : send)
			msg.addReceiver(s);

		msg.setFocus(focus);
				
		msg.setContent("Well I think " + coordinate.getVal() + " would be at ("
				+ coordinate.getX() + "," + coordinate.getY() + ")!");
		
		msg.setCoordinate(coordinate);
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to acknowledge a mistake
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void acknowledge(Coordinate coordinate, Set<AID> send,
			Agent sender, AID focus) {
		
		Message msg = new Message(ACLMessage.INFORM);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setCoordinate(coordinate);
		
		msg.setFocus(focus);
		
		msg.setContent("Woops I must have not been paying attention.");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to distract a peer
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void distract(Set<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.DISTRACT);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setFocus(focus);
		
		msg.setContent("CONFUSION!!! *waves arms*.");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to react positively to the distraction
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void wasteTime(Set<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.DISTRACT);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setFocus(focus);
		
		msg.setContent("CONFUSION is SUPER EFFECTIVE!.");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method to advise a peer to ignore an agresive distracter
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void focus(Set<AID> send, Agent sender) {
		Message msg = new Message(Message.FOCUS);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setContent("Just ignore him/her");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}

	/**
	 * method used as a response to focus
	 * @param send
	 * @param sender
	 * @deprecated
	 */
	public static void focused(Set<AID> send, Agent sender) {
		Message msg = new Message(ACLMessage.INFORM);
		
		for (AID a : send) {
			msg.addReceiver(a);
		}
		
		msg.setContent("Fine I'll ignore him/her");
		
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		
		sender.send(msg);
	}
}
