package agent.Interaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import control.Control;
import control.Problem;
import extras.Stats;

import agent.Knowledge.OthersModel;
import agent.Knowledge.Personality;
import agent.Knowledge.WorldView;

import sudoku.Coordinate;
import sudoku.Sudoku;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 * main agent class for the simulation
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class Pupil extends Agent {

	// states
	public static final int ARGUING = 3;
	public static final int WORKING = 4;
	public static final int DISTRACTED = 5;
	public static final int SHY = 6;

	// private final boolean testingDisability = true;

	// private final int DYSCAL = 1, DYSLEX = 2, NORM = 3;

	private long startTime;
	private int timeLimit;
	private WorldView world;
	private Sudoku brain;
	private Personality personality;
	private OthersModel others;
	private Stats stats;
	private Control parent;
	private AID currentFocus = null;

	private Coordinate asking = null;
	private List<Coordinate> asked = new ArrayList<Coordinate>(47);
	private List<Coordinate> responded = new ArrayList<Coordinate>(47);
	private List<Coordinate> answered = new ArrayList<Coordinate>(47);
	private int state = WORKING;
	private long waitTime = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.Agent#doActivate()
	 */
	@Override
	public void doActivate() {
		// used to clear the values on restart
		currentFocus = null;
		asking = null;
		timeLimit = 0;
		startTime = 0;
		world = null;
		brain = null;
		personality = null;
		others = null;
		stats = null;
		parent = null;
		asked.clear();
		responded.clear();
		answered.clear();
		state = WORKING;
		waitTime = 0;
		super.doActivate();
	}

	@Override
	protected void setup() {
		// make sure the values are clear
		currentFocus = null;
		asking = null;
		timeLimit = 0;
		startTime = 0;
		world = null;
		brain = null;
		personality = null;
		others = null;
		stats = null;
		parent = null;
		asked.clear();
		responded.clear();
		answered.clear();
		state = WORKING;
		waitTime = 0;

		Object[] args = getArguments(); // get arguments from startup

		List<AID> peers = new ArrayList<AID>();
		@SuppressWarnings("unchecked")
		List<String> temp = ((List<String>) args[1]);
		List<String> peersStr = new ArrayList<String>();
		for (String s : temp) {
			if (!s.equals(this.getLocalName())) { // add peers
				peers.add(new AID(s, AID.ISLOCALNAME));
				peersStr.add(s);
			}
		}

		personality = (Personality) args[0];
		timeLimit = (Integer) args[2];
		startTime = (Long) args[3];
		parent = (Control) args[4];

		// view of other pupils
		others = new OthersModel(peers);

		// persons understanding of the world
		world = new WorldView(peers, personality.isDyslexic());

		// sudoku solving
		brain = new Sudoku(world.getProblem());

		// logging
		stats = new Stats(this.getAID(), personality.getOCEAN(), peersStr);

		personality.resetSelfEsteam();

		this.addBehaviour(new Action(this));

		this.addBehaviour(new Reaction(this));
	}

	/**
	 * method to set the state of a peer
	 * 
	 * @param sender
	 * @param visWorking
	 */
	public void setPeerActionState(AID sender, int visWorking) {
		others.setVisState(sender, visWorking);
	}

	/**
	 * method to controle the change of state for a pupil
	 * 
	 * @param state
	 */
	public void setActionState(int state) {
		// starting events
		switch (state) {
		case Pupil.SHY:
			/*
			 * if the previous state was distracted this means that there was a
			 * failed distraction attempt
			 */
			if (this.state != DISTRACTED) {
				stats.setStartShy(); // new introversion
				System.out
						.println(this.getLocalName()
								+ ": SHY:"
								+ (100 - ((((startTime + (timeLimit * 6000)) - System
										.currentTimeMillis()) / 6000.0) / timeLimit) * 100));
			}

			break;

		case Pupil.DISTRACTED:
			stats.setStartDistractions();
			break;
		}

		// stoping events
		switch (this.state) {
		case Pupil.SHY:

			if (state != Pupil.SHY) {
				stats.stopShy();
				System.out
						.println(this.getLocalName()
								+ "STOP SHY :"
								+ (100 - ((((startTime + (timeLimit * 6000)) - System
										.currentTimeMillis()) / 6000.0) / timeLimit) * 100));
			}

			break;

		case Pupil.DISTRACTED:
			if (state != Pupil.DISTRACTED)
				stats.stopDistract();
			break;
		}

		// change state
		this.state = state;
	}

	/**
	 * method to return the current state of the pupil
	 * 
	 * @return
	 */
	public int getActionState() {
		return state;
	}

	/**
	 * method to log new distraction attempt statistics
	 */
	public void setDistract() {
		stats.setStartDistractions();
		stats.addDistract();
	}

	/**
	 * method to log a new distraction conversation
	 * 
	 * @param name
	 */
	public void setDistracted(String name) {
		stats.addDistracted(name);
	}

	// -------------State------------

	// asking

	/**
	 * method return the current query value
	 * 
	 * @return
	 */
	public Coordinate getAsking() {
		return asking;
	}

	/**
	 * method to set the current query value
	 * 
	 * @param ans
	 */
	public void setAsking(Coordinate ans) {
		// set waitTime
		this.asking = ans;
		this.asked.add(ans);
		waitTime = System.currentTimeMillis();

	}

	// asked

	/**
	 * method to check if already asked a query
	 * 
	 * @param ans
	 * @return
	 */
	public boolean asked(Coordinate ans) {
		return asked.contains(ans);
	}

	// answered

	/**
	 * method to deleate a previously set query in the problem copy. used for
	 * error recovery
	 * 
	 * @param coordinate
	 * @deprecated
	 */
	public void newNumber(Coordinate coordinate) {
		asking = null;
		coordinate = new Coordinate(coordinate.getX(), coordinate.getY(), 0);
		Problem.edditProblem(coordinate);
	}

	/**
	 * method to log that somone has changed a value on the paper problem
	 * 
	 * @param coordinate
	 */
	public void setAnswered(Coordinate coordinate) {
		world.refresh();
		brain.refresh(world.getProblem());
		responded.add(coordinate);
		asking = null;
		answered.add(coordinate);
	}

	/**
	 * method to check if a query has been answered
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean isAnswered(Coordinate coordinate) {
		return answered.contains(coordinate);
	}

	// responded

	/**
	 * method to check if this pupil has answered this query befoure
	 * 
	 * @param coordinate
	 * @return
	 */
	public boolean haveResponded(Coordinate coordinate) {
		return responded.contains(coordinate);
	}

	/**
	 * method to add a query to the responded list
	 * 
	 * @param coordinate
	 */
	public void responded(Coordinate coordinate) {
		responded.add(coordinate);
	}

	// ------------Answers/Questions---------------

	/**
	 * method to pause an agent, used to slow down a pupil to simulate slower
	 * processing
	 */
	public void pauseAction(Behaviour b) {
		checkDone();
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());
	}

	/**
	 * method to check a query to find if it is correct
	 * 
	 * @param coordinate
	 * @param b
	 * @return
	 */
	public boolean checkAnswer(Coordinate coordinate, Behaviour b) {
		System.out.println(this.getLocalName() + ":" + personality.getSpeed());
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());
		return brain.check(coordinate);
	}

	/**
	 * method to check a query to find if it is an error
	 * 
	 * @param coordinate
	 * @param b
	 * @return
	 * @deprecated
	 */
	public boolean checkErr(Coordinate coordinate, Behaviour b) {
		System.out.println(this.getLocalName() + ":" + personality.getSpeed());
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());
		world.refresh();
		brain.refresh(world.getProblem());
		if (!world.check(coordinate))
			return false;

		return brain.checkErr(coordinate);
	}

	// ------------check coords-----------------

	/**
	 * method to reset the self esteem of a pupil
	 * 
	 */
	public void resetSelfEsteam() {
		personality.resetSelfEsteam();
	}

	/**
	 * method to recheck the central copy of the problem
	 */
	public void refreshWorld() {
		world.refresh();
		brain.refresh(world.getProblem());
	}

	/**
	 * method to decrease the pupils self esteem
	 */
	public void decSelfEsteam() {
		personality.decSelfEsteam();
	}

	/**
	 * method used to decrease focus on a pupil when focus was used like self
	 * esteam
	 * 
	 * @param sender
	 * @deprecated
	 */
	public void lowerFocus(AID sender) {
		others.lowerFocus(sender);
	}

	/**
	 * method used to increase the pupils self esteem
	 */
	public void incSelfEsteam() {
		personality.incSelfEsteam();
	}

	/**
	 * method used to increase focus on a pupil when focus was used like self
	 * esteem.
	 * 
	 * repurposed to increase the number of pupils' questions answered by the
	 * sender for the trust system
	 * 
	 * @param sender
	 */
	public void incFocus(AID sender) {
		others.incFocus(sender);
	}

	// --------------selfEsteam/focus-------------
	/**
	 * method used to get the states of the pupils peers
	 * 
	 * @return
	 */
	public Map<AID, Integer> getPeersStates() {
		return others.getPeers();
	}

	/**
	 * method used to get the peers of a pupil
	 * 
	 * @return
	 */
	public Set<AID> getPeers() {
		return others.getPeers().keySet();
	}

	// -------------others----------------

	/**
	 * method used to get the distracted peers of a pupil
	 * 
	 * @return
	 */
	public Set<AID> getDistracted() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.DISTRACTED)
				ans.add(a);
		}
		return ans;
	}

	/**
	 * method for returning the arguing peers of a pupil
	 * 
	 * @return
	 */
	public Set<AID> getArguing() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.ARGUING)
				ans.add(a);
		}
		return ans;
	}

	/**
	 * method for returning the introverted peers of a pupil
	 * 
	 * @return
	 */
	public Set<AID> getShy() {
		Set<AID> ans = new HashSet<AID>();
		Map<AID, Integer> peers = getPeersStates();
		for (AID a : peers.keySet()) {
			if (peers.get(a) == Pupil.SHY)
				ans.add(a);
		}
		return ans;
	}

	/**
	 * method used to return the focus of a pupil if there is one
	 * 
	 * @return
	 */
	public AID getFocus() {
		AID ans = others.focus();
		if (ans != null) {
			if (currentFocus == null) { // change the stats on focus
				currentFocus = ans;
				stats.focus(ans.getLocalName());
			} else if (currentFocus != ans) {
				stats.focus(currentFocus.getLocalName());
				currentFocus = ans;
				stats.focus(ans.getLocalName());
			}
		}
		return ans;
	}

	// --------------------self---------------------

	/**
	 * return the time since the last interaction of a pupil
	 * 
	 * @return
	 */
	public long getWaitTime() {
		return System.currentTimeMillis() - waitTime;
	}

	/**
	 * method to set a log for last pupil interaction
	 */
	public void setWaitTime() {
		waitTime = System.currentTimeMillis();
	}

	/**
	 * method to return if the simulation should finnish
	 */
	public void checkDone() {
		if (brain.done() // problem done
				|| ((startTime + (timeLimit * 6000)) - System
						.currentTimeMillis()) < 0) { // time limit up
			System.out.println(this.getLocalName() + ": done");
			stats.print();
			parent.stopPupil(this.getLocalName(), stats, this);
			// endStats();
			while (true)
				doWait();
		}
	}

	/**
	 * method to search for the next answer to the problem
	 * 
	 * @param b
	 * @return
	 */
	public Coordinate search(Behaviour b) {
		doWait(personality.getSpeed());
		b.block(personality.getSpeed());

		if (this.getCurQueueSize() == 0) { // make sure there isn't any querys
											// pending
			world.refresh();
			brain.refresh(world.getProblem());
			Coordinate nextNum = brain.nextNumber();
			if (nextNum == null) {
				checkDone();
				return null;
				/*
				 * // deprecated section from when errors were used if
				 * (brain.done()) { System.out.println(this.getLocalName() +
				 * ": done"); stats.print();
				 * parent.stopPupil(this.getLocalName(), stats, this); //
				 * endStats(); return null; } else { Coordinate err =
				 * brain.searchErr(); return err; }
				 */
			} else if (!asked.contains(nextNum) || !responded.contains(nextNum)
					|| !answered.contains(nextNum)) {
				waitTime = System.currentTimeMillis();
				incSelfEsteam();
				return nextNum;
			} else { // rare state
				System.out.println(this.getLocalName() + ": stuck");
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * method to add a new answer to the central problem
	 * 
	 * @param coordinate
	 */
	public void answer(Coordinate coordinate) {
		System.out.println("timeleft:"
				+ ((startTime + (timeLimit * 6000)) - System
						.currentTimeMillis()));
		Problem.edditProblem(coordinate);
		// parent.updateLoadingProb();
		brain.print();
	}

	/**
	 * method to wipe the memory of the previous querys used in rare cases were
	 * refresh erros cause issues
	 */
	public void clearCoordinates() {
		asked.clear();
		answered.clear();
		responded.clear();
	}

	/**
	 * method to increase the questions asked stat for loging and the trust
	 * system
	 */
	public void incQuestions() {
		others.incQuestion();
		stats.incQuestions();
	}

	/**
	 * method to update the graph on the loading page
	 */
	public void updateGraph() {
		parent.updateGraph(this.getAID().getLocalName(), stats);
	}

	/**
	 * method to return if the pupil is likly to be distracted
	 * 
	 * @return
	 */
	public boolean isChatty() {
		return personality.isChatty();
	}

	/**
	 * method to return if the pupil should introvert
	 * 
	 * @return
	 */
	public boolean isShy() {
		boolean shy = personality.isShy(this.getLocalName());
		if (shy)
			System.out.print(this.getLocalName() + ": SHY");
		return shy;
	}

	/**
	 * method to return a likly target for distraction attempt
	 * 
	 * @return
	 */
	public AID getDistractable() {
		return others.getDistractable();
	}

	/**
	 * method to return if a pupil should attempt to distract
	 * 
	 * @return
	 */
	public boolean distract() {
		return personality.distract();
	}

	/**
	 * method to increase the asked questions stat in the logs
	 */
	public void incAskedStats() {
		stats.incAsked();
	}

	/**
	 * method to incrase the answers stat in the logs
	 */
	public void incAnsweredStats() {
		stats.incAnswered();
	}

	/**
	 * method to increase the total question stat in the log and to update the
	 * graph on the loading page
	 */
	public void incQuestionsStats() {
		stats.incQuestions();
		parent.updateGraph(this.getAID().getLocalName(), stats);
	}

	/**
	 * method to increase the questions missed due to introversion
	 */
	public void incShyMissedStats() {
		stats.incShyMissed();
	}

	/**
	 * method to increase the questions missed due to disraction
	 */
	public void incDistractionsStats() {
		stats.incDistractions();
	}

	/**
	 * method to stop the agent
	 */
	public void stop() {
		this.clearCoordinates();
		parent.stopPupil(this.getLocalName(), stats, this);
		this.doWait();
	}

	/**
	 * method to check if the simulations timelimit is up
	 * 
	 * @return
	 */
	public boolean timeUp() {
		return (startTime + (timeLimit * 6000)) - System.currentTimeMillis() <= 0;
	}
}
