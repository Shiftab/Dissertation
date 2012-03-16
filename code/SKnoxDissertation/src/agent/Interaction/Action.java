package agent.Interaction;

import jade.core.behaviours.CyclicBehaviour;

/**
 * class for handeling the action behaviours of agents
 * @author shiftab
 *
 */
@SuppressWarnings("serial")
public class Action extends CyclicBehaviour {

	private Pupil parent;
	
	public Action(Pupil parent){
		super(parent);
		this.parent = parent;
	}
	
	@Override
	public void action() {
		switch (parent.getBehaviourState()) {
		case Pupil.ASKING: // if asking probably shouldn't be doing two things
						// at once
			break;
		case Pupil.ANSWERING: // same as asking
			break;
		case Pupil.ARGUING: // continue arguing or switch to wait?
			parent.pickAFight();
			break;
		case Pupil.WAITING:
			// will continue waiting?
			parent.waiting(this);
			break;
		case Pupil.DISTRACTED:
			// continue being distracted?
			parent.stopDistraction();
			break;
		}

	}

}
