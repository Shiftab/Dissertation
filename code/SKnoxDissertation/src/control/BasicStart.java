package control;

import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class BasicStart extends Agent {

	protected void setup() {

		Object[] args = new Object[3];

		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };

		for (int y = 0; y < 9; y++){
			for (int x = 0; x < 9; x++)
				System.out.print(problem[x][y]);
			System.out.print("\n");
		}
		List<String> agents = new ArrayList<String>();
		agents.add("Bob");
		agents.add("Alice");
		agents.add("Alicia");

		Problem prob = new Problem(problem);

		args[0] = problem;
		args[1] = agents;
		args[2] = prob;
		try {
			for (String a : agents)
				((AgentController) getContainerController().createNewAgent(a,
						"agent.Pupil", args)).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
