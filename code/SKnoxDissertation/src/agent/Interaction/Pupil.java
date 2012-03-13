package agent.Interaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import agent.Knowledge.OthersModel;
import agent.Knowledge.Personality;
import agent.Knowledge.WorldView;
import agent.Reasoning.DecisionMaking;

import sudoku.Coordinate;
import sudoku.Sudoku;

import control.Message;
import control.Problem;
import extras.Stats;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class Pupil extends Agent {

	boolean testing = false;
	boolean testingDisability = true;

	private static final int NORM = 1, DYSLEX = 2, DYSCAL = 3;

	Random r = new Random();
	private int waits = 0;
	private int times = 0;
	private int qAsked = 0;
	private int qAnswer = 0;
	private ArrayList<AID> usefullAnswers = new ArrayList<AID>();

	private Map<AID, Stats> peerData = new HashMap<AID, Stats>();

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

	// wait modifier that changes based on previous things
	private double waitMod = 0;
	private volatile int state = 4;
	private volatile int prevState = 4;
	private WorldView world;
	private Sudoku brain;
	private Personality personality;
	private OthersModel others;
	private List<Coordinate> asking = new ArrayList<Coordinate>();
	private List<Coordinate> asked = new ArrayList<Coordinate>();
	private List<Coordinate> responded = new ArrayList<Coordinate>();

	private Coordinate lastAsked = null;
	private AID friend = null;

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
				peerData.put(new AID(s, AID.ISLOCALNAME), new Stats(new AID(s,
						AID.ISLOCALNAME), this.getAID()));
			}
		}

		others = new OthersModel(peers);

		System.out.print(this.getAID().getLocalName() + ": ");

		if (!testingDisability) {
			personality = new Personality(0);
		} else if (this.getAID().getLocalName().equals("Alicia")) {
			// dyscalculic
			personality = new Personality(NORM);
		} else if (this.getAID().getLocalName().equals("Steve")) {
			// dyslexic
			personality = new Personality(DYSLEX);
		} else if (this.getAID().getLocalName().equals("Bob")) {
			// "normal"
			personality = new Personality(NORM);
		}

		// persons understanding of the world
		world = new WorldView((int[][]) args[0], peers,
				personality.isDyslexic());

		brain = new Sudoku(world.getProblem());

		this.addBehaviour(new Action(this));

		this.addBehaviour(new Reaction(this));

	}

	public void waits(Behaviour b) {
		if (personality.isDyslexic() && !testing) {
			int wait = r.nextInt(150) + 200;
			waits += wait;
			times++;
			doWait(wait);
			// b.block(wait);
		} else if (personality.isDyscalculic() && !testing) {
			int wait = r.nextInt(150) + 400;
			waits += wait;
			times++;
			doWait(wait);
			// b.block(wait);
		} else {
			int wait = r.nextInt(150) + 100;
			waits += wait;
			times++;
			doWait(wait);
			// b.block(wait);
		}
	}

	private void changeState(int state) {
		if (this.state != state) {
			prevState = this.state;
			this.state = state;
			if (!testing)
				doWait(r.nextInt(200));
		}
	}

	public void waiting(Behaviour b) {
		if (DecisionMaking.decide(DecisionMaking.WAIT, waitMod,
				personality.getOCEAN())) {
			waitMod += 0.01 * waitMod;
			System.out.println("System: waitMod update on "
					+ this.getAID().getLocalName());
		} else {
			// should I distract?
			if (DecisionMaking.decide(DecisionMaking.DISTRACT, waitMod,
					personality.getOCEAN())
					&& state != DISTRACTED
					&& prevState != DISTRACTED) {
				distract();
			} else if (prevState == ASKING) {
				askAgain();
			} else {
				waits(b);
				search(); // TODO: might be a problem hear with
							// intermitent states
			}
			// TODO: add stuff for interveaning on
			// arguing/chattering peers
		}
	}

	public void search() {
		if (this.getCurQueueSize() == 0) {
			world.refresh();
			brain.refresh(world.getProblem());
			Coordinate nextNum = brain.nextNumber();
			if (nextNum == null) {
				// TODO: make this an official message and create a stuck and a
				// finish system
				if (brain.done()) {
					endStats();
					this.blockingReceive();
				}else{
					Coordinate err = brain.searchErr();
					if(!err.equals(null)){
						ArrayList<AID> send = new ArrayList<AID>();
						for (AID a : world.getPeers()) {
							if (!a.equals(this.getAID()))
								send.add(a);
						}
						Messages.errorQuery(err, send, this);
					
					}
				}
			} else if (!asked.contains(nextNum)) {
				for (AID s : peerData.keySet()) {
					peerData.get(s).print();
				}

				asked.add(nextNum);
				asking.add(nextNum);
				ArrayList<AID> send = new ArrayList<AID>();
				for (AID a : world.getPeers()) {
					if (!a.equals(this.getAID()))
						send.add(a);
				}
				Messages.query(nextNum, send, this, others.focus());
				qAsked++;
			}
		}
	}

	public void answer() {

	}

	private void write(AID answer) {
		for (AID a : peerData.keySet()) {
			if (!a.equals(this.getAID()) && !a.equals(answer))
				peerData.get(a).write(false);
		}

		peerData.get(answer).write(true);
		others.incQuestionAnswered(answer);
		others.incQuestion();
	}

	public void messageHandel(Message res, Behaviour b) {
		peerData.get(res.getSender()).setLastCom(System.currentTimeMillis());
		ArrayList<AID> send = new ArrayList<AID>();
		for (AID a : world.getPeers())
			if (!a.equals(this.getAID()))
				send.add(a);
		switch (res.getPerformative()) {
		case Message.INFORM:
			peerData.get(res.getSender()).incAnswered();
			if (asking.contains(res.getCoordinate())) {
				if (res.getContent().contains("already")) {
					Messages.acknowledge(send, this);
					changeState(WAITING);
					asking.remove(res.getCoordinate());
				} else if (res.getContent().startsWith("Yes")) {
					if (asked.contains(res.getCoordinate())) {
						if (Problem.edditProblem(res.getCoordinate())) {
							peerData.get(res.getSender()).incMyAnswer();
							write(res.getSender());
							Messages.change(res.getCoordinate(), send, this);
							world.edditProblem(res.getCoordinate());
							brain.print();
						}
						world.refresh();
						brain.refresh(world.getProblem());
						changeState(WAITING);
						asking.remove(res.getCoordinate());
						usefullAnswers.add(res.getSender());
					}
				} else if (res.getContent().startsWith("No")) {
					// TODO:Stuff for errors
				} else if (res.getContent().contains("Changing")) {
					if (!asked.contains(res.getCoordinate()))
						asked.add(res.getCoordinate());

					if (!responded.contains(res.getCoordinate())
							&& !res.getContent().contains("No"))
						responded.add((res.getCoordinate()));
					brain.refresh(world.getProblem());
					break;
				}
			}
			if (res.getCoordinate() != null) {
				asked.add(res.getCoordinate());
				responded.add(res.getCoordinate());
			}
			break;
		case Message.QUERY_IF: //add stuff for querry of an error
			if (state != ARGUING || state != DISTRACTED) {
				Coordinate check = res.getCoordinate();
				if (check != null)
					if (!responded.contains(check)) {
						peerData.get(res.getSender()).incAsked();
						changeState(ANSWERING);
						this.waits(b);
						asked.add(check);
						world.refresh();
						brain.refresh(world.getProblem());
						if (world.check(check)) {
							if (DecisionMaking.decide(DecisionMaking.DISAGREE,
									0, personality.getOCEAN())) {
								responded.add(check);
								Messages.already(check, send, this);
							}
						} else if (brain.check(check)) {
							if (DecisionMaking.decide(DecisionMaking.AGREE, 0,
									personality.getOCEAN())) {
								responded.add(check);
								Messages.agree(check, send, this);
								qAnswer++;
							}
						} else {
							if (DecisionMaking.decide(DecisionMaking.DISAGREE,
									0, personality.getOCEAN())) {
								responded.add(check);
								Messages.disagree(check, send, this);
							}
						}
					}
				changeState(WAITING);
			}
			break;
		case Message.ARGUE:
			// at the moment the only argue is somone saying that there number
			// is correct

			/*
			 * if (prevState == ANSWERING) { // TODO: add stuff to handel
			 * disagreements } else { if
			 * (DecisionMaking.decide(DecisionMaking.AGRESIVE, 0,
			 * personality.getOCEAN())) { if
			 * (DecisionMaking.decide(DecisionMaking.ARGUE, 0,
			 * personality.getOCEAN())) { Messages.retaliate(send,
			 * res.getSender(), this); changeState(ARGUING); } else {
			 * Messages.angryReprisal(send, res.getSender(), this);
			 * changeState(ARGUING); } } else if
			 * (DecisionMaking.decide(DecisionMaking.TENTITIVE, 0,
			 * personality.getOCEAN())) { Messages.reprisal(send,
			 * res.getSender(), this); changeState(WAITING); } else { // TODO:
			 * figure out if want to use // probability for ignore
			 * changeState(WAITING); } }
			 */
			break;
		case Message.DISTRACT:
			if (DecisionMaking.decide(DecisionMaking.CHATTER, 0,
					personality.getOCEAN())) {
				Messages.wasteTime(send, res.getSender(), this);
				changeState(DISTRACTED);
			} else if (DecisionMaking.decide(DecisionMaking.TENTITIVE, 0,
					personality.getOCEAN())) {
				Messages.reprisal(send, res.getSender(), this);
				changeState(WAITING);
			} else if (DecisionMaking.decide(DecisionMaking.AGRESIVE, 0,
					personality.getOCEAN())) {
				Messages.angryReprisal(send, res.getSender(), this);
				changeState(WAITING);
			} else {
				changeState(WAITING);
			}
			break;
		case Message.FOCUS:
			if (!DecisionMaking.decide(DecisionMaking.IGNORE, 0,
					personality.getOCEAN())) {
				changeState(WAITING);
			}
			break;
		case Message.REPRISAL:
			if (DecisionMaking.decide(DecisionMaking.AGRESIVE, 0,
					personality.getOCEAN())) {
				Messages.retaliate(send, res.getSender(), this);
			} else {
				changeState(WAITING);
			}
			break;
		}
	}

	private void distract() {
		// TODO: method for distracting a peer
		// add somthing for finding who you're most likly to talk to
		ArrayList<AID> send = new ArrayList<AID>();
		for (AID a : world.getPeers())
			if (!a.equals(this.getAID()))
				send.add(a);

		Messages.distract(send, send.get(0), this);
	}

	public void pickAFight() {
		if (DecisionMaking.decide(DecisionMaking.AGRESIVE, waitMod,
				personality.getOCEAN())) {
			changeState(WAITING);
			waitMod = 0;
		} else {
			waitMod += 0.01 * waitMod;
			System.out.println("System: waitMod update on "
					+ this.getAID().getLocalName());
		}
	}

	public void askAgain() {
		ArrayList<AID> send = new ArrayList<AID>();
		for (AID a : world.getPeers())
			if (!a.equals(this.getAID()))
				send.add(a);

		Messages.query(lastAsked, send, this, others.focus());
		changeState(ASKING);
	}

	public void stopDistraction() {
		if (!DecisionMaking.decide(DecisionMaking.CHATTER, waitMod,
				personality.getOCEAN())) {
			ArrayList<AID> send = new ArrayList<AID>();
			for (AID a : world.getPeers())
				if (!a.equals(this.getAID()))
					send.add(a);

			if (DecisionMaking.decide(DecisionMaking.AGRESIVE, 0,
					personality.getOCEAN())) {
				Messages.angryReprisal(send, friend, this);
			} else if (DecisionMaking.decide(DecisionMaking.TENTITIVE, 0,
					personality.getOCEAN())) {
				Messages.reprisal(send, friend, this);
			}
			changeState(WAITING);
		}
	}

	public int getBehaviourState() {
		return state;
	}

	private void endStats() {
		int bob = 0, alic = 0, steve = 0;
		for (AID a : usefullAnswers) {
			if (a.getLocalName().equals("Bob")) {
				bob++;
			} else if (a.getLocalName().equals("Steve")) {
				steve++;
			} else if (a.getLocalName().equals("Alicia")) {
				alic++;
			}
		}

		System.out.println(this.getAID().getLocalName() + ": wait time: "
				+ waits + " Num:" + times + "\nNumasked: " + qAsked
				+ "\nNumanswer: " + qAnswer + "\nUsefull answers: Bob=" + bob
				+ " Alicia=" + alic + " Steve=" + steve);

		for (AID a : peerData.keySet()) {
			if (!a.equals(this.getAID()))
				peerData.get(a).close();
		}
	}
}
