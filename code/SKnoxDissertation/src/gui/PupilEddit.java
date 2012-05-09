package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import agent.Knowledge.Personality;

import control.Control;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class PupilEddit extends JPanel {

	// JFrame frame; //needs to be here for the design edditor to work, can't be
	// there when running

	private final String OP_POS = " likes complex problems",
			OP_NEG = " struggles with using math operations",

			NCN_POS = " has a good understanding of numbers",
			NCN_NEG = " strugles with numerical values and concepts",

			NCM_POS = " very good with complex sequences",
			NCM_NEG = " strugles with ordering and ranking things",

			AB_POS = " can work well with abstract problems",
			AB_NEG = " struggles with more theoretical concepts",

			AVG = " is a compatent student but could use time better";

	private final String O_POS = " is open to new ideas and experiances",
			O_NEG = " dislikes change",

			C_POS = " is a hard worker with a strong focus",
			C_NEG = " is easly distracted",

			E_POS = " is very outgoing and works well with others",
			E_NEG = " can be quite shy",

			A_POS = " is very helpull and quick to please",
			A_NEG = " is very opinionated",

			N_POS = " can be argumentitive", N_NEG = " is easy going",

			AVG_P = " is a well manered individual";

	private final String LNK_POS = ", and", LNK_NEG = ", but";

	private JTextField txtName;
	JTextPane txtpnAbility;
	JTextPane txtpnPersonal;
	private Control parent;
	private String name;

	/**
	 * Create the application.
	 */
	public PupilEddit(Control parent) {
		this.parent = parent;
		setOpaque(false);
		initialize();
	}

	public void setUp(String name, Personality personality) {
		txtName.setText(name);
		this.name = name;
		setAbilityText(personality);
		setPersonalityText(personality);
	}

	private void setAbilityText(Personality personality) {
		double[] ability = personality.getAbility();
		// temporary TODO: implement nlg
		double val = 0.5;
		int big = 0, little = 0;

		for (int x = 0; x < 4; x++)
			// get the most promanent values
			if (Math.abs(ability[x] - 0.5) > Math.abs(val - 0.5)) {
				little = x;
				if (Math.abs(ability[big] - 0.5) < Math
						.abs(ability[little] - 0.5)) {
					int temp = big;
					big = little;
					little = temp;
				}
				if (x > 0)
					val = ability[little];
			}

		boolean pos = false;
		boolean avg = false;
		String disc = "";
		if (ability[big] > 0.55) {
			// pos
			pos = true;
			switch (big) {
			case 0:
				disc += OP_POS;
				break;
			case 1:
				disc += NCN_POS;
				break;
			case 2:
				disc += NCM_POS;
				break;
			case 3:
				disc += AB_POS;
				break;
			}
		} else if (ability[big] < 0.45) {
			pos = false;
			// neg
			switch (big) {
			case 0:
				disc += OP_NEG;
				break;
			case 1:
				disc += NCN_NEG;
				break;
			case 2:
				disc += NCM_NEG;
				break;
			case 3:
				disc += AB_NEG;
				break;
			}
		} else {
			disc += AVG;
			avg = true;
		}
		if (!avg)
			if (ability[little] > 0.5) {
				if (pos)
					disc += LNK_POS;
				else
					disc += LNK_NEG;

				switch (little) {
				case 0:
					disc += OP_POS;
					break;
				case 1:
					disc += NCN_POS;
					break;
				case 2:
					disc += NCM_POS;
					break;
				case 3:
					disc += AB_POS;
					break;
				}
			} else {
				if (pos)
					disc += LNK_NEG;
				else
					disc += LNK_POS;

				switch (little) {
				case 0:
					disc += OP_NEG;
					break;
				case 1:
					disc += NCN_NEG;
					break;
				case 2:
					disc += NCM_NEG;
					break;
				case 3:
					disc += AB_NEG;
					break;
				}
			}

		txtpnAbility.setText(name + disc + ".");
	}

	private void setPersonalityText(Personality personality) {
		double[] ocean = personality.getOCEAN();
		// temporary TODO: implement nlg
		double val = 0;
		int big = 0, little = 0;

		for (int x = 0; x < 4; x++)
			// get the most promanent values
			if (Math.abs(ocean[x]) > Math.abs(val)) {
				little = x;
				if (Math.abs(ocean[big]) < Math.abs(ocean[little])) {
					int temp = big;
					big = little;
					little = temp;
				}
				if (x > 0)
					val = ocean[little];
			}

		boolean pos = false;
		boolean avg = false;
		String disc = "";
		if (ocean[big] > 0.1) {
			// pos
			pos = true;
			switch (big) {
			case 0:
				disc += O_POS;
				break;
			case 1:
				disc += C_POS;
				break;
			case 2:
				disc += E_POS;
				break;
			case 3:
				disc += A_POS;
				break;
			case 4:
				disc += N_POS;
				break;
			}
		} else if (ocean[big] < -0.1) {
			pos = false;
			// neg
			switch (big) {
			case 0:
				disc += O_NEG;
				break;
			case 1:
				disc += C_NEG;
				break;
			case 2:
				disc += E_NEG;
				break;
			case 3:
				disc += A_NEG;
				break;
			case 4:
				disc += N_NEG;
				break;
			}
		} else {
			disc += AVG_P;
			avg = true;
		}

		if (!avg)
			if (ocean[little] > 0) {
				if (pos)
					disc += LNK_POS;
				else
					disc += LNK_NEG;

				switch (little) {
				case 0:
					disc += O_POS;
					break;
				case 1:
					disc += C_POS;
					break;
				case 2:
					disc += E_POS;
					break;
				case 3:
					disc += A_POS;
					break;
				case 4:
					disc += N_POS;
					break;
				}
			} else {
				if (pos)
					disc += LNK_NEG;
				else
					disc += LNK_POS;

				switch (little) {
				case 0:
					disc += O_NEG;
					break;
				case 1:
					disc += C_NEG;
					break;
				case 2:
					disc += E_NEG;
					break;
				case 3:
					disc += A_NEG;
					break;
				case 4:
					disc += N_NEG;
					break;
				}
			}

		txtpnPersonal.setText(name + disc + ".");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblPupilView = new JLabel("Pupil View");
		lblPupilView.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		JLabel lblAbility = new JLabel("Ability:");

		txtpnAbility = new JTextPane();
		txtpnAbility.setEditable(false);
		txtpnAbility.setText("this is a \nbox\n");

		JLabel lblPersonality = new JLabel("Personality:");

		txtpnPersonal = new JTextPane();
		txtpnPersonal.setEditable(false);

		JButton btnChangeAbility = new JButton("Change");
		btnChangeAbility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// setVisible(false);
				parent.changeAbility(name);
			}
		});

		JButton btnChangePers = new JButton("Change");
		btnChangePers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// setVisible(false);
				parent.changePersonality(name);
			}
		});

		JButton btnBack = new JButton("Save");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.newName(name, txtName.getText());
				// setVisible(false);
				parent.changeView(Control.MAIN);
			}
		});

		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(94)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																txtName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblPupilView)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblAbility)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																lblPersonality)
																														.addComponent(
																																btnChangePers,
																																Alignment.LEADING,
																																GroupLayout.DEFAULT_SIZE,
																																91,
																																Short.MAX_VALUE)
																														.addComponent(
																																btnBack,
																																Alignment.LEADING,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)))
																						.addComponent(
																								btnChangeAbility))
																		.addGap(18)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								txtpnPersonal,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								264,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtpnAbility,
																								Alignment.LEADING))))
										.addContainerGap(557, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(49)
										.addComponent(lblPupilView)
										.addGap(23)
										.addComponent(txtName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblAbility)
																		.addGap(18)
																		.addComponent(
																				btnChangeAbility))
														.addComponent(
																txtpnAbility,
																GroupLayout.PREFERRED_SIZE,
																141,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblPersonality)
																		.addGap(18)
																		.addComponent(
																				btnChangePers))
														.addComponent(
																txtpnPersonal,
																GroupLayout.PREFERRED_SIZE,
																147,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18).addComponent(btnBack)
										.addContainerGap(131, Short.MAX_VALUE)));
		this.setLayout(groupLayout);
	}
}
