package agent.Interaction;

import java.util.ArrayList;

import agent.Knowledge.Personality;

import sudoku.Coordinate;

import control.Message;
import jade.core.behaviours.CyclicBehaviour;

public class Reaction extends CyclicBehaviour {

	private final int YES = 1, NO = 2, CHANGE = 3;
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
		while (msg != null) {
			if (msg.getPerformative() != Message.QUERY_IF)
				inform.add(msg);
			else if (msg.getPerformative() == Message.QUERY_IF)
				query.add(msg);
			msg = (Message) parent.receive();
		}

		if (!inform.isEmpty())
			for (Message res : inform) {
				switch (res.getPerformative()) {
				case Message.INFORM:
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

		if (!query.isEmpty())
			for (Message res : query) {
				query(res);
			}
	}

	private void query(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);
		if (!parent.haveResponded(res.getCoordinate())) {
			parent.setActionState(Pupil.WORKING);
			parent.responded(res.getCoordinate());
			if (parent.checkAnswer(res.getCoordinate(), this)) {
				if (true)// !parent.shy())
					Messages.agree(res.getCoordinate(), parent.getPeers(),
							parent);
			} else {
				if (true)// !parent.shy())
					Messages.disagree(res.getCoordinate(), parent.getPeers(),
							parent);
			}
		}
	}

	private void focus(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);
		switch (parent.getActionState()) {
		case Pupil.ARGUING:
			parent.setAnswered(res.getCoordinate());
			parent.setActionState(Pupil.WORKING);
			break;
		case Pupil.DISTRACTED:
			parent.setActionState(Pupil.WORKING);
			parent.resetSelfEsteam();
			Messages.focused(parent.getPeers(), parent);
			break;
		case Pupil.SHY:
			parent.setActionState(Pupil.WORKING);
			parent.resetSelfEsteam();
			break;
		}
	}

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

	private boolean errorQuery(String content) {
		if (content.contains("agree"))
			return false;
		else
			return false;
	}

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

	private void distract(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.DISTRACTED);
		if (parent.getActionState() == Pupil.DISTRACTED) {
			if (parent.getWaitTime() > 2000) {
				parent.setWaitTime();
				Messages.wasteTime(parent.getPeers(), res.getSender(), parent);
			}
		} else if (parent.isChatty()) {// , prior)){
			parent.setActionState(Pupil.DISTRACTED);
			Messages.wasteTime(parent.getPeers(), res.getSender(), parent);
		} else {
			// parent.lowerFocus(res.getSender());
			parent.setActionState(Pupil.WORKING);
		}
	}

	private void inform(Message res) {
		parent.setPeerActionState(res.getSender(), Pupil.WORKING);
		switch (getType(res.getContent())) {
		case YES:
			if (parent.getAsking() != null)
				if (parent.getAsking().equals(res.getCoordinate())) {
					parent.incSelfEsteam();
					parent.incFocus(res.getSender());
					parent.setAnswered(res.getCoordinate());
					parent.answer(res.getCoordinate());
					Messages.change(res.getCoordinate(), parent.getPeers(),
							parent, res.getSender());
				} else {
					parent.decSelfEsteam();
				}
			break;
		case NO:
			if (parent.getAsking() != null)
				if (res.getCoordinate().equals(parent.getAsking())) {
					parent.refreshWorld();
					if (parent.checkAnswer(res.getCoordinate(), this)) {
						Messages.acknowledge(res.getCoordinate(),
								parent.getPeers(), parent, res.getSender());
						parent.decSelfEsteam();
						parent.setActionState(Pupil.WORKING);
					} else {
						if (true) {// parent.decide(Personality.ARGUE, 0)){
							Messages.argue(res.getCoordinate(),
									parent.getPeers(), parent, res.getSender());
							// parent.lowerFocus(res.getSender());
							parent.setActionState(Pupil.ARGUING);
						} else {
							parent.setAnswered(res.getCoordinate());
							parent.decSelfEsteam();
							parent.setActionState(Pupil.WORKING);
						}
					}
				}
			break;
		case CHANGE:
			parent.setPeerActionState(res.getSender(), Pupil.WORKING);
			if (res.getFocus().equals(parent.getAID())) {
				parent.incSelfEsteam();
			} else
				parent.decSelfEsteam();
			parent.setAnswered(res.getCoordinate());
			parent.setActionState(Pupil.WORKING);
			break;
		default:
			break;
		}
	}

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
