package gui;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class Loading extends JPanel {

	JProgressBar pbTime= new JProgressBar(), pbProb= new JProgressBar();
	private final JLabel lbTimeD = new JLabel("100");
	private final JLabel lbProbD = new JLabel("0");
	JPanel panel = new Graph();
	
	/**
	 * Create the panel.
	 */
	public Loading() {
		
		JLabel lblTime = new JLabel("Time");
		
		JLabel lblProblem = new JLabel("Problem");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, lbProbD, 0, SpringLayout.EAST, lbTimeD);
		springLayout.putConstraint(SpringLayout.WEST, lblProblem, 199, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTime, -6, SpringLayout.NORTH, pbTime);
		springLayout.putConstraint(SpringLayout.EAST, lblTime, 0, SpringLayout.EAST, lblProblem);
		springLayout.putConstraint(SpringLayout.NORTH, lbProbD, 6, SpringLayout.SOUTH, pbProb);
		springLayout.putConstraint(SpringLayout.SOUTH, lbProbD, -166, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, pbProb, 6, SpringLayout.SOUTH, lblProblem);
		springLayout.putConstraint(SpringLayout.WEST, pbProb, 0, SpringLayout.WEST, pbTime);
		springLayout.putConstraint(SpringLayout.SOUTH, pbProb, -188, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, pbProb, 0, SpringLayout.EAST, pbTime);
		springLayout.putConstraint(SpringLayout.NORTH, lbTimeD, 6, SpringLayout.SOUTH, pbTime);
		springLayout.putConstraint(SpringLayout.WEST, pbTime, 250, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lbProbD, 0, SpringLayout.WEST, lbTimeD);
		springLayout.putConstraint(SpringLayout.EAST, pbTime, 615, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblProblem, -239, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lbTimeD, 427, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, pbTime, 197, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, pbTime, -324, SpringLayout.SOUTH, this);
		setLayout(springLayout);
		add(lblTime);
		add(lblProblem);
		add(pbProb);
		add(pbTime);
		add(lbTimeD);
		add(lbProbD);
		
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		add(panel);

		this.setVisible(true);
	}
	
	public void updateTime(double time){
		pbTime.setValue((int)time);
		lbTimeD.setText(String.valueOf(((int)time)));
	}
	
	public void updateProb(double problem){
		pbProb.setValue((int)(100-problem));
		lbProbD.setText(String.valueOf((int)(100-problem)));
	}
}
