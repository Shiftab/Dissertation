package gui;

import jade.core.Agent;

import java.awt.EventQueue;

import javax.swing.JFrame;

import agent.Knowledge.Personality;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import control.Control;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Setup extends GUITemplate {

	private Map<String, Personality> pupils;
	private List<String> pupilList = new ArrayList<String>();
	private List<JLabel> labelList = new ArrayList<JLabel>();

	// JFrame frame;
	JLabel operationalD;
	JSlider slTime;
	JSlider slPupils;
	JLabel spatialD;
	JLabel name1, name2, name3, name4, name5, name6;
	JButton btnChange1, btnChange2, btnChange3, btnChange4, btnChange5,
			btnChange6;
	JButton btnLoad1, btnLoad2, btnLoad3, btnLoad4, btnLoad5, btnLoad6;
	private JButton btnStart;

	/**
	 * Create the application.
	 */
	public Setup(Control parent, Map<String, Personality> pupils) {
		super(parent);
		this.pupils = pupils;
		for (String s : pupils.keySet())
			pupilList.add(s);
		initialize();
	}

	public void toggleVisible() {
		frame.setVisible(!frame.isVisible());
	}

	public void refresh(Map<String, Personality> pupils) {
		this.pupils = pupils;
		int pos = 0;
		for (String s : pupilList) {
			if (pupils.keySet().contains(s))
				continue;
			else {
				pos = pupilList.indexOf(s);
				pupilList.remove(pos);
				break;
			}
		}

		for (String s : pupils.keySet())
			if (pupilList.contains(s))
				continue;
			else
				pupilList.add(pos, s);

		int count = 0;
		for (String s : pupilList) {
			labelList.get(count).setText(s);
			count++;
		}
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

		operationalD = new JLabel("5");

		spatialD = new JLabel("New label");

		JLabel lblNewLabel = new JLabel("Time Limit");

		JLabel lblSpatialTemporal = new JLabel("Number of pupils in the group");

		JLabel lblPupils = new JLabel("Pupils");

		name1 = new JLabel(pupilList.get(0));

		btnChange1 = new JButton("View/Change");
		btnChange1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name1.getText(), pupils.get(name1.getText()));
			}
		});

		name2 = new JLabel(pupilList.get(1));

		btnChange2 = new JButton("View/Change");
		btnChange2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name2.getText(), pupils.get(name2.getText()));
			}
		});

		name3 = new JLabel(pupilList.get(2));

		btnChange3 = new JButton("View/Change");
		btnChange3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name3.getText(), pupils.get(name3.getText()));
			}
		});

		name4 = new JLabel(pupilList.get(3));

		btnChange4 = new JButton("View/Change");
		btnChange4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name4.getText(), pupils.get(name4.getText()));
			}
		});

		name5 = new JLabel(pupilList.get(4));

		btnChange5 = new JButton("View/Change");
		btnChange5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name5.getText(), pupils.get(name5.getText()));
			}
		});

		name6 = new JLabel(pupilList.get(5));

		btnChange6 = new JButton("View/Change");
		btnChange6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.pupilEddit(name6.getText(), pupils.get(name6.getText()));
			}
		});

		btnLoad2 = new JButton("Load");

		btnLoad1 = new JButton("Load");

		btnLoad3 = new JButton("Load");

		btnLoad4 = new JButton("Load");

		btnLoad5 = new JButton("Load");

		btnLoad6 = new JButton("Load");

		slTime = new JSlider();
		slTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				operationalD.setText(Integer.toString(slTime.getValue()));
			}
		});
		slTime.setMajorTickSpacing(1);
		slTime.setMinimum(1);
		slTime.setMaximum(10);
		slTime.setValue(5);

		slPupils = new JSlider();
		slPupils.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				spatialD.setText(Integer.toString(slPupils.getValue()));
				switch (slPupils.getValue()) {
				case 2:
					name3.setVisible(false);
					btnChange3.setVisible(false);
					btnLoad3.setVisible(false);
					break;
				case 3:
					name3.setVisible(true);
					btnChange3.setVisible(true);
					btnLoad3.setVisible(true);

					name4.setVisible(false);
					btnChange4.setVisible(false);
					btnLoad4.setVisible(false);
					break;
				case 4:
					name4.setVisible(true);
					btnChange4.setVisible(true);
					btnLoad4.setVisible(true);

					name5.setVisible(false);
					btnChange5.setVisible(false);
					btnLoad5.setVisible(false);
					break;
				case 5:
					name5.setVisible(true);
					btnChange5.setVisible(true);
					btnLoad5.setVisible(true);

					name6.setVisible(false);
					btnChange6.setVisible(false);
					btnLoad6.setVisible(false);
					break;
				case 6:
					name6.setVisible(true);
					btnChange6.setVisible(true);
					btnLoad6.setVisible(true);
					break;
				}
			}
		});

		name4.setVisible(false);
		btnChange4.setVisible(false);
		btnLoad4.setVisible(false);

		name5.setVisible(false);
		btnChange5.setVisible(false);
		btnLoad5.setVisible(false);

		name6.setVisible(false);
		btnChange6.setVisible(false);
		btnLoad6.setVisible(false);

		slPupils.setValue(3);
		slPupils.setMinimum(2);
		slPupils.setMaximum(6);
		slPupils.setMajorTickSpacing(1);

		JLabel lblProblem = new JLabel("Problem");

		JButton btnViewchange = new JButton("View/Change");
		btnViewchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.changeView(Control.PROB);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Minuets");

		labelList.add(name1);
		labelList.add(name2);
		labelList.add(name3);
		labelList.add(name4);
		labelList.add(name5);
		labelList.add(name6);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> names = new ArrayList<String>();
				int count = 0;
				for(JLabel l: labelList){
					String s = l.getText();
					names.add(s);
					count++;
					if(count>slPupils.getValue())
						break;
				}
				parent.start(slTime.getValue(), names);
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(319)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDisabilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(slTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblProblem)
									.addGap(54)
									.addComponent(btnViewchange))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSpatialTemporal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(slPupils, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPupils)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(263)
									.addComponent(spatialD))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(name2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(name1)
										.addComponent(name3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(name4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(name5, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(name6, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnChange2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChange3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChange4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChange5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChange1)
										.addComponent(btnChange6, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnLoad2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLoad3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLoad4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLoad5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLoad6, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLoad1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(457)
							.addComponent(operationalD)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(308, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDisabilities, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(slTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(operationalD)
						.addComponent(lblNewLabel_1))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProblem)
						.addComponent(btnViewchange))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSpatialTemporal)
						.addComponent(slPupils, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spatialD)
					.addGap(18)
					.addComponent(lblPupils)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(name1)
								.addComponent(btnChange1)
								.addComponent(btnLoad1))
							.addGap(23)
							.addComponent(name2)
							.addGap(31)
							.addComponent(name3)
							.addGap(31)
							.addComponent(name4)
							.addGap(31)
							.addComponent(name5)
							.addGap(31)
							.addComponent(name6))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(btnChange2)
							.addGap(18)
							.addComponent(btnChange3)
							.addGap(18)
							.addComponent(btnChange4)
							.addGap(18)
							.addComponent(btnChange5))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(btnLoad2)
							.addGap(18)
							.addComponent(btnLoad3)
							.addGap(18)
							.addComponent(btnLoad4)
							.addGap(18)
							.addComponent(btnLoad5)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad6)
								.addComponent(btnChange6)))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void setPane(JPanel loading){
		frame.setContentPane(loading);
		frame.repaint();
		}
}
