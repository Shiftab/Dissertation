package agent.Interaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import control.Problem;

import agent.Knowledge.OthersModel;
import agent.Knowledge.Personality;
import agent.Knowledge.WorldView;

import sudoku.Coordinate;
import sudoku.Sudoku;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Pupil extends Agent {

	public static final int ARGUING = 3;
	public static final int WORKING = 4;
	public static final int DISTRACTED = 5;
	public static final int SHY = 6;

	private final boolean testingDisability = true;

	private final int DYSCAL = 1, DYSLEX = 2, NORM = 3;

	private WorldView world;
	private Sudoku brain;
	private Personality personality;
	private OthersModel others;

	private Coordinate asking = null;
	private List<Coordinate> asked = new ArrayList<Coordinate>(47);
	private List<Coordinate> responded = new ArrayList<Coordinate>(47);
	private List<Coordinate> answered = new ArrayList<Coordinate>(47);
	private int state = WORKING;
	private long waitTime = 0;

	@Override
	protected void setup() {

		Object[] args = getArguments(); // get arguments from startup

		List<AID> peers = new ArrayList<AID>();
		List<String> temp = (List<String>) args[1];
		for (String s : temp) {
			if (!s.equals(this.getLocalName())) {
				peers.add(new AID(s, AID.ISLOCALNAME));
			}
		}

		others = new OthersModel(peers);

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

	public void setPeerActionState(AID sender, int visWorking) {
		others.setVisState(sender, visWorking);
	}

	public void setActionState(int state) {
		this.state = state;
	}

	public int getActionState() {
		return state;
	}

	// -------------State------------

	// asking

	public Coordinate getAsking() {
		return asking;
	}

	public void setAsking(Coordinate ans) {
		// set waitTime
		this.asking = ans;
		this.asked.add(ans);
		waitTime = System.currentTimeMillis();

	}

	// asked

	public boolean asked(Coordinate ans) {
		return asked.contains(ans);
	}

	// answered

	public void newNumber(Coordinate coordinate) {
		asking = null;
		coordinate = new Coordinate(coordinate.getX(), coordinate.getY(), 0);
		Problem.edditProblem(coordinate);
	}

	public void setAnswered(Coordinate coordinate) {
		world.refresh();
		brain.refresh(world.getProblem());
		responded.add(coordinate);
		asking = null;
		answered.add(coordinate);
	}

	public boolean isAnswered(Coordinate coordinate) {
		return answered.contains(coordinate);
	}

	// responded
	public boolean haveResponded(Coordinate coordinate) {
		return responded.contains(coordinate);
	}

	public void responded(Coordinate coordinate) {
		responded.add(coordinate);
	}

	// ------------Answers/Questions---------------

	public boolean checkAnswer(Coordinate coordinate, Behaviour b) {
		System.out.println(this.getLocalName()+":"+personality.getSpeed());
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());
		return brain.check(coordinate);
	}

	public boolean checkErr(Coordinate coordinate, Behaviour b) {
		System.out.println(this.getLocalName()+":"+personality.getSpeed());
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());
		world.refresh();
		brain.refresh(world.getProblem());
		if(!world.check(coordinate))
			return false;
		
		return brain.checkErr(coordinate);
	}

	// ------------check coords-----------------

	public void resetSelfEsteam() {
		personality.resetSelfEsteam();
	}

	public void refreshWorld() {
		world.refresh();
		brain.refresh(world.getProblem());
	}

	public void decSelfEsteam() {
		personality.decSelfEsteam();
	}

	public void lowerFocus(AID sender) {
		others.lowerFocus(sender);
	}

	public void incSelfEsteam() {
		personality.incSelfEsteam();
	}

	public void incFocus(AID sender) {
		others.incFocus(sender);
	}

	// --------------selfEsteam/focus-------------
	public Map<AID, Integer> getPeersStates() {
		return others.getPeers();
	}

	public Set<AID> getPeers() {
		return others.getPeers().keySet();
	}

	// -------------others----------------

	public Set<AID> getDistracted() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.DISTRACTED)
				ans.add(a);
		}
		return ans;
	}

	public Set<AID> getArguing() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.ARGUING)
				ans.add(a);
		}
		return ans;
	}

	public Set<AID> getShy() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.SHY)
				ans.add(a);
		}
		return ans;
	}

	public AID getFocus() {
		return others.focus();
	}

	// --------------------self---------------------

	public long getWaitTime() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() - waitTime;
	}

	public void setWaitTime() {
		waitTime = System.currentTimeMillis();
	}

	public Coordinate search(Behaviour b) {
		doWait(personality.getSpeed());
			b.block(personality.getSpeed());
			
		if (this.getCurQueueSize() == 0) {
			world.refresh();
			brain.refresh(world.getProblem());
			Coordinate nextNum = brain.nextNumber();
			if (nextNum == null) {
				// TODO: make this an official message and create a stuck and a
				// finish system
				if (brain.done()) {
					System.out.println(this.getLocalName()+": done");
					// endStats();
					return null;
				} else {
					Coordinate err = brain.searchErr();
					return err;
				}
			} else if (!asked.contains(nextNum) || !responded.contains(nextNum)
					|| !answered.contains(nextNum)) {
				waitTime = System.currentTimeMillis();
				incSelfEsteam();
				return nextNum;
			} else {
				System.out.println(this.getLocalName() + ": stuck");
				return null;
			}
		} else {
			return null;
		}
	}
	public void answer(Coordinate coordinate){
		Problem.edditProblem(coordinate);
		brain.print();
	}

	public void clearCoordinates() {
		asked.clear();
		answered.clear();
		responded.clear();
	}

	public void incQuestions() {
		others.incQuestion();
	}

	public boolean isChatty() {
		return personality.isChatty();
	}
	
	public boolean isShy() {
		boolean shy = personality.isShy();
		if(shy)
			System.out.print(this.getLocalName()+": SHY");
		return shy;
	}
	
	public AID getDistractable() {
		return others.getDistractable();
	}
	
	public boolean distract(){
		return personality.distract();
	}
}
