package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setup window = new Setup();
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
	public Setup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDisabilities = new JLabel("Setup Page");
		lblDisabilities.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Time Limit");
		
		JLabel lblSpatialTemporal = new JLabel("Number of pupils in the group");
		
		JSlider operationalS = new JSlider();
		operationalS.setMajorTickSpacing(1);
		operationalS.setMinimum(-10);
		operationalS.setMaximum(10);
		operationalS.setValue(0);
		
		JSlider spatialS = new JSlider();
		spatialS.setValue(0);
		spatialS.setMinimum(-10);
		spatialS.setMaximum(10);
		spatialS.setMajorTickSpacing(1);
		
		JLabel operationalD = new JLabel("New label");
		
		JLabel spatialD = new JLabel("New label");
		
		JLabel lblProblem = new JLabel("Problem");
		
		JButton btnViewchange = new JButton("View/Change");
		
		JLabel lblPupils = new JLabel("Pupils");
		
		JLabel lblName = new JLabel("Name");
		
		JLabel label = new JLabel(":");
		
		JLabel lblAbility = new JLabel("Ability");
		
		JLabel label_1 = new JLabel(",");
		
		JLabel lblPersonality = new JLabel("Personality");
		
		JButton btnViewchange_1 = new JButton("View/Change");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(319)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDisabilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(operationalS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblProblem)
								.addGap(54)
								.addComponent(btnViewchange))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblSpatialTemporal)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(spatialS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblPupils)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblName)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblAbility)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblPersonality)
								.addGap(18)
								.addComponent(btnViewchange_1)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(136)
							.addComponent(operationalD))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(263)
							.addComponent(spatialD)))
					.addContainerGap(318, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDisabilities, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(operationalS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(operationalD)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProblem)
						.addComponent(btnViewchange))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSpatialTemporal)
						.addComponent(spatialS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spatialD)
					.addGap(18)
					.addComponent(lblPupils)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(label)
						.addComponent(lblAbility)
						.addComponent(label_1)
						.addComponent(lblPersonality)
						.addComponent(btnViewchange_1))
					.addContainerGap(252, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
