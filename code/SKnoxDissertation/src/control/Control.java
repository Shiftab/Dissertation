package control;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import extras.Stats;

import agent.Interaction.Pupil;
import agent.Knowledge.Personality;

import gui.FinalScreen;
import gui.Loading;
import gui.ParamiterEddit;
import gui.ProblemEddit;
import gui.PupilEddit;
import gui.Setup;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

@SuppressWarnings("serial")
public class Control extends Agent {

	public static final int MAIN = 1, PARAM = 2, PROB = 3, PUPIL = 4, SUM = 5;

	private List<String> names = new ArrayList<String>(Arrays.asList("Bob",
			"Steve", "Alica", "Sky", "Erik", "Freya"));

	private Setup main;
	private JFrame frame = new JFrame();
	private ParamiterEddit paramEddit = new ParamiterEddit(this);
	private ProblemEddit probEddit = new ProblemEddit(this);
	private PupilEddit pupilEddit = new PupilEddit(this);
	private FinalScreen summary = new FinalScreen(this);
	private Loading loading = new Loading();

	private Map<String, Personality> pupils = new HashMap<String, Personality>();
	private Map<String, Stats> endStats = new HashMap<String, Stats>();
	private List<String> pupilList = new ArrayList<String>();
	private List<Pupil> agents = new ArrayList<Pupil>();
	private int[][] problem = new int[9][9];
	private long startTime = 0;
	private int timeLimit = 0;
	
	private final String PARAM_S="Parameter Eddit", PROB_S="Problem Eddit", PUPIL_S="Pupil Eddit", SUM_S="Summary", LOAD_S="Loading", MAIN_S="Main";
	
	private JPanel cards = new JPanel(new CardLayout());
	private CardLayout cl = (CardLayout) cards.getLayout();

	private List<AgentController> ac = new ArrayList<AgentController>();

	@Override
	protected void setup() {
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		cards.add(paramEddit, PARAM_S);
		cards.add(probEddit, PROB_S);
		cards.add(pupilEddit, PUPIL_S);
		cards.add(summary, SUM_S);
		cards.add(loading, LOAD_S);
		
		for (String s : names) {
			pupils.put(s, new Personality(0));
		}
		loadDefault();
		main = new Setup(this, pupils);
		cards.add(main, MAIN_S);
		frame.add(cards);
		cl.show(cards, MAIN_S);

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
	
	private void loadDefault(){
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("res/problem.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		while (scanner.hasNextLine()) {
			int split = 0;
			for (String s : scanner.nextLine().split(",")) {
				problem[split][count] = Integer.parseInt(s.trim());
				split++;
			}
			count++;
		}
	}
	
	public Map<String, Personality> getPupils(){
		return pupils;
	}

	public void pupilEddit(String name, Personality pers) {
		pupilEddit.setUp(name, pers);
		cl.show(cards, PUPIL_S);
	}

	public void changeAbility(String name) {
		paramEddit.setUpAbility(pupils.get(name), name);
		cl.show(cards, PARAM_S);
	}

	public void changePersonality(String name) {
		paramEddit.setUpPersonal(pupils.get(name), name);
		cl.show(cards, PARAM_S);
	}

	public void setPersonality(String name, Personality pers) {
		pupils.put(name, pers);
	}
	
	public void replasePersonality(String oldName, String name, Personality pers){
		pupils.remove(oldName);
		pupils.put(name,  pers);
	}

	public void newName(String oldName, String newName) {
		if (oldName.equals(newName))
			return;
		pupils.put(newName, pupils.get(oldName));
		pupils.remove(oldName);
	}

	public void newProblem(int[][] problem) {
		this.problem = problem;
	}

	public void changeView(int page) {
		switch (page) {
		case MAIN:
			main.refresh(pupils);
			cl.show(cards, MAIN_S);
			break;
		case PARAM:
			cl.show(cards, PARAM_S);
			break;
		case PROB:
			probEddit.loadProblem(problem);
			cl.show(cards, PROB_S);
			break;
		case PUPIL:
			cl.show(cards, PUPIL_S);
			break;
		case SUM:
			// TODO:summary page stuff
			break;
		}
	}

	public void start(int time, List<String> names) {
		Object[] args = new Object[5];

		pupilList = names;
		Problem.setProblem(problem);
		timeLimit = time;
		args[0] = problem;
		args[1] = pupilList;
		args[2] = timeLimit;
		args[3] = System.currentTimeMillis();
		args[4] = this;
		try {
			for (String a : pupilList) {
				ac.add(getContainerController()
						.createNewAgent(a, "agent.Interaction.Pupil", args));
			}

			for (AgentController a : ac)
				a.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		startTime = (Long) args[3];
		cl.show(cards, LOAD_S);
		this.addBehaviour(new CyclicBehaviour(this) {
			long lastTime = startTime;

			@Override
			public void action() {
				if (System.currentTimeMillis() - lastTime > 1000) {
					lastTime = System.currentTimeMillis();
					loading.updateTime(((((startTime + (timeLimit * 6000)) - System
							.currentTimeMillis()) / 6000.0) / timeLimit) * 100);
				}
			}
		});
	}

	public void updateLoadingTime(double time) {
		loading.updateTime(time);
	}
	
	public void resetProblem(){
		loadDefault();
	}

	public void updateLoadingProb() {
		loading.updateProb(Problem.amountDone());
	}

	public void stopPupil(String name, Stats stats, Pupil pupil) {
		agents.add(pupil);
		endStats.put(name, stats);
		if (endStats.size() == pupilList.size()) {
			stop(System.currentTimeMillis());
		}
	}

	public void stop(long endTime) {
		for (AgentController a : ac)
			try {
				a.kill();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		summary.display(endStats, Problem.amountDone(), endTime - startTime);
		cl.show(cards, SUM_S);
	}

	public Component getFrame() {
		return frame;
	}
}
