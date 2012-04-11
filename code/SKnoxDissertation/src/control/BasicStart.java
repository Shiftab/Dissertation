package control;

import gui.Setup;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * basic init class for starting agents
 * 
 * @author shiftab
 *
 */
@SuppressWarnings("serial")
public class BasicStart extends Agent {

	/**
	 * standard method for seting up an agent
	 */
	@Override 
	protected void setup() {
		Object[] args = new Object[3];

		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };

		List<String> agents = new ArrayList<String>();
		agents.add("Bob");
		agents.add("Steve");
		agents.add("Alicia");
		agents.add("test");
		Problem.setProblem(problem);
		
		args[0] = problem;
		args[1] = agents;
		args[2] = 5;
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
