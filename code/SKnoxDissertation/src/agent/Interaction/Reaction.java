package agent.Interaction;

import java.util.ArrayList;

import control.Message;
import jade.core.behaviours.CyclicBehaviour;

/**
 * class to handel the reactive behaviours of agents
 * @author shiftab
 *
 */
@SuppressWarnings("serial")
public class Reaction extends CyclicBehaviour {

	private Pupil parent;

	/**
	 * constructor to set the parent agent of a behaviour
	 * @param parent
	 */
	public Reaction(Pupil parent) {
		super(parent);
		this.parent = parent;
	}
	
	@Override
	public void action() {
		ArrayList<Message> inform = new ArrayList<Message>();
		ArrayList<Message> query = new ArrayList<Message>();
		Message msg = (Message) parent.receive();
		while (msg != null) {
			if (msg.getPerformative() == Message.INFORM)
				inform.add(msg);
			else if (msg.getPerformative() == Message.QUERY_IF)
				query.add(msg);
			msg = (Message) parent.receive();
		}

		if (!inform.isEmpty())
			for (Message res : inform) {
				parent.messageHandel(res, this);
			}
		
		if (!query.isEmpty())
			for (Message res : query) {
				parent.messageHandel(res, this);
			}

	}
}
