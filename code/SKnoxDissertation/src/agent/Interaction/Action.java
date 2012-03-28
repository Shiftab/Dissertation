package agent.Interaction;

import java.util.Map;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.AID;

/**
 * class for handeling the action behaviours of agents
 * 
 * @author shiftab
 * 
 */
@SuppressWarnings("serial")
public class Action extends CyclicBehaviour {

	private Pupil parent;

	public Action(Pupil parent) {
		super(parent);
		this.parent = parent;
	}

	@Override
	public void action() {
		if (parent.getBehaviourState() == Pupil.WAITING) {
			int working = 0, arguing = 0, distracted = 0, shy = 0;
			Map<AID, Integer> pupils = parent.getPeers();
			for (AID a : pupils.keySet()) {
				switch (pupils.get(a)) {
				case Pupil.VIS_WORKING:
					working++;
					break;
				case Pupil.VIS_ARGUING:
					arguing++;
					break;
				case Pupil.VIS_DISTRACTED:
					distracted++;
					break;
				case Pupil.VIS_SHY:
					shy++;
					break;
				}
			}

			if (working >= 2) {
				// enough people to keep working and ignore others
				parent.search(this);
			}
			// else if one, check for probabilitys of search or break others
			// else if zero, check for prob of break or shy
			if (shy > 1) {
				if (working == 1) {
					if (!parent.shouldEncurage()) {
						parent.search(this);
					}
				} else if (!parent.shy()) {
					parent.encurage();
				}

			} else if (distracted > 1) {
				if (working == 1) {
					if (!parent.shouldBreakDistract()) {
						parent.search(this);
					}
				} else if (!parent.shy()) {
					parent.breakDistract();
				}
			} else if (arguing > 1) {
				if (working == 1) {
					if (!parent.shouldBreakArgue()) {
						parent.search(this);
					}
				} else if (!parent.shy()) {
					parent.breakArgue();
				}
			}
		}
	}

}
