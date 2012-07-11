package control;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

/**
 * class to start up the system
 * @author Steven Knox
 *
 */
public class Start {

	/**
	 * main method for the system
	 * @param args
	 */
	public static void main(String[] args) {
		Profile prof = new ProfileImpl(null, 5000, null);
		AgentContainer mainContainer = jade.core.Runtime.instance()
				.createMainContainer(prof);
		try {
			mainContainer.createNewAgent("Control", "control.Control", null)
					.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

}
