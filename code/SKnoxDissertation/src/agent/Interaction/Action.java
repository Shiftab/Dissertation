package agent.Interaction;

import agent.Reasoning.DecisionMaking;
import jade.core.behaviours.CyclicBehaviour;

public class Action extends CyclicBehaviour {

	private Pupil2 parent;
	
	public Action(Pupil2 parent){
		super(parent);
		this.parent = parent;
	}
	
	@Override
	public void action() {
		switch (parent.getBehaviourState()) {
		case Pupil2.ASKING: // if asking probably shouldn't be doing two things
						// at once
			break;
		case Pupil2.ANSWERING: // same as asking
			break;
		case Pupil2.ARGUING: // continue arguing or switch to wait?
			parent.pickAFight();
			break;
		case Pupil2.WAITING:
			// will continue waiting?
			parent.waiting(this);
			break;
		case Pupil2.DISTRACTED:
			// continue being distracted?
			parent.stopDistraction();
			break;
		}

	}

}
