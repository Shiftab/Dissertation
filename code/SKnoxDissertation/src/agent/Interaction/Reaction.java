package agent.Interaction;

import java.util.ArrayList;
import java.util.List;

import sudoku.Coordinate;

import agent.Knowledge.Personality;
import control.Message;
import control.Problem;
import jade.core.behaviours.CyclicBehaviour;

/**
 * class to handel the reactive behaviours of agents
 * 
 * @author shiftab
 * 
 */
@SuppressWarnings("serial")
public class Reaction extends CyclicBehaviour {

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
				case Message.ENCORAGE:
					encorage(res);
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

	private void inform(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_WORKING);
	//	peerData.get(res.getSender()).incAnswered();
		List<Coordinate> asked = parent.getAsked();
		List<Coordinate> asking = parent.getAsking();
		List<Coordinate> responded = parent.getResponded();
		if (asking.contains(res.getCoordinate())) {
			if (res.getContent().contains("already")) {
				Messages.acknowledge(parent.getPeers().keySet(), parent);
				parent.changeState(Pupil.WAITING);
				asking.remove(res.getCoordinate());
			} else if (res.getContent().startsWith("Yes")) {
				if (asked.contains(res.getCoordinate())) {
					if (Problem.edditProblem(res.getCoordinate())) {
//						totalQ++;
//						peerData.get(res.getSender()).incMyAnswer();
//						write(res.getSender());
						Messages.change(res.getCoordinate(), parent.getPeers().keySet(), parent);
						parent.edditProblem(res.getCoordinate());
						parent.print();
					}
					parent.refreshWorld();
					parent.changeState(Pupil.WAITING);
					asking.remove(res.getCoordinate());
//					usefullAnswers.add(res.getSender());
				}
			} else if (res.getContent().startsWith("No")) {
				// TODO:Stuff for errors
			} else if (res.getContent().contains("Changing")) {
				
			}
		}
		if (res.getCoordinate() != null) {
			if(!asked.contains(res.getCoordinate()))
			asked.add(res.getCoordinate());
			if(!responded.contains(res.getCoordinate()))
			responded.add(res.getCoordinate());
		}
		
		parent.setAsked(asked);
		parent.setAsking(asking);
		parent.setResponded(responded);
	}

	private void error(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_WORKING);
		if (res.getContent().contains("agree")) { // response
			parent.refreshWorld();
			parent.addChecked(res.getCoordinate());
		} else { // query
			parent.refreshWorld();
			if (parent.checkErr(res.getCoordinate())) {
				Messages.agreeError(res.getCoordinate(), parent.getPeers()
						.keySet(), parent);
				parent.edditProblem(new Coordinate(res.getCoordinate().getX(),
						res.getCoordinate().getY(), 0));
				parent.refreshWorld();
				parent.removeCoord(res.getCoordinate());
			} else {
				Messages.argue(res.getCoordinate(), res.getSender(), parent);
			}
		}
	}

	private void argue(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_ARGUING);
		// at the moment the only argue is somone saying that there number
		// is correct
		parent.refreshWorld();
		if(parent.checkErr(res.getCoordinate())){
			Messages.acknowledge(parent.getPeers().keySet(), parent);
			parent.edditProblem(res.getCoordinate());
			parent.refreshWorld();
		}else if(parent.decide(Personality.ARGUE, 0)){
			Messages.disagree(res.getCoordinate(), parent.getPeers().keySet(), parent);
		}else{
			parent.refreshWorld();
			Messages.acknowledge(parent.getPeers().keySet(), parent);
		}
	}

	private void distract(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_DISTRACTED);
		if (parent.decide(Personality.CHATTER, 0)) {
			Messages.wasteTime(parent.getPeers().keySet(), res.getSender(), parent);
			parent.changeState(Pupil.DISTRACTED);
		} else if (parent.decide(Personality.FOCUS, 0)) {
			Messages.reprisal(parent.getPeers().keySet(), res.getSender(), parent);
			parent.changeState(Pupil.WAITING);
		} else {
			parent.changeState(Pupil.WAITING);
		}
	}

	private void focus(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_WORKING);
		if (!parent.decide(Personality.IGNORE, 0)) {
			parent.changeState(Pupil.WAITING);
		}
	}

	private void encorage(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_WORKING);
		if(!parent.decide(Personality.IGNORE, 0)){
			parent.changeState(Pupil.WAITING);
		}
	}

	private void query(Message res) {
		parent.setPeerVisState(res.getSender(), Pupil.VIS_WORKING);
		int state = parent.getState();
		List<Coordinate> responded = parent.getAnswered();
		List<Coordinate> asked = parent.getAsked();

		if (state != Pupil.ARGUING || state != Pupil.DISTRACTED) {
			Coordinate check = res.getCoordinate();
			if (check != null)
				if (!responded.contains(check)) {
					// peerData.get(res.getSender()).incAsked();
					parent.changeState(Pupil.ANSWERING);
					parent.waits(this);
					asked.add(check);
					parent.refreshWorld();
					if (parent.checkAlready(check)) {
						if (parent.decide(Personality.DISAGREE, 0)) {
							responded.add(check);
							Messages.already(check, parent.getPeers().keySet(),
									parent);
						}
					} else if (parent.checkCorrect(check)) {
						if (parent.decide(Personality.AGREE, 0)) {
							responded.add(check);
							Messages.agree(check, parent.getPeers().keySet(),
									parent);
							// qAnswer++;
						}
					} else {
						if (parent.decide(Personality.DISAGREE, 0)) {
							responded.add(check);
							Messages.disagree(check,
									parent.getPeers().keySet(), parent);
						}
					}
				}
			parent.changeState(Pupil.WAITING);
		}
		parent.setAsked(asked);
		parent.setAnswered(responded);
	}
}
