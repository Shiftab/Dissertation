package gui;

import agent.Knowledge.Personality;

import control.Control;

import javax.swing.JSlider;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Setup extends JPanel {

	private Map<String, Personality> pupils;
	private List<String> pupilList = new ArrayList<String>();
	private List<JLabel> labelList = new ArrayList<JLabel>();
	private Control parent;

	// JFrame frame;
	JLabel operationalD;
	JSlider slTime;
	JSlider slPupils;
	JLabel spatialD;
	JLabel name1, name2, name3, name4, name5;
	JButton btnChange1, btnChange2, btnChange3, btnChange4, btnChange5;
	JButton btnLoad1, btnLoad2, btnLoad3, btnLoad4, btnLoad5;
	JButton btnSave1, btnSave2, btnSave3, btnSave4, btnSave5;
	private JButton btnStart;

	/**
	 * Create the application.
	 */
	public Setup(Control parent, Map<String, Personality> pupils, Dimension size) {
		setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
		setBorder(null);
		this.parent = parent;
		this.pupils = pupils;
		for (String s : pupils.keySet())
			pupilList.add(s);
		initialize();
	}

	public void toggleVisible() {
		this.setVisible(!this.isVisible());
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilEddit(name1.getText(), pupils.get(name1.getText()));
			}
		});

		name2 = new JLabel(pupilList.get(1));

		btnChange2 = new JButton("View/Change");
		btnChange2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilEddit(name2.getText(), pupils.get(name2.getText()));
			}
		});

		name3 = new JLabel(pupilList.get(2));

		btnChange3 = new JButton("View/Change");
		btnChange3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilEddit(name3.getText(), pupils.get(name3.getText()));
			}
		});

		name4 = new JLabel(pupilList.get(3));

		btnChange4 = new JButton("View/Change");
		btnChange4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilEddit(name4.getText(), pupils.get(name4.getText()));
			}
		});

		name5 = new JLabel(pupilList.get(4));

		btnChange5 = new JButton("View/Change");
		btnChange5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilEddit(name5.getText(), pupils.get(name5.getText()));
			}
		});

		btnLoad2 = new JButton("Load");
		btnLoad2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPupil(name2.getText());
			}
		});

		btnLoad1 = new JButton("Load");
		btnLoad1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPupil(name1.getText());
			}
		});

		btnLoad3 = new JButton("Load");
		btnLoad3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPupil(name3.getText());
			}
		});

		btnLoad4 = new JButton("Load");
		btnLoad4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPupil(name4.getText());
			}
		});

		btnLoad5 = new JButton("Load");
		btnLoad5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadPupil(name5.getText());
			}
		});

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<String> names = new ArrayList<String>();
				int count = 0;
				for (JLabel l : labelList) {
					String s = l.getText();
					names.add(s);
					count++;
					if (count >= slPupils.getValue())
						break;
				}
				parent.start(slTime.getValue(), names);
			}
		});

		btnSave1 = new JButton("Save");
		btnSave1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePupil(name1.getText());
			}
		});

		btnSave2 = new JButton("Save");
		btnSave2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePupil(name2.getText());
			}
		});

		btnSave3 = new JButton("Save");
		btnSave3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePupil(name3.getText());
			}
		});

		btnSave4 = new JButton("Save");
		btnSave4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePupil(name4.getText());
			}
		});

		btnSave5 = new JButton("Save");
		btnSave5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				savePupil(name5.getText());
			}
		});

		slTime = new JSlider();
		slTime.addChangeListener(new ChangeListener() {
			@Override
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
			@Override
			public void stateChanged(ChangeEvent arg0) {
				spatialD.setText(Integer.toString(slPupils.getValue()));
				switch (slPupils.getValue()) {
				case 2:
					name3.setVisible(false);
					btnChange3.setVisible(false);
					btnLoad3.setVisible(false);
					btnSave3.setVisible(false);

					name4.setVisible(false);
					btnChange4.setVisible(false);
					btnLoad4.setVisible(false);
					btnSave4.setVisible(false);
					
					name5.setVisible(false);
					btnChange5.setVisible(false);
					btnLoad5.setVisible(false);
					btnSave5.setVisible(false);
					break;
				case 3:
					name3.setVisible(true);
					btnChange3.setVisible(true);
					btnLoad3.setVisible(true);
					btnSave3.setVisible(true);

					name4.setVisible(false);
					btnChange4.setVisible(false);
					btnLoad4.setVisible(false);
					btnSave4.setVisible(false);
					
					name5.setVisible(false);
					btnChange5.setVisible(false);
					btnLoad5.setVisible(false);
					btnSave5.setVisible(false);
					break;
				case 4:
					name4.setVisible(true);
					btnChange4.setVisible(true);
					btnLoad4.setVisible(true);
					btnSave4.setVisible(true);
					
					name3.setVisible(true);
					btnChange3.setVisible(true);
					btnLoad3.setVisible(true);
					btnSave3.setVisible(true);

					name5.setVisible(false);
					btnChange5.setVisible(false);
					btnLoad5.setVisible(false);
					btnSave5.setVisible(false);
					break;
				case 5:

					name4.setVisible(true);
					btnChange4.setVisible(true);
					btnLoad4.setVisible(true);
					btnSave4.setVisible(true);
					
					name3.setVisible(true);
					btnChange3.setVisible(true);
					btnLoad3.setVisible(true);
					btnSave3.setVisible(true);
					
					name5.setVisible(true);
					btnChange5.setVisible(true);
					btnLoad5.setVisible(true);
					btnSave5.setVisible(true);
				}
				repaint();
			}
		});

		slPupils.setValue(3);
		slPupils.setMinimum(2);
		slPupils.setMaximum(5);
		slPupils.setMajorTickSpacing(1);

		JLabel lblProblem = new JLabel("Problem");

		JButton btnViewchange = new JButton("View/Change");
		btnViewchange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.changeView(Control.PROB);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Minuets");

		labelList.add(name1);
		labelList.add(name2);
		labelList.add(name3);
		labelList.add(name4);
		labelList.add(name5);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(316)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(263)
								.addComponent(spatialD))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(name1, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addComponent(name2, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addComponent(name3, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addComponent(name4, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addComponent(name5, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnChange2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnChange3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnChange4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnChange5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnChange1))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnLoad2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLoad3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLoad4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLoad5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLoad1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
							.addComponent(lblPupils))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(138)
							.addComponent(operationalD)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSave1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave3, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave5, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addGap(165))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
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
					.addGap(19)
					.addComponent(lblPupils)
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnChange1)
							.addComponent(btnLoad1)
							.addComponent(btnSave1)
							.addComponent(name1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnChange2)
								.addComponent(name2))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnChange3)
								.addComponent(name3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnChange4)
								.addComponent(name4))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnChange5)
								.addComponent(name5)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad2)
								.addComponent(btnSave2))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad3)
								.addComponent(btnSave3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad4)
								.addComponent(btnSave4))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoad5)
								.addComponent(btnSave5))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		this.setLayout(groupLayout);
	}

	private void loadPupil(String oldName) {
		String name;
		double[] ability = new double[6], ocean = new double[5];
		JFileChooser fc = new JFileChooser();

		int returnVal = fc.showOpenDialog(parent.getFrame());

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			File prob = fc.getSelectedFile();

			Scanner scan = null;
			try {
				scan = new Scanner(prob);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int count = 0;
			try {
				name = scan.nextLine();
				count = 0;
				for (String s : scan.nextLine().split(",")) {
					ability[count] = Double.valueOf(s.trim());
					count++;
				}
				count = 0;
				for (String s : scan.nextLine().split(",")) {
					ocean[count] = Double.valueOf(s.trim());
					count++;
				}
			} catch (ArrayIndexOutOfBoundsException b) {
				JOptionPane.showMessageDialog(parent.getFrame(),
						"Error, the file was not in the correct format");
				return;
			}

			Personality pers = new Personality(0);
			pers.setAbility(ability);
			pers.setOCEAN(ocean);

			parent.replasePersonality(oldName, name, pers);
			this.refresh(parent.getPupils());
			repaint();
		}
	}

	private void savePupil(String name) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			BufferedWriter out;
			try {
				out = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
				out.write(name);
				out.newLine();
				String output = "";
				for (double d : pupils.get(name).getAbility())
					output += d + ",";
				out.write(output.substring(0, output.length() - 1));
				out.newLine();

				output="";
				for (double d : pupils.get(name).getOCEAN())
					output += d + ",";
				out.write(output.substring(0, output.length() - 1));
				out.newLine();

				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
