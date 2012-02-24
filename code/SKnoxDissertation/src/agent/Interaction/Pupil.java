package agent.Interaction;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import agent.Knowledge.WorldView;

import sudoku.Coordinate;
import sudoku.Sudoku;

import control.Problem;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Pupil extends Agent {
	private WorldView world;
	private int crashCount = 0;
	private final int MAX_WAIT = 3;
	private Problem paperProblem;
	private Sudoku brain;
	private List<Coordinate> asked = new ArrayList<Coordinate>();
	private List<Coordinate> responded = new ArrayList<Coordinate>();

	@SuppressWarnings("unchecked")
	@Override
	protected void setup() {
		super.setup();

		Object[] args = getArguments(); // get arguments from startup

		List<AID> peers = new ArrayList<AID>();
		List<String> temp = (List<String>) args[1];
		for (String s : temp)
			peers.add(new AID(s, AID.ISLOCALNAME));

		// persons understanding of the world
		world = new WorldView((int[][]) args[0], peers);

		paperProblem = (Problem) args[2]; // problem as is on paper

		brain = new Sudoku(world.getProblem());
		search();

		/**
		 * respond behavior
		 */
		addBehaviour(new CyclicBehaviour(this) {

			@Override
			public void action() {
				ACLMessage res = receive();

				if (res != null)
					switch (res.getPerformative()) {
					case ACLMessage.INFORM:
						String ans = res.getContent();
						if (ans.contains("already")) {
							ArrayList<AID> send = new ArrayList<AID>();
							for (AID a : world.getPeers()) {
								if (!a.equals(this.myAgent.getAID())) {
									send.add(a);
								}
							}
							Messages.acknowledge(send, this.myAgent);
						} else if (ans.startsWith("Yes")) {
							String ears = res.getContent();
							Coordinate check = new Coordinate(
									Integer.valueOf(String.valueOf(ears
											.charAt(28))),
									Integer.valueOf(String.valueOf(ears
											.charAt(30))),
									Integer.valueOf(String.valueOf(ears
											.charAt(13))));
							if (asked.contains(check)) {
								try {
									if (paperProblem.edditProblem(check)) {
										ArrayList<AID> send = new ArrayList<AID>();
										for (AID a : world.getPeers()) {
											if (!a.equals(this.myAgent.getAID())) {
												send.add(a);
											}
										}
										Messages.change(check, send,
												this.myAgent);
										world.edditProblem(check);
										brain.print();
									}
									brain.refresh(world.getProblem());

								} catch (ConcurrentModificationException cme) {
									System.out.println("this");
								}
							}
						}
						search();

						break;
					case ACLMessage.QUERY_IF:
						String ears = res.getContent();
						Coordinate check = new Coordinate(
								Integer.valueOf(String.valueOf(ears.charAt(21))),
								Integer.valueOf(String.valueOf(ears.charAt(23))),
								Integer.valueOf(String.valueOf(ears.charAt(29))));
						if (!responded.contains(check)) {
							responded.add(check);
							asked.add(check);

							ArrayList<AID> send = new ArrayList<AID>();
							for (AID a : world.getPeers()) {
								if (!a.equals(this.myAgent.getAID())) {
									send.add(a);
								}
							}

							if (world.check(check)) {
								Messages.already(check, send, this.myAgent);
							} else if (brain.check(check)) {
								Messages.agree(check, send, this.myAgent);
							} else {
								Messages.disagree(check, send, this.myAgent);
							}
						}
						break;
					}
			}

		});

		addBehaviour(new TickerBehaviour(this, 6000) {
			@Override
			public void onTick() {
				if (crashCount == MAX_WAIT) {
					crashCount = 0;
					search();
				} else {
					crashCount++;
				}
			}
		});

	}

	private void search() {

		world.setProblem(paperProblem.getProblem());
		brain.refresh(world.getProblem());
		Coordinate nextNum = brain.nextNumber(asked);
		if (nextNum == null) {
			// TODO: make this an official message and create a stuck and a
			// finish system
			System.out.println(this.getAID().getLocalName()
					+ ": can't find anymore");
			this.blockingReceive();
		} else if (!asked.contains(nextNum)) {
			asked.add(nextNum);
			ArrayList<AID> send = new ArrayList<AID>();
			for (AID a : world.getPeers()) {
				if (!a.equals(this.getAID()))
					send.add(a);
			}
			Messages.query(nextNum, send, this);
		}
	}

}
