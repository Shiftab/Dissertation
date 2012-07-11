package gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import control.Control;
import extras.Stats;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JSeparator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

/**
 * JPanel extention for the final summary screen
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class FinalScreen extends JPanel {

	private Control parent;
	DecimalFormat format = new DecimalFormat("#.##");

	JLabel lbTimeTaken = new JLabel("time"), lbPercentageCompleat = new JLabel(
			"percent");

	private final JLabel lbFormat = new JLabel(""), graphDisplay = new JLabel(
			"");

	private final JButton btnRestart = new JButton("Re-Start");
	private final JButton btnDetails1 = new JButton("Details");
	private final JButton btnDetails2 = new JButton("Details");
	private final JButton btnDetails3 = new JButton("Details");
	private final JButton btnDetails4 = new JButton("Details");
	private final JButton btnDetails5 = new JButton("Details");
	private final JButton btnDetails6 = new JButton("Details");

	private JLabel lblName1 = new JLabel("name1"), lblName2 = new JLabel(
			"name1"), lblName3 = new JLabel("name1"), lblName4 = new JLabel(
			"name1"), lblName5 = new JLabel("name1"), lblName6 = new JLabel(
			"name1");

	private List<JLabel> names = new ArrayList<JLabel>(Arrays.asList(lblName1,
			lblName2, lblName3, lblName4, lblName5, lblName6));

	private List<JButton> buttons = new ArrayList<JButton>(Arrays.asList(
			btnDetails1, btnDetails2, btnDetails3, btnDetails4, btnDetails5,
			btnDetails6));

	private Graph graph = new Graph();
	long startTime, timeLimit;
	Map<String, Stats> pupils;

	/**
	 * Create the application.
	 */
	public FinalScreen(Control parent) {
		this.parent = parent;
		setOpaque(false);
		initialize();
	}

	/**
	 * method to display the summary screen
	 * 
	 * @param pupil
	 * @param percentCompleat
	 * @param timeTaken
	 * @param graphData
	 */
	public void display(Map<String, Stats> pupil, double percentCompleat,
			long timeTaken, Graph graphData) {
		this.pupils = pupil;
		int count = 0;

		for (String s : pupil.keySet()) {
			names.get(count).setText(s);
			names.get(count).setVisible(true);
			buttons.get(count).setVisible(true);
			count++;
		}

		for (; count < names.size(); count++) {
			names.get(count).setVisible(false);
			buttons.get(count).setVisible(false);
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		for (XYSeries s : graphData.getSeries())
			dataset.addSeries(s);

		JFreeChart chart = ChartFactory.createXYLineChart("Group Data", "Time",
				"Input", dataset, PlotOrientation.VERTICAL, true, true, false);

		BufferedImage image = chart.createBufferedImage(500, 300);
		graphDisplay.setIcon(new ImageIcon(image));

		graph = graphData;
		lbTimeTaken.setText(formatTime(timeTaken));
		if (percentCompleat == 100)
			lbPercentageCompleat.setText(String.valueOf(format
					.format(percentCompleat)));
		else
			lbPercentageCompleat.setText(String.valueOf(format
					.format(100 - percentCompleat)));

	}

	/**
	 * method to format the long values for times into simulation minuets and
	 * seconds
	 * 
	 * @param l
	 * @return
	 */
	private String formatTime(long l) {
		String ans = "";
		String what = String.valueOf(l / 6000.0);
		String split[] = what.split("\\.");
		ans += split[0] + ":";
		try {
			String sec = String
					.valueOf((int) (Double.valueOf("0." + split[1]) * 60));
			if (sec.length() < 2)
				sec += "0";
			ans += sec;
		} catch (NumberFormatException nf) {
		}
		return ans;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		btnDetails1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName1.getText(),
						pupils.get(lblName1.getText()),
						graph.getSeries(lblName1.getText()));
			}
		});

		btnDetails2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName2.getText(),
						pupils.get(lblName2.getText()),
						graph.getSeries(lblName2.getText()));
			}
		});

		btnDetails3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName3.getText(),
						pupils.get(lblName3.getText()),
						graph.getSeries(lblName3.getText()));
			}
		});

		btnDetails4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName4.getText(),
						pupils.get(lblName4.getText()),
						graph.getSeries(lblName4.getText()));
			}
		});

		btnDetails5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName5.getText(),
						pupils.get(lblName5.getText()),
						graph.getSeries(lblName5.getText()));
			}
		});

		btnDetails6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.pupilSummery(lblName6.getText(),
						pupils.get(lblName6.getText()),
						graph.getSeries(lblName6.getText()));
			}
		});

		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			//	for(String s: pupils.keySet())
			//		pupils.get(s).clear();
				graph = null;
				pupils = null;
				System.gc();
				setVisible(false);
				parent.changeView(Control.MAIN);
				parent.resetProblem();
			}
		});

		JLabel lblTimeTaken = new JLabel("Time Taken: ");

		JLabel lblPercentageCompleat = new JLabel("Percentage Compleat: ");

		JSeparator separator = new JSeparator();

		JLabel lblPupils = new JLabel("Pupils");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(111)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblPercentageCompleat,
																				GroupLayout.PREFERRED_SIZE,
																				152,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)
																		.addComponent(
																				lbPercentageCompleat,
																				GroupLayout.PREFERRED_SIZE,
																				55,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblName1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblName2,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblName3,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblName4,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblName5,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblName6,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnDetails1)
																										.addGap(6)
																										.addComponent(
																												separator,
																												GroupLayout.PREFERRED_SIZE,
																												1,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								btnDetails5,
																								GroupLayout.PREFERRED_SIZE,
																								88,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnDetails4,
																								GroupLayout.PREFERRED_SIZE,
																								88,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnDetails3,
																								GroupLayout.PREFERRED_SIZE,
																								88,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnDetails2,
																								GroupLayout.PREFERRED_SIZE,
																								88,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnDetails6,
																								GroupLayout.PREFERRED_SIZE,
																								88,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				graphDisplay,
																				GroupLayout.PREFERRED_SIZE,
																				498,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblSummary,
																				GroupLayout.PREFERRED_SIZE,
																				99,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(187)
																		.addComponent(
																				btnRestart)
																		.addGap(265))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblTimeTaken,
																				GroupLayout.PREFERRED_SIZE,
																				99,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lbTimeTaken,
																				GroupLayout.PREFERRED_SIZE,
																				38,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGap(910)
										.addComponent(lblPupils,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(
								groupLayout.createSequentialGroup()
										.addGap(1028).addComponent(lbFormat)
										.addGap(396)));
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
																		.addComponent(
																				lbFormat)
																		.addGap(215)
																		.addComponent(
																				lblPupils))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(103)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblSummary)
																						.addComponent(
																								btnRestart))
																		.addGap(21)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblTimeTaken)
																						.addComponent(
																								lbTimeTaken))
																		.addGap(5)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblPercentageCompleat)
																						.addComponent(
																								lbPercentageCompleat))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												separator,
																												GroupLayout.PREFERRED_SIZE,
																												1,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																btnDetails1)
																														.addComponent(
																																lblName1))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																btnDetails2)
																														.addComponent(
																																lblName2))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																btnDetails3)
																														.addComponent(
																																lblName3))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																btnDetails4)
																														.addComponent(
																																lblName4))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblName5)
																														.addComponent(
																																btnDetails5))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																lblName6)
																														.addComponent(
																																btnDetails6)))
																						.addComponent(
																								graphDisplay,
																								GroupLayout.PREFERRED_SIZE,
																								342,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(103, Short.MAX_VALUE)));
		setLayout(groupLayout);
		setVisible(true);
	}
}
