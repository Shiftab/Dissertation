package agent.Interaction;

import java.util.Map;

import sudoku.Coordinate;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;

@SuppressWarnings("serial")
public class Action extends CyclicBehaviour {
	private Pupil parent;
	private int waitCount = 0;

	public Action(Pupil parent) {
		super(parent);
		this.parent = parent;
	}

	@Override
	public void action() {
		parent.checkDone();
		parent.updateGraph();
		if (parent.timeUp()) {
			parent.stop();
		}
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
			if (parent.isShy()) {
				parent.setActionState(Pupil.SHY);
				parent.setWaitTime();
				return;
			}
			if (working > 0) {
				ask();
			} else {
				focusPeers(distracted, arguing, shy);
			}
			break;
		case Pupil.DISTRACTED:
			if (parent.getWaitTime() > 5000)
				if (parent.isShy())
					parent.setActionState(Pupil.SHY);
				else
					parent.setActionState(Pupil.WORKING);
			break;
		case Pupil.ARGUING:
			if (parent.getWaitTime() > 5000)
				parent.setActionState(Pupil.WORKING);
			break;
		case Pupil.SHY:
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

	private void ask() {
		if (parent.getAsking() == null) {
			waitCount = 0;
			Coordinate ans = parent.search(this);
			if (ans == null)
				return;
			if (!parent.asked(ans)) {
				parent.setAsking(ans);
				parent.incQuestions();
				Messages.query(ans, parent.getPeers(), parent,
						parent.getFocus());
				parent.incAskedStats();
			} else {
				parent.refreshWorld();
				System.out.println(ans);
			}
		} else if (parent.getWaitTime() > 1000) {
			if (waitCount == 5) {
				parent.clearCoordinates();
			}
			parent.setWaitTime();
			Messages.query(parent.getAsking(), parent.getPeers(), parent,
					parent.getFocus());
			waitCount++;
		}
	}

	private void focusPeers(int distracted, int arguing, int shy) {
		if (distracted > 0) {
			Messages.focus(parent.getDistracted(), parent);
		} else if (arguing > 0) {
			Messages.focus(parent.getArguing(), parent);
		} else
			Messages.focus(parent.getShy(), parent);
	}
}
