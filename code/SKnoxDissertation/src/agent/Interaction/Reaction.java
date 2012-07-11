package agent.Interaction;

import java.util.ArrayList;

import sudoku.Coordinate;

import control.Message;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * class to handel the reaction behaviours of a pupil
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class Reaction extends CyclicBehaviour {

	private final int YES = 1, NO = 2, CHANGE = 3; // inform message types
	private Pupil parent;

	/**
	 * constructor to set the parent agent of a behaviour
	 * 
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

		// split the messages into querys and informs
		while (msg != null) {
			if (msg.getPerformative() != ACLMessage.QUERY_IF)
				inform.add(msg);
			else if (msg.getPerformative() == ACLMessage.QUERY_IF)
				query.add(msg);
			msg = (Message) parent.receive();
		}

		/*
		 * check informs first to stop pupil from answering querys already
		 * answered by another
		 */
		if (!inform.isEmpty())
			for (Message res : inform) {
				switch (res.getPerformative()) {
				case ACLMessage.INFORM:
					inform(res);
					break;
				case Message.ARGUE:
					argue(res);
					break;
				case Message.DISTRACT:
					distract(res);
					break;
				case Message.ERROR:
					error(res);
					break;
				case Message.FOCUS:
					focus(res);
					break;
				}
			}

		if (!query.isEmpty()) // check the querys
			for (Message res : query) {
				query(res);
			}
	}

	/**
	 * method to handle the response to a query message
	 * 
	 * @param res
	 */
	private void query(Message res) {
		parent.incQuestionsStats();
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);

		if (!parent.haveResponded(res.getCoordinate())
				&& parent.getActionState() != Pupil.SHY
				&& parent.getActionState() != Pupil.DISTRACTED) {

			parent.setActionState(Pupil.WORKING);
			parent.responded(res.getCoordinate());

			if (parent.checkAnswer(res.getCoordinate(), this)) {
				Messages.agree(res.getCoordinate(), parent.getPeers(), parent);
			} else {
				Messages.disagree(res.getCoordinate(), parent.getPeers(),
						parent);
			}
		}
	}

	/**
	 * method to handel a focus message
	 * 
	 * @param res
	 */
	private void focus(Message res) {

		parent.setPeerActionState(res.getSender(), Pupil.WORKING);

		/*
		 * swich statment was more usefull when distract focus could create
		 * argument
		 */
		switch (parent.getActionState()) {
		case Pupil.ARGUING:
			parent.setAnswered(res.getCoordinate());
			parent.setActionState(Pupil.WORKING);

			break;

		case Pupil.DISTRACTED:
			parent.setActionState(Pupil.WORKING);
			parent.resetSelfEsteam();

			break;

		case Pupil.SHY:
			parent.setActionState(Pupil.WORKING);
			parent.resetSelfEsteam();

			break;
		}
	}

	/**
	 * method to handel the rsponse of an error query or error query response
	 * 
	 * @param res
	 * @deprecated
	 */
	private void error(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);

		if (errorQuery(res.getContent())) {

			if (res.getFocus().equals(parent.getAID())) {
				Coordinate wipe = res.getCoordinate();
				wipe.setVal(0);
				parent.newNumber(res.getCoordinate());
				parent.setActionState(Pupil.WORKING);

				Messages.change(wipe, parent.getPeers(), parent,
						res.getSender());
				parent.incFocus(res.getSender());
			}

		} else {

			parent.responded(res.getCoordinate());
			parent.setActionState(Pupil.WORKING);

			if (parent.checkErr(res.getCoordinate(), this)) {
				Messages.agreeError(res.getCoordinate(), parent.getPeers(),
						parent);
			} else {
				Messages.disagree(res.getCoordinate(), parent.getPeers(),
						parent);
			}
		}
	}

	/**
	 * method used to check if an error message was a query or a response
	 * 
	 * @param content
	 * @return
	 * @deprecated
	 */
	private boolean errorQuery(String content) {
		if (content.contains("agree"))
			return false;
		else
			return false;
	}

	/**
	 * method used to handel an argue message
	 * 
	 * @param res
	 * @deprecated
	 */
	private void argue(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.ARGUING);

		if (parent.getActionState() == Pupil.ARGUING)
			parent.setWaitTime();

		if (!parent.isAnswered(res.getCoordinate())) {

			parent.refreshWorld();

			if (parent.checkAnswer(res.getCoordinate(), this)) {
				parent.decSelfEsteam();
				parent.setActionState(Pupil.WORKING);
				Messages.acknowledge(res.getCoordinate(), parent.getPeers(),
						parent, res.getSender());
			} else {
				parent.setActionState((Pupil.ARGUING));
				Messages.argue(res.getCoordinate(), parent.getPeers(), parent,
						res.getSender());
				// parent.lowerFocus(res.getSender());
			}
		}
	}

	/**
	 * method to handel a distraction message
	 * 
	 * @param res
	 */
	private void distract(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.DISTRACTED);

		if (res.getFocus().equals(parent.getAID())) {// if it's directed at me

			if (parent.getActionState() == Pupil.DISTRACTED// disractable
					|| parent.getActionState() == Pupil.SHY) {
				parent.setDistracted(res.getSender().getLocalName());
				parent.setActionState(Pupil.DISTRACTED);
				parent.setWaitTime();
				parent.pauseAction(this);
				Messages.wasteTime(parent.getPeers(), res.getSender(), parent);

			} else if (parent.isChatty()) {// , prior)){ isDistractible?
				parent.setDistracted(res.getSender().getLocalName());
				parent.setActionState(Pupil.DISTRACTED);
				parent.pauseAction(this);
				Messages.wasteTime(parent.getPeers(), res.getSender(), parent);

			} else { // not distractable
				// parent.lowerFocus(res.getSender());
				parent.setActionState(Pupil.WORKING);
			}

		}
	}

	/**
	 * method to handel inform messages
	 * 
	 * @param res
	 */
	private void inform(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);

		switch (getType(res.getContent())) {
		case YES:
			if (parent.getAsking() != null)
				if (parent.getAsking().equals(res.getCoordinate())) {
					// answer was correct
					parent.incSelfEsteam();
					parent.incFocus(res.getSender());
					parent.setAnswered(res.getCoordinate());
					parent.answer(res.getCoordinate());
					Messages.change(res.getCoordinate(), parent.getPeers(),
							parent, res.getSender());

				}

			break;

		case NO:
			if (parent.getAsking() != null)
				try {
					if (res.getCoordinate().equals(parent.getAsking())) {
						parent.refreshWorld();

						if (parent.checkAnswer(res.getCoordinate(), this)) {
							// got it wrong
							Messages.acknowledge(res.getCoordinate(),
									parent.getPeers(), parent, res.getSender());
							parent.decSelfEsteam();
							parent.setActionState(Pupil.WORKING);

						} else { // don't thing got it wrong
							/*
							 * deprecated argument system if (false) {//
							 * parent.decide(Personality.ARGUE, 0)){
							 * Messages.argue(res.getCoordinate(),
							 * parent.getPeers(), parent, res.getSender()); //
							 * parent.lowerFocus(res.getSender());
							 * parent.setActionState(Pupil.ARGUING); } else {
							 */
							parent.setAnswered(res.getCoordinate());
							parent.decSelfEsteam();
							parent.setActionState(Pupil.WORKING);
							// }
						}
					}
				} catch (NullPointerException np) {
					np.printStackTrace();
					System.out
							.println("Error wrong answer:" + res.getContent());
				}

			break;

		case CHANGE:
			parent.setPeerActionState(res.getSender(), Pupil.WORKING);

			if (res.getFocus().equals(parent.getAID())) {
				// this pupils answer was used
				parent.incSelfEsteam();
				parent.incAnsweredStats();
			} else
				parent.decSelfEsteam();

			parent.setAnswered(res.getCoordinate());

			// update stat logs
			if (parent.getActionState() == Pupil.DISTRACTED)
				parent.incDistractionsStats();
			else if (parent.getActionState() == Pupil.SHY)
				parent.incShyMissedStats();

			break;

		}
	}

	/**
	 * method to get the type of the inform message
	 * 
	 * @param content
	 * @return
	 */
	private int getType(String content) {
		content = content.toLowerCase();
		if (content.contains("yes")) {
			return YES;
		} else if (content.contains("no")) {
			return NO;
		} else if (content.contains("changing")) {
			return CHANGE;
		} else if (content.contains("woops")) {
			return CHANGE;
		} else
			return -1; // error
	}
}
