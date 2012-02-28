package agent.Interaction;

import jade.core.AID;
import jade.core.Agent;

import java.util.List;

import control.Message;

import sudoku.Coordinate;

public class Messages {

	/**
	 * method used to send a query about a new value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void query(Coordinate coordinate, List<AID> send, Agent sender) {
		Message msg = new Message(Message.QUERY_IF);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.setContent("Would the number at (" + coordinate.getX() + ","
				+ coordinate.getY() + ") be " + coordinate.getVal() + "?");
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
	public static void agree(Coordinate coordinate, List<AID> send, Agent sender) {
		Message msg = new Message(Message.INFORM);
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
	 * method used to inform about the existence of a value
	 * 
	 * @param coordinate
	 * @param send
	 * @param sender
	 */
	public static void already(Coordinate coordinate, List<AID> send,
			Agent sender) {
		Message msg = new Message(Message.INFORM);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.setContent("Yes, " + coordinate.getVal() + " is already at ("
				+ coordinate.getX() + "," + coordinate.getY() + ").");
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
	public static void disagree(Coordinate coordinate, List<AID> send,
			Agent sender) {
		Message msg = new Message(Message.INFORM);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.setContent("No, I don't think " + coordinate.getVal()
				+ " would be at (" + coordinate.getX() + ","
				+ coordinate.getY() + ").");
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
	public static void change(Coordinate coordinate, List<AID> send,
			Agent sender) {
		Message msg = new Message(Message.INFORM);
		for (AID a : send) {
			msg.addReceiver(a);
		}
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
	public static void argue(Coordinate coordinate, List<AID> send, Agent sender) {
		Message msg = new Message(Message.ARGUE);
		for (AID a : send) {
			msg.addReceiver(a);
		}
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
	public static void acknowledge(List<AID> send, Agent sender) {
		Message msg = new Message(Message.INFORM);
		for (AID a : send) {
			msg.addReceiver(a);
		}
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
	public static void distract(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.DISTRACT);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
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
	public static void wasteTime(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.DISTRACT);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
		msg.setContent("CONFUSION is SUPER EFFECTIVE!.");
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		sender.send(msg);
	}

	/**
	 * method to tentatively bring the distractor back to the problem
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void reprisal(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.REPRISAL);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
		msg.setContent("Maby we should continue with the problem?");
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		sender.send(msg);
	}

	/**
	 * method to angraly bring the distractor back to the problem
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void angryReprisal(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.REPRISAL);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
		msg.setContent("Would you shut up!");
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		sender.send(msg);
	}

	/**
	 * method to retaliate to anger
	 * 
	 * @param send
	 * @param focus
	 * @param sender
	 */
	public static void retaliate(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.ARGUE);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
		msg.setContent("No, you shut up!");
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
	public static void advise(List<AID> send, AID focus, Agent sender) {
		Message msg = new Message(Message.FOCUS);
		for (AID a : send) {
			msg.addReceiver(a);
		}
		msg.addReplyTo(focus);
		msg.setContent("Just ignore him/her");
		System.out.println(sender.getAID().getLocalName() + ": "
				+ msg.getContent());
		sender.send(msg);
	}
}
