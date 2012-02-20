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
	private boolean asking = false;
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

		world = new WorldView((int[][]) args[0], peers); // persons
															// understanding of
															// the world
		paperProblem = (Problem) args[2]; // problem as is on paper

		brain = new Sudoku(world.getProblem());
		search();

		/**
		 * taken out atm as the loop doesn't realy work, atempting a one-shot
		 * solution addBehaviour(new CyclicBehaviour(this) {
		 * 
		 * @Override public void action() {
		 *           world.setProblem(paperProblem.getProblem());
		 * 
		 *           Coordinate nextNum = Sudoku.nextNumber(world.getProblem());
		 *           ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
		 *           List<AID> peers = world.getPeers(); for (AID a : peers) {
		 *           if(!a.equals(this.myAgent.getAID())) msg.addReceiver(a); }
		 *           msg.setContent("Would the number at (" + nextNum.getX() +
		 *           "," + nextNum.getY() + ") be " + nextNum.getVal() + "?");
		 *           System.out.println(this.myAgent.getLocalName()+": "+msg.
		 *           getContent()); send(msg);
		 * 
		 *           // TODO: fix this garbage confirmed = false; sucess =
		 *           false; while (!confirmed) block(); if (sucess) {
		 *           world.edditProblem(nextNum);
		 *           paperProblem.edditProblem(nextNum); } }
		 * 
		 *           });
		 **/

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
						if (asking) {
							String ans = res.getContent();
							Iterator<AID> i = res.getAllReplyTo();
							while (i.hasNext()) {
								if (i.next().equals(this.myAgent.getAID())) {
									if(ans.contains("already")){
										System.out.println(this.myAgent.getLocalName()+": woops");
									}else if (ans.startsWith("Yes")) {
										String ears = res.getContent();
										Coordinate check = new Coordinate(
												Integer.valueOf(String
														.valueOf(ears
																.charAt(28))),
												Integer.valueOf(String
														.valueOf(ears
																.charAt(30))),
												Integer.valueOf(String
														.valueOf(ears
																.charAt(13))));
										try {

											if (paperProblem
													.edditProblem(check)) {
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
									} else
										;
									// TODO: somthing for when it doesn't
									// work

									asking = false;

									search();
								}
							}
						}

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
							msg.addReplyTo(res.getSender());
							for (AID a : world.getPeers()) {
								if (!a.equals(this.myAgent.getAID())) {
									msg.addReceiver(a);
								}
							}

							if(world.check(check)){
								msg.setContent("Yes, " + check.getVal()
										+ " is already at (" + check.getX() + ","
										+ check.getY() + ").");
							}else if (brain.check(check)) {
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

		addBehaviour(new TickerBehaviour(this, 60000) {
			@Override
			public void onTick() {
				if (!asking || crashCount == MAX_WAIT) {
					asking = false;
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
			System.out.println("Finnished!");
			this.takeDown();
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
			asking = true;
		}
	}

}
