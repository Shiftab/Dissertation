package agent;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import test.Coordinate;
import test.Sudoku;

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
							System.out.println(this.myAgent.getLocalName()
									+ ": woops");
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
										System.out.println(this.myAgent
												.getLocalName()
												+ ": Changing ("
												+ check.getX()
												+ ","
												+ check.getY()
												+ ") to "
												+ check.getVal());
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
							ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
							for (AID a : world.getPeers()) {
								if (!a.equals(this.myAgent.getAID())) {
									msg.addReceiver(a);
								}
							}

							if (world.check(check)) {
								msg.setContent("Yes, " + check.getVal()
										+ " is already at (" + check.getX()
										+ "," + check.getY() + ").");
							} else if (brain.check(check)) {
								msg.setContent("Yes, I think " + check.getVal()
										+ " would be at (" + check.getX() + ","
										+ check.getY() + ").");
							} else {
								msg.setContent("No, I don't think "
										+ check.getVal() + " would be at ("
										+ check.getX() + "," + check.getY()
										+ ").");
							}
							System.out.println(this.myAgent.getLocalName()
									+ ": " + msg.getContent());
							send(msg);
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
			System.out.println(this.getAID().getLocalName()
					+ ": can't find anymore");
			this.blockingReceive();
		} else if (!asked.contains(nextNum)) {
			asked.add(nextNum);
			ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
			List<AID> peers = world.getPeers();
			for (AID a : peers) {
				if (!a.equals(this.getAID()))
					msg.addReceiver(a);
			}
			msg.setContent("Would the number at (" + nextNum.getX() + ","
					+ nextNum.getY() + ") be " + nextNum.getVal() + "?");
			System.out.println(this.getLocalName() + ": " + msg.getContent());
			send(msg);
		}
	}

}
