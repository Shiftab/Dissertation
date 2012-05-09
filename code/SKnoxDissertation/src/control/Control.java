package control;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;

import extras.Stats;

import agent.Interaction.Pupil;
import agent.Knowledge.Personality;

import gui.FinalScreen;
import gui.Graph;
import gui.Loading;
import gui.ParamiterEddit;
import gui.ProblemEddit;
import gui.PupilEddit;
import gui.PupilStats;
import gui.Setup;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

@SuppressWarnings("serial")
public class Control extends Agent {

	public static final int MAIN = 1, PARAM = 2, PROB = 3, PUPIL = 4, SUM = 5;

	private List<String> names = new ArrayList<String>(Arrays.asList("Bob",
			"Steve", "Alica", "Sky", "Freya"));

	private Setup main;
	private JFrame frame = new JFrame();
	private ParamiterEddit paramEddit = new ParamiterEddit(this);
	private ProblemEddit probEddit = new ProblemEddit(this);
	private PupilEddit pupilEddit = new PupilEddit(this);
	private FinalScreen summary = new FinalScreen(this);
	private Loading loading = new Loading();
	private Graph graph = new Graph();
	private PupilStats stats = new PupilStats(this);

	private Map<String, Personality> pupils = new HashMap<String, Personality>();
	private Map<String, Stats> endStats = new HashMap<String, Stats>();
	private List<String> pupilList = new ArrayList<String>();
	private List<Pupil> agents = new ArrayList<Pupil>();
	private int[][] problem = new int[9][9];
	private long startTime = 0;
	private int timeLimit = 0;

	private final String PARAM_S = "Parameter Eddit", PROB_S = "Problem Eddit",
			PUPIL_S = "Pupil Eddit", SUM_S = "Summary", LOAD_S = "Loading",
			MAIN_S = "Main", PUPILSUM_S = "Pupil Summary";

	private JPanel cards = new JPanel(new CardLayout());
	private CardLayout cl = (CardLayout) cards.getLayout();

	private List<AgentController> ac = new ArrayList<AgentController>();

	@Override
	protected void setup() {
		URL url = getClass().getResource("/image.png");

		ImageIcon imh = new ImageIcon(url);
		frame.setSize(imh.getIconWidth(), imh.getIconHeight());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				URL url = getClass().getResource("/image.png");
				Image img = new ImageIcon(url).getImage();
				Dimension size = new Dimension(img.getWidth(null), img
						.getHeight(null));
				setPreferredSize(size);
				setMinimumSize(size);
				setMaximumSize(size);
				setSize(size);
				setLayout(null);
				g.drawImage(img, 0, 0, null);
			}
		});

		cards.add(stats, PUPILSUM_S);
		cards.add(paramEddit, PARAM_S);
		cards.add(probEddit, PROB_S);
		cards.add(pupilEddit, PUPIL_S);
		cards.add(loading, LOAD_S);
		cards.add(summary, SUM_S);

		graph.clearSeries();

		for (String s : names) {
			pupils.put(s, new Personality(0));
		}
		loadDefault();
		main = new Setup(this, pupils, frame.getSize());
		cards.add(main, MAIN_S);
		cards.setSize(frame.getSize());
		cards.setPreferredSize(frame.getSize());
		cards.setMaximumSize(frame.getSize());
		cards.setMinimumSize(frame.getSize());
		cards.setOpaque(false);
		main.setOpaque(false);
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

	private void loadDefault() {
		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };
		this.problem = problem;
	}

	public Map<String, Personality> getPupils() {
		return pupils;
	}

	public void pupilEddit(String name, Personality pers) {
		pupilEddit.setUp(name, pers);
		cl.show(cards, PUPIL_S);
		frame.repaint();
	}

	public void changeAbility(String name) {
		paramEddit.setUpAbility(pupils.get(name), name);
		cl.show(cards, PARAM_S);
		frame.repaint();
	}

	public void changePersonality(String name) {
		paramEddit.setUpPersonal(pupils.get(name), name);
		cl.show(cards, PARAM_S);
		frame.repaint();
	}

	public void setPersonality(String name, Personality pers) {
		pupils.put(name, pers);
	}

	public void replasePersonality(String oldName, String name, Personality pers) {
		pupils.remove(oldName);
		pupils.put(name, pers);
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
			cl.show(cards, SUM_S);
			break;
		}
	}

	public void start(int time, List<String> names) {
		pupilList = names;
		Problem.setProblem(problem);
		timeLimit = time;
		startTime = System.currentTimeMillis();
		ac.clear();
		try {
			for (String a : pupilList) {
				Object[] args = new Object[5];
				args[0] = pupils.get(a);
				args[1] = pupilList;
				args[2] = timeLimit;
				args[3] = startTime;
				args[4] = this;
				ac.add(getContainerController().createNewAgent(a,
						"agent.Interaction.Pupil", args));
			}

			for (AgentController a : ac)
				a.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		loading.clearGraphs();
		loading.setNames(names);
		cl.show(cards, LOAD_S);
		frame.repaint();
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

	public void updateGraph(String name, Stats stats) {
		loading.updateGraph(name,
				100 - ((((startTime + (timeLimit * 6000)) - System
						.currentTimeMillis()) / 6000.0) / timeLimit) * 100,
				(((stats.getAsked() + stats.getAnswered()) / ((stats
						.getQuestions()) * 2.0)) * 100.0));
	}

	public void resetProblem() {
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
		summary.display(endStats, Problem.amountDone(), endTime - startTime,
				loading.getGraphData());
		cl.show(cards, SUM_S);
	}

	public void pupilSummery(String name, Stats pupilStats, XYSeries series) {
		stats.setUp(name, pupilStats, series, startTime, timeLimit);
		cl.show(cards, PUPILSUM_S);
	}

	public Component getFrame() {
		return frame;
	}
}
