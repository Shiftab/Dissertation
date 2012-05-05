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
		
		JLabel lblTime = new JLabel("Time Left: ");
		
		JLabel lblProblem = new JLabel("Problem Compleated: ");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, lbTimeD, 0, SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, lbTimeD, 6, SpringLayout.EAST, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, lbProbD, 6, SpringLayout.EAST, lblProblem);
		springLayout.putConstraint(SpringLayout.EAST, lbProbD, -687, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lbProbD, 0, SpringLayout.NORTH, lblProblem);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, pbTime, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pbTime, 99, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, pbTime, -490, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, pbProb, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pbProb, -350, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, pbProb, -490, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblProblem, 127, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, pbTime, 6, SpringLayout.SOUTH, lblTime);
		springLayout.putConstraint(SpringLayout.NORTH, pbProb, 6, SpringLayout.SOUTH, lblProblem);
		springLayout.putConstraint(SpringLayout.WEST, lblProblem, 0, SpringLayout.WEST, lblTime);
		setLayout(springLayout);
		add(lblTime);
		add(lblProblem);
		add(pbProb);
		add(pbTime);
		add(lbTimeD);
		add(lbProbD);
		add(panel);
		
		JLabel label = new JLabel(" ");
		springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, lblTime);
		springLayout.putConstraint(SpringLayout.WEST, label, 252, SpringLayout.EAST, lblTime);
		springLayout.putConstraint(SpringLayout.EAST, label, -490, SpringLayout.EAST, this);
		add(label);

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
		lbTimeD.setText(String.valueOf(((int)time))+"%");
	}
	
	public void updateProb(double problem){
		pbProb.setValue((int)(100-problem));
		lbProbD.setText(String.valueOf((int)(100-problem))+"%");
	}

	public void clearGraphs() {
		panel.clearSeries();
	}
}
