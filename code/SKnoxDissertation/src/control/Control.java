package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import agent.Knowledge.Personality;

import gui.ParamiterEddit;
import gui.ProblemEddit;
import gui.PupilEddit;
import gui.Setup;
import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

@SuppressWarnings("serial")
public class Control extends Agent {

	public static final int MAIN = 1, PARAM = 2, PROB = 3, PUPIL = 4, SUM = 5;

	private List<String> names = new ArrayList<String>(Arrays.asList("Bob",
			"Steve", "Alica", "Sky", "Erik", "Freya"));

	private Setup main;
	private ParamiterEddit paramEddit = new ParamiterEddit(this);
	private ProblemEddit probEddit = new ProblemEddit(this);
	private PupilEddit pupilEddit = new PupilEddit(this);

	private Map<String, Personality> pupils = new HashMap<String, Personality>();
	private List<String> pupilList = new ArrayList<String>();
	private int[][] problem = new int[9][9];

	@Override
	protected void setup() {
		for (String s : names) {
			pupils.put(s, new Personality(0));
		}
		Scanner scanner=null;
		try {
			 scanner = new Scanner(new FileInputStream("problem.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		while(scanner.hasNextLine()){
			int split = 0;
			for(String s: scanner.nextLine().split(",")){
				problem[split][count] = Integer.parseInt(s.trim());
				split++;
			}
			count++;
		}
		main = new Setup(this, pupils);
		main.toggleVisible();

		/*
		 * List<String> agents = new ArrayList<String>(); agents.add("Bob");
		 * agents.add("Steve"); agents.add("Alicia"); agents.add("test");
		 * 
		 * Object[] args = new Object[3];
		 * 
		 * Problem.setProblem(problem);
		 * 
		 * args[0] = problem; args[1] = agents; try { for (String a : agents){
		 * ((AgentController) getContainerController().createNewAgent(a,
		 * "agent.Interaction.Pupil", args)).start(); } } catch
		 * (StaleProxyException e) { e.printStackTrace(); }
		 */
	}

	public void pupilEddit(String name, Personality pers) {
		pupilEddit.setUp(name, pers);
		pupilEddit.setVisible();
	}

	public void changeAbility(String name) {
		paramEddit.setUpAbility(pupils.get(name), name);
		paramEddit.setVisible();
	}

	public void changePersonality(String name) {
		paramEddit.setUpPersonal(pupils.get(name), name);
		paramEddit.setVisible();
	}

	public void setPersonality(String name, Personality pers) {
		pupils.put(name, pers);
	}

	public void newName(String oldName, String newName) {
		if(oldName.equals(newName))
			return;
		pupils.put(newName, pupils.get(oldName));
		pupils.remove(oldName);
	}
	
	public void newProblem(int[][] problem){
		this.problem = problem;
	}

	public void changeView(int page) {
		switch (page) {
		case MAIN:
			main.refresh(pupils);
			main.setVisible();
			break;
		case PARAM:
			paramEddit.setVisible();
			break;
		case PROB:
			probEddit.loadProblem(problem);
			probEddit.setVisible();
			break;
		case PUPIL:
			pupilEddit.setVisible();
			break;
		case SUM:
			// TODO:summary page stuff
			break;
		}
	}
	
	public void start(int time, List<String> names){
		Object[] args = new Object[5];

		List<String> agents = names;
		Problem.setProblem(problem);
		
		args[0] = problem;
		args[1] = agents;
		args[2] = (Integer)time;
		args[3] = (Long)System.currentTimeMillis();
		try {
			for (String a : agents){
				((AgentController) getContainerController().createNewAgent(a,
						"agent.Interaction.Pupil", args)).start();
			}
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
}
