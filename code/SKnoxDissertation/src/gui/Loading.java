package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Loading extends JPanel {

	JProgressBar pbTime= new JProgressBar(), pbProb= new JProgressBar();
	
	/**
	 * Create the panel.
	 */
	public Loading() {
		
		JLabel lblTime = new JLabel("Time");
		
		JLabel lblProblem = new JLabel("Problem");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.RELATED, 334, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblProblem)
								.addPreferredGap(ComponentPlacement.RELATED, 314, GroupLayout.PREFERRED_SIZE))
							.addComponent(pbTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(pbProb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(42))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(67)
					.addComponent(lblTime)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pbTime, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(lblProblem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pbProb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		setLayout(groupLayout);

		this.setVisible(true);
	}
	
	public void updateTime(double time){
		pbTime.setValue((int)time);
	}
	
	public void updateProb(double problem){
		pbProb.setValue((int)(100-problem));
	}
}
