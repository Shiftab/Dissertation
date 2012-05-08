package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import agent.Knowledge.Personality;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import control.Control;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ParamiterEddit extends JPanel {

	// JFrame frame;
	private Personality personality;
	private String name;
	private Control parent;

	private final String[][] DISC_ABILITY = {
			{ "Proficiant", "Adequit", "Struggles" },
			{ "Proficiant", "Adequit", "Struggles" },
			{ "Proficiant", "Adequit", "Struggles" },
			{ "Proficiant", "Adequit", "Struggles" },
			{ "Proficiant", "Adequit", "Struggles" },
			{ "Proficiant", "Adequit", "Struggles" } }; // temp
	/*
	 * { "Good with Complex mathmatical Processes",
	 * "Adequit at using mathmatical operations",
	 * "Struggles with using mathamatical operations" }, {
	 * "Excelent understanding of numerical values and concepts",
	 * "Adequit understanding of numerical values and concepts",
	 * "Struggles with understanding numerical values and concepts" }, {
	 * "Can manipulate and understand complex numerical sequences",
	 * "Adequit understanding of numerical relations",
	 * "Struggles with basic numerical sequences" }, {
	 * "Excelent at manipulating abstract symboels",
	 * "Can manipulate and understand abstract symboles",
	 * "Struggles when using abstract notation" }, {
	 * "Very good at working with graphical implementations",
	 * "Can understand graphical representations of problems",
	 * "Struggles with problems that use graphics" }, {
	 * "Very good sence of phisical location in abstract space",
	 * "Adequit understanding of space and direction",
	 * "Struggles with concepts such as left and right" } };
	 */

	private final String[][] DISC_PERS = {
			{ "Interested in new concepts", "Open to new ideas",
					"Dislikes change" },
			{ "Perfectionist", "Adequit organisational skills", "Lazy" },
			{ "Talkative", "Works well with others", "Very Shy" },
			{ "Conciderate and Friendly", "Works well with others",
					"Distrusting" },
			{ "Argumentitive", "Opininated", "Quiet" },
			{ "Very good sence of phisical location in abstract space",
					"Adequit understanding of space and direction",
					"Struggles with concepts such as left and right" } };

	JLabel lbParam1, lbParam2, lbParam3, lbParam4, lbParam5, lbParam6;
	JSlider sl1, sl2, sl3, sl4, sl5, sl6;
	JLabel lbDisc1, lbDisc2, lbDisc3, lbDisc4, lbDisc5, lbDisc6;

	private List<JLabel> nameList = new ArrayList<JLabel>();
	private List<JSlider> sliderList = new ArrayList<JSlider>();
	private List<JLabel> discList = new ArrayList<JLabel>();
	private boolean ability = false;

	/**
	 * Create the application.
	 */
	public ParamiterEddit(Control parent) {
		this.parent = parent;
		setOpaque(false);
		initialize();
	}

	public void setUpAbility(Personality personality, String name) {
		sliderList.clear();
		discList.clear();
		ability = true;
		this.personality = personality;
		this.name = name;
		double[] ability = personality.getAbility();

		sliderList.add(sl1);
		sliderList.add(sl2);
		sliderList.add(sl3);
		sliderList.add(sl4);
		sliderList.add(sl5);
		sliderList.add(sl6);

		discList.add(lbDisc1);
		discList.add(lbDisc2);
		discList.add(lbDisc3);
		discList.add(lbDisc4);
		discList.add(lbDisc5);
		discList.add(lbDisc6);

		lbParam1.setText("operational");
		lbParam2.setText("numberConceptual");
		lbParam3.setText("numberComparative");
		lbParam4.setText("abstractSymbolic");
		lbParam5.setText("graphical");
		lbParam6.setText("spatialTemporal");
		lbParam6.setVisible(true);
		sl6.setVisible(true);
		lbDisc6.setVisible(true);

		int count = 0;
		for (JSlider js : sliderList) {
			js.setMaximum(10);
			js.setMinimum(0);
			js.setValue((int) (ability[count] * 10));
			count++;
		}

		count = 0;
		for (JLabel jl : discList) {
			setDiscLable(jl, count, sliderList.get(count).getValue());
			count++;
		}
	}

	public void setUpPersonal(Personality personality, String name) {
		sliderList.clear();
		discList.clear();
		ability = false;
		this.personality = personality;
		this.name = name;
		double[] ocean = personality.getOCEAN();

		lbParam1.setText("openness");
		lbParam2.setText("conscientiousness");
		lbParam3.setText("extraversion");
		lbParam4.setText("agreeableness");
		lbParam5.setText("neuroticism");
		lbParam6.setVisible(false);
		sl6.setVisible(false);
		lbDisc6.setVisible(false);

		sliderList.add(sl1);
		sliderList.add(sl2);
		sliderList.add(sl3);
		sliderList.add(sl4);
		sliderList.add(sl5);

		discList.add(lbDisc1);
		discList.add(lbDisc2);
		discList.add(lbDisc3);
		discList.add(lbDisc4);
		discList.add(lbDisc5);

		int count = 0;
		for (JSlider js : sliderList) {
			js.setMaximum(10);
			js.setMinimum(-10);
			js.setValue((int) (ocean[count] * 10));
			js.repaint();
			this.repaint();
			count++;
		}
		System.out.println("\n");
		count = 0;
		for (JLabel jl : discList) {
			setDiscLable(jl, count, sliderList.get(count).getValue());
			count++;
		}
	}

	private void setDiscLable(JLabel jl, int count, int value) {
		String[][] disc;
		int uperCut = 5;
		int lowerCut = -5;
		if (ability) {
			disc = DISC_ABILITY;
			uperCut = 7;
			lowerCut = 3;
		} else {
			disc = DISC_PERS;
			uperCut = 5;
			lowerCut = -5;
		}
		if (value <= lowerCut) { // lower
			jl.setText(disc[count][2]);
			jl.setForeground(Color.red);
		} else if (value >= uperCut) { // higher
			jl.setText(disc[count][0]);
			jl.setForeground(Color.green);
		} else { // mid
			jl.setText(disc[count][1]);
			jl.setForeground(Color.orange);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblAbilities = new JLabel("Abilities");
		lblAbilities.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		lbParam2 = new JLabel("Number Conceptual");

		lbParam4 = new JLabel("Abstract Symbolic");

		lbParam5 = new JLabel("Graphical");

		lbParam6 = new JLabel("Spatial Temporal");

		lbParam3 = new JLabel("Number Comparative");

		lbParam1 = new JLabel("Operational");

		sl6 = new JSlider();
		sl6.setValue(0);
		sl6.setMinimum(-10);
		sl6.setMaximum(10);
		sl6.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc6, 5, sl6.getValue());
			}
		});

		lbDisc6 = new JLabel("New label");

		sl5 = new JSlider();
		sl5.setValue(0);
		sl5.setMinimum(-10);
		sl5.setMaximum(10);
		sl5.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc5, 4, sl5.getValue());
			}
		});

		lbDisc5 = new JLabel("New label");

		sl4 = new JSlider();
		sl4.setValue(0);
		sl4.setMinimum(-10);
		sl4.setMaximum(10);
		sl4.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc4, 3, sl4.getValue());
			}
		});

		lbDisc4 = new JLabel("New label");

		sl3 = new JSlider();
		sl3.setValue(0);
		sl3.setMinimum(-10);
		sl3.setMaximum(10);
		sl3.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc3, 2, sl3.getValue());
			}
		});

		lbDisc3 = new JLabel("numCompD");

		sl2 = new JSlider();
		sl2.setValue(0);
		sl2.setMinimum(-10);
		sl2.setMaximum(10);
		sl2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc2, 1, sl2.getValue());
			}
		});

		lbDisc2 = new JLabel("New label");

		sl1 = new JSlider();
		sl1.setValue(0);
		sl1.setMaximum(10);
		sl1.setMinimum(-10);
		sl1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setDiscLable(lbDisc1, 0, sl1.getValue());
			}
		});

		lbDisc1 = new JLabel("New label");

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double[] ans = null;
				if (ability)
					ans = new double[6];
				else
					ans = new double[5];

				int count = 0;
				for (JSlider sl : sliderList) {
					ans[count] = sl.getValue() * 0.1;
					count++;
				}

				if (ability) {
					personality.setAbility(ans);
				} else
					personality.setOCEAN(ans);

				parent.setPersonality(name, personality);
				setVisible(false);
				parent.pupilEddit(name, personality);
			}
		});

		JButton btnCansel = new JButton("Cancel");
		btnCansel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.changeView(Control.PUPIL);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(284)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblAbilities,
																GroupLayout.PREFERRED_SIZE,
																99,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lbParam3,
																				GroupLayout.PREFERRED_SIZE,
																				133,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(52)
																		.addComponent(
																				sl3,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(6)
																		.addComponent(
																				lbDisc3,
																				GroupLayout.PREFERRED_SIZE,
																				75,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lbParam4,
																				GroupLayout.PREFERRED_SIZE,
																				114,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(71)
																		.addComponent(
																				sl4,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbDisc4,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lbParam5,
																				GroupLayout.PREFERRED_SIZE,
																				88,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(97)
																		.addComponent(
																				sl5,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbDisc5,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								btnSave,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lbParam6,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								105,
																								Short.MAX_VALUE))
																		.addGap(80)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								sl6,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnCansel,
																								GroupLayout.PREFERRED_SIZE,
																								106,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbDisc6,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lbParam2,
																				GroupLayout.PREFERRED_SIZE,
																				125,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(60)
																		.addComponent(
																				sl2,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbDisc2,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lbParam1,
																				GroupLayout.PREFERRED_SIZE,
																				73,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(112)
																		.addComponent(
																				sl1,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbDisc1,
																				GroupLayout.DEFAULT_SIZE,
																				75,
																				Short.MAX_VALUE)))
										.addGap(102)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(180)
																		.addComponent(
																				lblAbilities,
																				GroupLayout.PREFERRED_SIZE,
																				34,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(21)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(13)
																										.addComponent(
																												lbParam1))
																						.addComponent(
																								sl1,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(248)
																		.addComponent(
																				lbDisc1)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																sl2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addComponent(
																				lbDisc2)
																		.addComponent(
																				lbParam2)))
										.addGap(6)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(13)
																		.addComponent(
																				lbParam3))
														.addComponent(
																sl3,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(13)
																		.addComponent(
																				lbDisc3)))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(6)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(13)
																										.addComponent(
																												lbParam4))
																						.addComponent(
																								sl4,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(19)
																		.addComponent(
																				lbDisc4)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(19)
																		.addComponent(
																				lbDisc5))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(6)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(13)
																										.addComponent(
																												lbParam5))
																						.addComponent(
																								sl5,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addGap(13)
																						.addComponent(
																								lbParam6))
																		.addComponent(
																				sl6,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(lbDisc6))
										.addGap(53)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnSave)
														.addComponent(btnCansel))
										.addContainerGap(111, Short.MAX_VALUE)));
		this.setLayout(groupLayout);
	}
}
