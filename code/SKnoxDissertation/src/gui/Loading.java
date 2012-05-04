package gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.jfree.data.xy.XYSeries;

public class Loading extends JPanel {

	JProgressBar pbTime= new JProgressBar(), pbProb= new JProgressBar();
	private final JLabel lbTimeD = new JLabel("100");
	private final JLabel lbProbD = new JLabel("0");
	Graph panel = new Graph();
	
	/**
	 * Create the panel.
	 */
	public Loading() {
		
		JLabel lblTime = new JLabel("Time");
		
		JLabel lblProblem = new JLabel("Problem");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, pbProb, 6, SpringLayout.SOUTH, lblProblem);
		springLayout.putConstraint(SpringLayout.SOUTH, pbProb, -287, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lbProbD, 6, SpringLayout.SOUTH, pbProb);
		springLayout.putConstraint(SpringLayout.WEST, lbProbD, 0, SpringLayout.WEST, lbTimeD);
		springLayout.putConstraint(SpringLayout.SOUTH, lbProbD, -265, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lbProbD, -479, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, pbProb, 188, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, pbProb, -312, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblProblem, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblProblem, 0, SpringLayout.WEST, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, pbTime, 199, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, pbTime, -301, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 199, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lbTimeD, 105, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, pbTime, 6, SpringLayout.SOUTH, lblTime);
		springLayout.putConstraint(SpringLayout.SOUTH, pbTime, -6, SpringLayout.NORTH, lbTimeD);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 42, SpringLayout.SOUTH, lbTimeD);
		springLayout.putConstraint(SpringLayout.WEST, panel, 96, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lbTimeD, 362, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 10, SpringLayout.NORTH, this);
		setLayout(springLayout);
		add(lblTime);
		add(lblProblem);
		add(pbProb);
		add(pbTime);
		add(lbTimeD);
		add(lbProbD);
		add(panel);

		this.setVisible(true);
	}
	
	public void setNames(List<String> names){
		panel.setNames(names);
	}
	
	public void updateGraph(String name, double time, double input){
		panel.updateChart();
		panel.updateSeries(name, time, input);
		panel.display();
		this.repaint();
	}
	
	public Graph getGraphData(){
		return panel;
	}
	
	public void updateTime(double time){
		pbTime.setValue((int)time);
		lbTimeD.setText(String.valueOf(((int)time)));
	}
	
	public void updateProb(double problem){
		pbProb.setValue((int)(100-problem));
		lbProbD.setText(String.valueOf((int)(100-problem)));
	}

	public void clearGraphs() {
		panel.clearSeries();
	}
}
