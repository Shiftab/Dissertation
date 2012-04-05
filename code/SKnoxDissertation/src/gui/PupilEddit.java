package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class PupilEddit {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PupilEddit window = new PupilEddit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PupilEddit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblPupilView = new JLabel("Pupil View");
		
		JLabel lblAbility = new JLabel("Ability:");
		
		JTextPane txtpnThisIsA = new JTextPane();
		txtpnThisIsA.setText("this is a \nbox\n");
		
		JLabel lblPersonality = new JLabel("Personality:");
		
		JTextPane textPane = new JTextPane();
		
		JButton btnChange = new JButton("Change");
		
		JButton button = new JButton("Change");
		
		JButton btnSave = new JButton("Save");
		
		JButton btnCancel = new JButton("Cancel");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPupilView)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAbility)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPersonality)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(btnSave)
											.addComponent(button, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
								.addComponent(btnChange))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtpnThisIsA)
									.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
								.addComponent(btnCancel))))
					.addContainerGap(575, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(lblPupilView)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAbility)
							.addGap(18)
							.addComponent(btnChange))
						.addComponent(txtpnThisIsA, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPersonality)
							.addGap(18)
							.addComponent(button))
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
