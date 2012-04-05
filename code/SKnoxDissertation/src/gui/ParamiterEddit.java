package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ParamiterEddit {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParamiterEddit window = new ParamiterEddit();
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
	public ParamiterEddit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAbilities = new JLabel("Abilities");
		lblAbilities.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("Number Conceptual");
		
		JLabel label_2 = new JLabel("Abstract Symbolic");
		
		JLabel label_3 = new JLabel("Graphical");
		
		JLabel label_4 = new JLabel("Spatial Temporal");
		
		JLabel label_5 = new JLabel("Number Comparative");
		
		JLabel label_6 = new JLabel("Operational");
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.setMinimum(-10);
		slider.setMaximum(10);
		slider.setMajorTickSpacing(1);
		
		JLabel label_7 = new JLabel("New label");
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(0);
		slider_1.setMinimum(-10);
		slider_1.setMaximum(10);
		slider_1.setMajorTickSpacing(1);
		
		JLabel label_8 = new JLabel("New label");
		
		JSlider slider_2 = new JSlider();
		slider_2.setValue(0);
		slider_2.setMinimum(-10);
		slider_2.setMaximum(10);
		slider_2.setMajorTickSpacing(1);
		
		JLabel label_9 = new JLabel("New label");
		
		JSlider slider_3 = new JSlider();
		slider_3.setValue(0);
		slider_3.setMinimum(-10);
		slider_3.setMaximum(10);
		slider_3.setMajorTickSpacing(1);
		
		JLabel label_10 = new JLabel("numCompD");
		
		JSlider slider_4 = new JSlider();
		slider_4.setValue(0);
		slider_4.setMinimum(-10);
		slider_4.setMaximum(10);
		slider_4.setMajorTickSpacing(1);
		
		JLabel label_11 = new JLabel("New label");
		
		JSlider slider_5 = new JSlider();
		slider_5.setValue(0);
		slider_5.setMinimum(-10);
		slider_5.setMaximum(10);
		slider_5.setMajorTickSpacing(1);
		
		JLabel label_12 = new JLabel("New label");
		
		JButton btnSave = new JButton("Save");
		
		JButton btnCansel = new JButton("Cancel");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(284)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(112)
							.addComponent(slider_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_12))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(slider_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(71)
							.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_9))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(126)
							.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(label_8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnSave, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addGap(80)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_7))
								.addComponent(btnCansel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(284, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(180)
					.addComponent(lblAbilities, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_6))
						.addComponent(slider_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_12)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_1))
						.addComponent(slider_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_11)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_5))
						.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_10)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_2))
						.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_9)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_3))
						.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label_8)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(13)
								.addComponent(label_4))
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_7))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCansel))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
