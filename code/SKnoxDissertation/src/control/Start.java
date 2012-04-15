package control;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Profile prof = new ProfileImpl(null, 5000, null);
		AgentContainer mainContainer = jade.core.Runtime.instance().createMainContainer(prof);
		try {
			mainContainer.createNewAgent("Control", "control.Control",null).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
