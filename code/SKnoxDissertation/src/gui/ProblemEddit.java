package gui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import control.Control;

public class ProblemEddit extends JPanel {

	//JFrame frame;
	private String file;
	private String[][] problem = new String[9][9];
	private Control parent;

	JLabel g00 = new JLabel("0"), g01 = new JLabel("0"), g02 = new JLabel("0"),
			g03 = new JLabel("0"), g04 = new JLabel("0"),
			g05 = new JLabel("0"), g06 = new JLabel("0"),
			g07 = new JLabel("0"), g08 = new JLabel("0");

	JLabel g10 = new JLabel("0"), g11 = new JLabel("0"), g12 = new JLabel("0"),
			g13 = new JLabel("0"), g14 = new JLabel("0"),
			g15 = new JLabel("0"), g16 = new JLabel("0"),
			g17 = new JLabel("0"), g18 = new JLabel("0");

	JLabel g20 = new JLabel("0"), g21 = new JLabel("0"), g22 = new JLabel("0"),
			g23 = new JLabel("0"), g24 = new JLabel("0"),
			g25 = new JLabel("0"), g26 = new JLabel("0"),
			g27 = new JLabel("0"), g28 = new JLabel("0");

	JLabel g30 = new JLabel("0"), g31 = new JLabel("0"), g32 = new JLabel("0"),
			g33 = new JLabel("0"), g34 = new JLabel("0"),
			g35 = new JLabel("0"), g36 = new JLabel("0"),
			g37 = new JLabel("0"), g38 = new JLabel("0");

	JLabel g40 = new JLabel("0"), g41 = new JLabel("0"), g42 = new JLabel("0"),
			g43 = new JLabel("0"), g44 = new JLabel("0"),
			g45 = new JLabel("0"), g46 = new JLabel("0"),
			g47 = new JLabel("0"), g48 = new JLabel("0");

	JLabel g50 = new JLabel("0"), g51 = new JLabel("0"), g52 = new JLabel("0"),
			g53 = new JLabel("0"), g54 = new JLabel("0"),
			g55 = new JLabel("0"), g56 = new JLabel("0"),
			g57 = new JLabel("0"), g58 = new JLabel("0");
	JLabel g60 = new JLabel("0"), g61 = new JLabel("0"), g62 = new JLabel("0"),
			g63 = new JLabel("0"), g64 = new JLabel("0"),
			g65 = new JLabel("0"), g66 = new JLabel("0"),
			g67 = new JLabel("0"), g68 = new JLabel("0");

	JLabel g70 = new JLabel("0"), g71 = new JLabel("0"), g72 = new JLabel("0"),
			g73 = new JLabel("0"), g74 = new JLabel("0"),
			g75 = new JLabel("0"), g76 = new JLabel("0"),
			g77 = new JLabel("0"), g78 = new JLabel("0");

	JLabel g80 = new JLabel("0"), g81 = new JLabel("0"), g82 = new JLabel("0"),
			g83 = new JLabel("0"), g84 = new JLabel("0"),
			g85 = new JLabel("0"), g86 = new JLabel("0"),
			g87 = new JLabel("0"), g88 = new JLabel("0");

	private JLabel[][] valueList = {
			{ g00, g01, g02, g03, g04, g05, g06, g07, g08 },
			{ g10, g11, g12, g13, g14, g15, g16, g17, g18 },
			{ g20, g21, g22, g23, g24, g25, g26, g27, g28 },
			{ g30, g31, g32, g33, g34, g35, g36, g37, g38 },
			{ g40, g41, g42, g43, g44, g45, g46, g47, g48 },
			{ g50, g51, g52, g53, g54, g55, g56, g57, g58 },
			{ g60, g61, g62, g63, g64, g65, g66, g67, g68 },
			{ g70, g71, g72, g73, g74, g75, g76, g77, g78 },
			{ g80, g81, g82, g83, g84, g85, g86, g87, g88 } };

	/**
	 * Create the application.
	 */
	public ProblemEddit(Control parent) {
		this.parent = parent;
		initialize();
	}

	public void loadProblem(int[][] problem){
		for (int y = 0; y < problem.length; y++)
			for (int x = 0; x < problem.length; x++) {
				valueList[x][y].setText(String.valueOf(problem[x][y]));
			}
	}
	
	public void saveProblem(){
		int[][] prob = new int[9][9];
		for (int y = 0; y < problem.length; y++)
			for (int x = 0; x < problem.length; x++) {
				prob[x][y] = Integer.valueOf(valueList[x][y].getText());
			}
		parent.newProblem(prob);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		JLabel lblProblemEddit = new JLabel("Problem Eddit");

		JLabel lblDefault = new JLabel("File:");

		JLabel lblDefault_1 = new JLabel("Default");

		JButton btnLoadNew = new JButton("Load New");
		btnLoadNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();

				int returnVal = fc.showOpenDialog(parent.getFrame());

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					File prob = fc.getSelectedFile();
					file = prob.getPath();

					Scanner scan = null;
					String[] list = new String[9];
					try {
						scan = new Scanner(prob);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int count = 0;
					while (scan.hasNextLine()) {
						try{
						problem[count] = scan.nextLine().split(",");
						}catch(ArrayIndexOutOfBoundsException b){
							  JOptionPane.showMessageDialog(parent.getFrame(),"Error, the file was not in the correct format");
							  return;
						}
						count++;
					}
					
					for (int y = 0; y < problem.length; y++)
						for (int x = 0; x < problem.length; x++) {
							try{
							Integer.parseInt(problem[x][y].trim());
							}catch(Exception ex){
								  JOptionPane.showMessageDialog(parent.getFrame(),"Error, some values were not numbers");
								  return;
							}
							valueList[x][y].setText(problem[x][y].trim());
						}

					repaint();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.changeView(Control.MAIN);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.changeView(Control.MAIN);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDefault)
							.addGap(37)
							.addComponent(lblDefault_1)
							.addGap(18)
							.addComponent(btnLoadNew))
						.addComponent(lblProblemEddit)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
							.addComponent(btnCancel))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(658, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(lblProblemEddit)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDefault)
						.addComponent(lblDefault_1)
						.addComponent(btnLoadNew))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap(279, Short.MAX_VALUE))
		);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.BLACK);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(Color.BLACK);

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(Color.BLACK);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBackground(Color.BLACK);
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setForeground(Color.BLACK);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setForeground(Color.BLACK);

		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setForeground(Color.BLACK);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setForeground(Color.BLACK);

		JSeparator separator_10 = new JSeparator();
		separator_10.setOrientation(SwingConstants.VERTICAL);
		separator_10.setForeground(Color.BLACK);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(Color.BLACK);
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setForeground(Color.BLACK);

		JSeparator separator_12 = new JSeparator();
		separator_12.setOrientation(SwingConstants.VERTICAL);
		separator_12.setForeground(Color.BLACK);

		JSeparator separator_13 = new JSeparator();
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setForeground(Color.BLACK);

		JSeparator separator_14 = new JSeparator();
		separator_14.setBackground(Color.BLACK);
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setForeground(Color.BLACK);

		JSeparator separator_15 = new JSeparator();
		separator_15.setOrientation(SwingConstants.VERTICAL);
		separator_15.setForeground(Color.BLACK);

		JSeparator separator_16 = new JSeparator();
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setForeground(Color.BLACK);

		JSeparator separator_17 = new JSeparator();
		separator_17.setForeground(Color.BLACK);

		JSeparator separator_18 = new JSeparator();
		separator_18.setOrientation(SwingConstants.VERTICAL);
		separator_18.setForeground(Color.BLACK);

		JSeparator separator_19 = new JSeparator();
		separator_19.setOrientation(SwingConstants.VERTICAL);
		separator_19.setForeground(Color.BLACK);

		JSeparator separator_20 = new JSeparator();
		separator_20.setBackground(Color.BLACK);
		separator_20.setOrientation(SwingConstants.VERTICAL);
		separator_20.setForeground(Color.BLACK);

		JSeparator separator_21 = new JSeparator();
		separator_21.setOrientation(SwingConstants.VERTICAL);
		separator_21.setForeground(Color.BLACK);

		JSeparator separator_22 = new JSeparator();
		separator_22.setOrientation(SwingConstants.VERTICAL);
		separator_22.setForeground(Color.BLACK);

		JSeparator separator_23 = new JSeparator();
		separator_23.setBackground(Color.BLACK);
		separator_23.setOrientation(SwingConstants.VERTICAL);
		separator_23.setForeground(Color.BLACK);

		JSeparator separator_24 = new JSeparator();
		separator_24.setOrientation(SwingConstants.VERTICAL);
		separator_24.setForeground(Color.BLACK);

		JSeparator separator_25 = new JSeparator();
		separator_25.setOrientation(SwingConstants.VERTICAL);
		separator_25.setForeground(Color.BLACK);

		JSeparator separator_26 = new JSeparator();
		separator_26.setOrientation(SwingConstants.VERTICAL);
		separator_26.setForeground(Color.BLACK);

		JSeparator separator_27 = new JSeparator();
		separator_27.setOrientation(SwingConstants.VERTICAL);
		separator_27.setForeground(Color.BLACK);

		JSeparator separator_28 = new JSeparator();
		separator_28.setBackground(Color.BLACK);
		separator_28.setOrientation(SwingConstants.VERTICAL);
		separator_28.setForeground(Color.BLACK);

		JSeparator separator_29 = new JSeparator();
		separator_29.setOrientation(SwingConstants.VERTICAL);
		separator_29.setForeground(Color.BLACK);

		JSeparator separator_30 = new JSeparator();
		separator_30.setOrientation(SwingConstants.VERTICAL);
		separator_30.setForeground(Color.BLACK);

		JSeparator separator_31 = new JSeparator();
		separator_31.setBackground(Color.BLACK);
		separator_31.setOrientation(SwingConstants.VERTICAL);
		separator_31.setForeground(Color.BLACK);

		JSeparator separator_32 = new JSeparator();
		separator_32.setOrientation(SwingConstants.VERTICAL);
		separator_32.setForeground(Color.BLACK);

		JSeparator separator_33 = new JSeparator();
		separator_33.setOrientation(SwingConstants.VERTICAL);
		separator_33.setForeground(Color.BLACK);

		JSeparator separator_34 = new JSeparator();
		separator_34.setBackground(Color.BLACK);
		separator_34.setForeground(Color.BLACK);

		JSeparator separator_35 = new JSeparator();
		separator_35.setForeground(Color.BLACK);

		JSeparator separator_36 = new JSeparator();
		separator_36.setOrientation(SwingConstants.VERTICAL);
		separator_36.setForeground(Color.BLACK);

		JSeparator separator_37 = new JSeparator();
		separator_37.setOrientation(SwingConstants.VERTICAL);
		separator_37.setForeground(Color.BLACK);

		JSeparator separator_38 = new JSeparator();
		separator_38.setBackground(Color.BLACK);
		separator_38.setOrientation(SwingConstants.VERTICAL);
		separator_38.setForeground(Color.BLACK);

		JSeparator separator_39 = new JSeparator();
		separator_39.setOrientation(SwingConstants.VERTICAL);
		separator_39.setForeground(Color.BLACK);

		JSeparator separator_40 = new JSeparator();
		separator_40.setOrientation(SwingConstants.VERTICAL);
		separator_40.setForeground(Color.BLACK);

		JSeparator separator_41 = new JSeparator();
		separator_41.setBackground(Color.BLACK);
		separator_41.setOrientation(SwingConstants.VERTICAL);
		separator_41.setForeground(Color.BLACK);

		JSeparator separator_42 = new JSeparator();
		separator_42.setOrientation(SwingConstants.VERTICAL);
		separator_42.setForeground(Color.BLACK);

		JSeparator separator_43 = new JSeparator();
		separator_43.setOrientation(SwingConstants.VERTICAL);
		separator_43.setForeground(Color.BLACK);

		JSeparator separator_44 = new JSeparator();
		separator_44.setOrientation(SwingConstants.VERTICAL);
		separator_44.setForeground(Color.BLACK);

		JSeparator separator_45 = new JSeparator();
		separator_45.setOrientation(SwingConstants.VERTICAL);
		separator_45.setForeground(Color.BLACK);

		JSeparator separator_46 = new JSeparator();
		separator_46.setBackground(Color.BLACK);
		separator_46.setOrientation(SwingConstants.VERTICAL);
		separator_46.setForeground(Color.BLACK);

		JSeparator separator_47 = new JSeparator();
		separator_47.setOrientation(SwingConstants.VERTICAL);
		separator_47.setForeground(Color.BLACK);

		JSeparator separator_48 = new JSeparator();
		separator_48.setOrientation(SwingConstants.VERTICAL);
		separator_48.setForeground(Color.BLACK);

		JSeparator separator_49 = new JSeparator();
		separator_49.setBackground(Color.BLACK);
		separator_49.setOrientation(SwingConstants.VERTICAL);
		separator_49.setForeground(Color.BLACK);

		JSeparator separator_50 = new JSeparator();
		separator_50.setOrientation(SwingConstants.VERTICAL);
		separator_50.setForeground(Color.BLACK);

		JSeparator separator_51 = new JSeparator();
		separator_51.setOrientation(SwingConstants.VERTICAL);
		separator_51.setForeground(Color.BLACK);

		JSeparator separator_52 = new JSeparator();
		separator_52.setOrientation(SwingConstants.VERTICAL);
		separator_52.setForeground(Color.BLACK);

		JSeparator separator_53 = new JSeparator();
		separator_53.setOrientation(SwingConstants.VERTICAL);
		separator_53.setForeground(Color.BLACK);

		JSeparator separator_54 = new JSeparator();
		separator_54.setBackground(Color.BLACK);
		separator_54.setOrientation(SwingConstants.VERTICAL);
		separator_54.setForeground(Color.BLACK);

		JSeparator separator_55 = new JSeparator();
		separator_55.setOrientation(SwingConstants.VERTICAL);
		separator_55.setForeground(Color.BLACK);

		JSeparator separator_56 = new JSeparator();
		separator_56.setOrientation(SwingConstants.VERTICAL);
		separator_56.setForeground(Color.BLACK);

		JSeparator separator_57 = new JSeparator();
		separator_57.setBackground(Color.BLACK);
		separator_57.setOrientation(SwingConstants.VERTICAL);
		separator_57.setForeground(Color.BLACK);

		JSeparator separator_58 = new JSeparator();
		separator_58.setOrientation(SwingConstants.VERTICAL);
		separator_58.setForeground(Color.BLACK);

		JSeparator separator_59 = new JSeparator();
		separator_59.setOrientation(SwingConstants.VERTICAL);
		separator_59.setForeground(Color.BLACK);

		JSeparator separator_60 = new JSeparator();
		separator_60.setOrientation(SwingConstants.VERTICAL);
		separator_60.setForeground(Color.BLACK);

		JSeparator separator_61 = new JSeparator();
		separator_61.setOrientation(SwingConstants.VERTICAL);
		separator_61.setForeground(Color.BLACK);

		JSeparator separator_62 = new JSeparator();
		separator_62.setBackground(Color.BLACK);
		separator_62.setOrientation(SwingConstants.VERTICAL);
		separator_62.setForeground(Color.BLACK);

		JSeparator separator_63 = new JSeparator();
		separator_63.setOrientation(SwingConstants.VERTICAL);
		separator_63.setForeground(Color.BLACK);

		JSeparator separator_64 = new JSeparator();
		separator_64.setOrientation(SwingConstants.VERTICAL);
		separator_64.setForeground(Color.BLACK);

		JSeparator separator_65 = new JSeparator();
		separator_65.setBackground(Color.BLACK);
		separator_65.setOrientation(SwingConstants.VERTICAL);
		separator_65.setForeground(Color.BLACK);

		JSeparator separator_66 = new JSeparator();
		separator_66.setOrientation(SwingConstants.VERTICAL);
		separator_66.setForeground(Color.BLACK);

		JSeparator separator_67 = new JSeparator();
		separator_67.setOrientation(SwingConstants.VERTICAL);
		separator_67.setForeground(Color.BLACK);

		JSeparator separator_68 = new JSeparator();
		separator_68.setForeground(Color.BLACK);

		JSeparator separator_69 = new JSeparator();
		separator_69.setBackground(Color.BLACK);
		separator_69.setForeground(Color.BLACK);

		JSeparator separator_70 = new JSeparator();
		separator_70.setForeground(Color.BLACK);

		JSeparator separator_71 = new JSeparator();
		separator_71.setForeground(Color.BLACK);

		JSeparator separator_72 = new JSeparator();
		separator_72.setOrientation(SwingConstants.VERTICAL);
		separator_72.setForeground(Color.BLACK);

		JSeparator separator_73 = new JSeparator();
		separator_73.setOrientation(SwingConstants.VERTICAL);
		separator_73.setForeground(Color.BLACK);

		JSeparator separator_74 = new JSeparator();
		separator_74.setBackground(Color.BLACK);
		separator_74.setOrientation(SwingConstants.VERTICAL);
		separator_74.setForeground(Color.BLACK);

		JSeparator separator_75 = new JSeparator();
		separator_75.setOrientation(SwingConstants.VERTICAL);
		separator_75.setForeground(Color.BLACK);

		JSeparator separator_76 = new JSeparator();
		separator_76.setOrientation(SwingConstants.VERTICAL);
		separator_76.setForeground(Color.BLACK);

		JSeparator separator_77 = new JSeparator();
		separator_77.setBackground(Color.BLACK);
		separator_77.setOrientation(SwingConstants.VERTICAL);
		separator_77.setForeground(Color.BLACK);

		JSeparator separator_78 = new JSeparator();
		separator_78.setOrientation(SwingConstants.VERTICAL);
		separator_78.setForeground(Color.BLACK);

		JSeparator separator_79 = new JSeparator();
		separator_79.setOrientation(SwingConstants.VERTICAL);
		separator_79.setForeground(Color.BLACK);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(g00)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g10)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_2,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g20)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_3,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g30)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_4,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g40)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_5,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g50)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_6,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g60)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_7,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(g70)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_8,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE).addGap(6)
								.addComponent(g80)
								.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(g01, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_9,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g11, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_10,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g21, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_11,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g31, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_12,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g41, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_13,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g51, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_14,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g61, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_15,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g71, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_16,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g81, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g02,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_26,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g12,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_27,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g22,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_28,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g32,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_29,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g42,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_30,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g52,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_31,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g62,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_32,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g72,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_33,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g82,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g03,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_18,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g13,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_19,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g23,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_20,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g33,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_21,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g43,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_22,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g53,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_23,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g63,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_24,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g73,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_25,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g83,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(separator_35,
										GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g04,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_60,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g14,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_61,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g24,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_62,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g34,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_63,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g44,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_64,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g54,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_65,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g64,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_66,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g74,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_67,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g84,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g05,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_36,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g15,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_37,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g25,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_38,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g35,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_39,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g45,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_40,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g55,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_41,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g65,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_42,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g75,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_43,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g85,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g06,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_52,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g16,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_53,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g26,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_54,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g36,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_55,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g46,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_56,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g56,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_57,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g66,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_58,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g76,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_59,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g86,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		g07,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_44,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g17,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_45,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g27,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_46,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g37,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_47,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g47,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_48,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g57,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_49,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g67,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_50,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g77,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		separator_51,
																		GroupLayout.PREFERRED_SIZE,
																		12,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(
																		g87,
																		GroupLayout.PREFERRED_SIZE,
																		8,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(separator_68,
										GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(separator_69,
										GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(separator_70,
										GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(separator_71,
										GroupLayout.PREFERRED_SIZE, 283,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(g08, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_72,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g18, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_73,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g28, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_74,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g38, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_75,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g48, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_76,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g58, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_77,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g68, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_78,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g78, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(separator_79,
										GroupLayout.PREFERRED_SIZE, 12,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(g88, GroupLayout.PREFERRED_SIZE,
										8, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING, false)
												.addComponent(separator,
														Alignment.LEADING)
												.addComponent(separator_17,
														Alignment.LEADING)
												.addComponent(
														separator_34,
														Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														283, Short.MAX_VALUE))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g80,
														GroupLayout.DEFAULT_SIZE,
														19, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.TRAILING)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						g00,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addGroup(
																						gl_panel.createParallelGroup(
																								Alignment.BASELINE)
																								.addComponent(
																										g10,
																										GroupLayout.DEFAULT_SIZE,
																										16,
																										Short.MAX_VALUE)
																								.addComponent(
																										g20,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										g30,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										g40,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										g50,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										g60,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										g70,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE))
																				.addComponent(
																						separator_2,
																						GroupLayout.PREFERRED_SIZE,
																						19,
																						GroupLayout.PREFERRED_SIZE))
																.addComponent(
																		separator_1,
																		GroupLayout.PREFERRED_SIZE,
																		19,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														separator_3,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_4,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_5,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_6,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_7,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_8,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g01,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_9,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g11)
												.addComponent(
														separator_10,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g21)
												.addComponent(
														separator_11,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g31)
												.addComponent(
														separator_12,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g41)
												.addComponent(
														separator_13,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g51)
												.addComponent(
														separator_14,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g61)
												.addComponent(
														separator_15,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g71)
												.addComponent(
														separator_16,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g81,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_17,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g02,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_26,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g12)
												.addComponent(
														separator_27,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g22)
												.addComponent(
														separator_28,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g32)
												.addComponent(
														separator_29,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g42)
												.addComponent(
														separator_30,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g52)
												.addComponent(
														separator_31,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g62)
												.addComponent(
														separator_32,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g72)
												.addComponent(
														separator_33,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g82,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_34,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g03,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_18,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g13)
												.addComponent(
														separator_19,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g23)
												.addComponent(
														separator_20,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g33)
												.addComponent(
														separator_21,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g43)
												.addComponent(
														separator_22,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g53)
												.addComponent(
														separator_23,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g63)
												.addComponent(
														separator_24,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g73)
												.addComponent(
														separator_25,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g83,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_35,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g04,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_60,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g14)
												.addComponent(
														separator_61,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g24)
												.addComponent(
														separator_62,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g34)
												.addComponent(
														separator_63,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g44)
												.addComponent(
														separator_64,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g54)
												.addComponent(
														separator_65,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g64)
												.addComponent(
														separator_66,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g74)
												.addComponent(
														separator_67,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g84,
														GroupLayout.PREFERRED_SIZE,
														22,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_68,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g05,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_36,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g15)
												.addComponent(
														separator_37,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g25)
												.addComponent(
														separator_38,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g35)
												.addComponent(
														separator_39,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g45)
												.addComponent(
														separator_40,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g55)
												.addComponent(
														separator_41,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g65)
												.addComponent(
														separator_42,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g75)
												.addComponent(
														separator_43,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g85,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_69,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(3)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g06,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_52,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g16)
												.addComponent(
														separator_53,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g26)
												.addComponent(
														separator_54,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g36)
												.addComponent(
														separator_55,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g46)
												.addComponent(
														separator_56,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g56)
												.addComponent(
														separator_57,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g66)
												.addComponent(
														separator_58,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g76)
												.addComponent(
														separator_59,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g86,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_70,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g07,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_44,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g17)
												.addComponent(
														separator_45,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g27)
												.addComponent(
														separator_46,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g37)
												.addComponent(
														separator_47,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g47)
												.addComponent(
														separator_48,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g57)
												.addComponent(
														separator_49,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g67)
												.addComponent(
														separator_50,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g77)
												.addComponent(
														separator_51,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g87,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_71,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														g08,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														separator_72,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g18)
												.addComponent(
														separator_73,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g28)
												.addComponent(
														separator_74,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g38)
												.addComponent(
														separator_75,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g48)
												.addComponent(
														separator_76,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g58)
												.addComponent(
														separator_77,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g68)
												.addComponent(
														separator_78,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(g78)
												.addComponent(
														separator_79,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														g88,
														GroupLayout.PREFERRED_SIZE,
														19,
														GroupLayout.PREFERRED_SIZE))
								.addGap(373)));
		panel.setLayout(gl_panel);
		this.setLayout(groupLayout);
	}
}
