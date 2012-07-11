package gui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

/**
 * class to handel the running screen for the simulation
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class Loading extends JPanel {

	JProgressBar pbTime = new JProgressBar();
	private final JLabel lbTimeD = new JLabel("100");
	Graph panel = new Graph();

	/**
	 * Create the panel.
	 */
	public Loading() {

		setOpaque(false);
		JLabel lblTime = new JLabel("Time Left: ");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, lbTimeD, 0,
				SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, lbTimeD, 6,
				SpringLayout.EAST, lblTime);
		springLayout.putConstraint(SpringLayout.EAST, lbTimeD, 51,
				SpringLayout.EAST, lblTime);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 175,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, pbTime, 102,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pbTime, -6,
				SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 472,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTime, -6,
				SpringLayout.NORTH, pbTime);
		springLayout.putConstraint(SpringLayout.WEST, panel, 258,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -222,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 754,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, pbTime, 323,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, pbTime, 688,
				SpringLayout.WEST, this);
		setLayout(springLayout);
		add(lblTime);
		add(pbTime);
		add(lbTimeD);
		add(panel);

		this.setVisible(true);
	}

	/**
	 * method to set the names of pupils for the graph
	 * 
	 * @param names
	 */
	public void setNames(List<String> names) {
		panel.setNames(names);
	}

	/**
	 * method to update the graph on the loading screen
	 * 
	 * @param name
	 * @param time
	 * @param input
	 */
	public void updateGraph(String name, double time, double input) {
		panel.updateChart();
		panel.updateSeries(name, time, input);
		panel.display();
		this.repaint();
	}

	/**
	 * method to return the graph
	 * 
	 * @return
	 */
	public Graph getGraphData() {
		return panel;
	}

	/**
	 * method to update the time value on the loading screen
	 * 
	 * @param time
	 */
	public void updateTime(double time) {
		pbTime.setValue((int) time);
		lbTimeD.setText(String.valueOf(((int) time)) + "%");
	}

	/**
	 * method to update the percentage done value on the loading screen
	 * 
	 * @param problem
	 * @deprecated
	 */
	public void updateProb(double problem) {
	}

	/**
	 * method to clear the graphs
	 */
	public void clearGraphs() {
		panel.clearSeries();
	}
}
