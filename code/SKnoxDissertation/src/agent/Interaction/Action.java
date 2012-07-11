package agent.Interaction;

import java.util.Map;

import sudoku.Coordinate;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;

/**
 * class containing the action behaviour of the pupils
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class Action extends CyclicBehaviour {
	private Pupil parent;
	private int waitCount = 0;

	/**
	 * contructer used to set up the pupil as the behaviours parent
	 * 
	 * @param parent
	 */
	public Action(Pupil parent) {
		super(parent);
		this.parent = parent;
	}

	@Override
	public void action() {
		parent.checkDone(); // stop the agent if the problem is compleat

		parent.updateGraph();

		if (parent.timeUp()) { // stop the agent if the timelimit has passed
			parent.stop();
		}

		// count the numbers and states of the other pupils
		int working = 0, arguing = 0, distracted = 0, shy = 0;
		Map<AID, Integer> pupils = parent.getPeersStates();
		for (AID a : pupils.keySet()) {
			switch (pupils.get(a)) {
			case Pupil.WORKING:
				working++;
				break;
			case Pupil.ARGUING:
				arguing++;
				break;
			case Pupil.DISTRACTED:
				distracted++;
				break;
			case Pupil.SHY:
				shy++;
				break;
			}
		}

		switch (parent.getActionState()) {
		case Pupil.WORKING:
			if (parent.isShy()) { // check to see if should introvert
				parent.setActionState(Pupil.SHY);
				parent.setWaitTime();
				return;
			}

			if (working > 0) { // if somone is still working continue
				ask();
			} else { // else focus somone
				focusPeers(distracted, arguing, shy);
			}

			break;

		case Pupil.DISTRACTED:
			// if no one has said anything for a while
			if (parent.getWaitTime() > 5000)
				if (parent.isShy()) // move back to introvert
					parent.setActionState(Pupil.SHY);
				else
					// go back to work
					parent.setActionState(Pupil.WORKING);

			break;

		case Pupil.ARGUING:
			// if the other person has stoped arguing
			if (parent.getWaitTime() > 5000)
				parent.setActionState(Pupil.WORKING);// back to work

			break;

		case Pupil.SHY:
			// don't spam attempts
			if (parent.distract() && parent.getWaitTime() > 2000) {
				parent.setActionState(Pupil.DISTRACTED);
				parent.setDistract();
				parent.setWaitTime();
				Messages.distract(parent.getPeers(), parent.getDistractable(),
						parent);
			}

			break;
		}
	}

	/**
	 * searching method used to find a new query and send the query to the other
	 * pupils
	 */
	private void ask() {
		if (parent.getAsking() == null) { // not currently querying
			waitCount = 0;
			Coordinate ans = parent.search(this); // get a query
			if (ans == null)
				return;

			if (!parent.asked(ans)) { // if not already asked
				parent.setAsking(ans);
				parent.incQuestions();
				Messages.query(ans, parent.getPeers(), parent,
						parent.getFocus());
				parent.incAskedStats();
			} else {
				parent.refreshWorld();
			}

		} else if (parent.getWaitTime() > 1000) {
			/*
			 * on rair ocasions it is posible for a pupil to remove a valid
			 * answer from discussiondue to refreshing errors, this alows us to
			 * handle for that case
			 */
			if (waitCount == 5) {
				parent.clearCoordinates();
			}

			parent.setWaitTime();
			Messages.query(parent.getAsking(), parent.getPeers(), parent,
					parent.getFocus());
			waitCount++;
		}
	}

	/**
	 * method to handle the focusing of pupils engaged in disruptive behaviour
	 * 
	 * @param distracted
	 * @param arguing
	 * @param shy
	 */
	private void focusPeers(int distracted, int arguing, int shy) {
		if (distracted > 0) {
			Messages.focus(parent.getDistracted(), parent);
		} else if (arguing > 0) {
			Messages.focus(parent.getArguing(), parent);
		} else
			Messages.focus(parent.getShy(), parent);
	}
}
