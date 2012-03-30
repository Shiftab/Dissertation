package agent.Interaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import agent.Knowledge.OthersModel;
import agent.Knowledge.Personality;
import agent.Knowledge.WorldView;

import sudoku.Coordinate;
import sudoku.Sudoku;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class Pupil extends Agent {

	boolean testing = false;
	boolean testingDisability = true;

	private static final int NORM = 1, DYSLEX = 2, DYSCAL = 3;

	Random r = new Random();

	// TODO: diside if use thease
	// finals for state modifiers
	/*
	 * private final double ASKING_MOD = 1; private final double ANSWERING_MOD =
	 * 2; private final double ARGUING_MOD = 3; private final double WAITING_MOD
	 * = 4; private final double DISTRACTED_MOD = 5;
	 */
	// finals for state
	public static final int ASKING = 1;
	public static final int ANSWERING = 2;
	public static final int ARGUING = 3;
	public static final int WAITING = 4;
	public static final int DISTRACTED = 5;

	public static final int VIS_WORKING = 1;
	public static final int VIS_ARGUING = 2;
	public static final int VIS_DISTRACTED = 3;
	public static final int VIS_SHY = 4;

	// wait modifier that changes based on previous things
	private volatile int state = 4;
	private WorldView world;
	private Sudoku brain;
	private Personality personality;
	private OthersModel others;
	private List<Coordinate> asking = new ArrayList<Coordinate>();
	private List<Coordinate> asked = new ArrayList<Coordinate>();
	private List<Coordinate> responded = new ArrayList<Coordinate>();
	private List<Coordinate> checked = new ArrayList<Coordinate>();

	@SuppressWarnings("unchecked")
	@Override
	protected void setup() {
		super.setup();

		Object[] args = getArguments(); // get arguments from startup

		List<AID> peers = new ArrayList<AID>();
		List<String> temp = (List<String>) args[1];
		for (String s : temp) {
			if (!s.equals(this.getLocalName())) {
				peers.add(new AID(s, AID.ISLOCALNAME));
			}
		}

		others = new OthersModel(peers);

		System.out.print(this.getAID().getLocalName() + ": ");

		if (!testingDisability) {
			personality = new Personality(0);
		} else if (this.getAID().getLocalName().equals("Alicia")) {
			// dyscalculic
			personality = new Personality(DYSCAL);
		} else if (this.getAID().getLocalName().equals("Steve")) {
			// dyslexic
			personality = new Personality(DYSLEX);
		} else if (this.getAID().getLocalName().equals("Bob")) {
			// "normal"
			personality = new Personality(NORM);
		} else {
			personality = new Personality(0);
		}

		// persons understanding of the world
		world = new WorldView(peers, personality.isDyslexic());

		brain = new Sudoku(world.getProblem());

		this.addBehaviour(new Action(this));

		this.addBehaviour(new Reaction(this));

	}

	public Map<AID, Integer> getPeers() {
		return world.getPeers();
	}

	public void waits(Behaviour b) {
		if (personality.isDyslexic() && !testing) {
			int wait = r.nextInt(150) + 200;
			doWait(wait);
			// b.block(wait);
		} else if (personality.isDyscalculic() && !testing) {
			int wait = r.nextInt(150) + 400;
			doWait(wait);
			// b.block(wait);
		} else {
			int wait = r.nextInt(150) + 100;
			doWait(wait);
			// b.block(wait);
		}
	}

	public void changeState(int state) {
		if (this.state != state) {
			this.state = state;
		}
	}

	public List<Coordinate> getAnswered() {
		return responded;
	}

	public List<Coordinate> getAsked() {
		return asked;
	}

	public List<Coordinate> getAsking() {
		return asking;
	}

	public void setAsking(List<Coordinate> asking) {
		this.asking = asking;
	}

	public void setAnswered(List<Coordinate> responded) {
		this.responded = responded;
	}

	public void setAsked(List<Coordinate> asked) {
		this.asked = asked;
	}

	public void refreshWorld() {
		world.refresh();
		brain.refresh(world.getProblem());
	}

	public void setPeerVisState(AID aid, int state) {
		world.setVisState(aid, state);
	}

	public boolean checkAlready(Coordinate coordinate) {
		return world.check(coordinate);
	}

	public boolean checkCorrect(Coordinate coordinate) {
		return brain.check(coordinate);
	}

	public boolean decide(int decision, double prior) {
		return personality.decide(decision, prior);
	}

	public void addChecked(Coordinate coordinate) {
		checked.add(coordinate);
	}

	public boolean checkErr(Coordinate coordinate) {
		return brain.checkErr(coordinate);
	}

	public void edditProblem(Coordinate coordinate) {
		world.edditProblem(coordinate);
	}

	public List<Coordinate> getResponded() {
		return responded;
	}

	public void setResponded(List<Coordinate> responded) {
		this.responded = responded;
	}

	public void print() {
		brain.print();
	}

	public void search(Behaviour b) {
		waits(b);
		if (this.getCurQueueSize() == 0) {
			world.refresh();
			brain.refresh(world.getProblem());
			Coordinate nextNum = brain.nextNumber();
			if (nextNum == null) {
				// TODO: make this an official message and create a stuck and a
				// finish system
				if (brain.done()) {
					//endStats();
				} else {
					Coordinate err = brain.searchErr();
					if (err != null) {
						Messages.errorQuery(err, getPeers().keySet(), this);
					}
				}
			} else if (!asked.contains(nextNum)) {
				asked.add(nextNum);
				asking.add(nextNum);
				Messages.query(nextNum, getPeers().keySet(), this,
						others.focus());
			}
		}
	}

	public void removeCoord(Coordinate err) {
		for (Coordinate c : asked) {
			if (c.getX() == err.getX() && c.getY() == err.getY()) {
				asked.remove(c);
				break;
			}
		}
		for (Coordinate c : responded) {
			if (c.getX() == err.getX() && c.getY() == err.getY()) {
				responded.remove(c);
				break;
			}
		}
		for (Coordinate c : asking) {
			if (c.getX() == err.getX() && c.getY() == err.getY()) {
				asking.remove(c);
				break;
			}
		}
	}

	public int getBehaviourState() {
		return state;
	}

	public boolean shouldEncurage() {
		if (personality.decide(Personality.ENCORIGE, 0))
			encurage();
		else
			return false;

		return true;
	}

	public boolean shy() {
		return personality.decide(Personality.SHY, 0);
	}

	public void encurage() {
		AID shy = this.world.getShyPerson();
		Messages.encurage(this.world.getPeers().keySet(), shy, this);
	}

	public boolean shouldBreakDistract() {
		if (personality.decide(Personality.FOCUS, 0))
			breakDistract();
		else
			return false;

		return true;
	}

	public void breakDistract() {
		AID distracted = this.world.getDistracted();
		Messages.reprisal(this.world.getPeers().keySet(), distracted, this);
	}

	public boolean shouldBreakArgue() {
		if (personality.decide(Personality.PLACATE, 0))
			breakArgue();
		else
			return false;

		return true;
	}

	public void breakArgue() {
		AID arguer = this.world.getArguing();
		Messages.advise(this.world.getPeers().keySet(), arguer, this);
	}
}
