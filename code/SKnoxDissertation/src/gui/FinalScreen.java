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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class FinalScreen extends JPanel {

	private Control parent;
	DecimalFormat format = new DecimalFormat("#.##");
	JLabel lbTimeTaken = new JLabel("time"), lbPercentageCompleat = new JLabel(
			"percent");

	private final JLabel lbFormat = new JLabel(" "), graphDisplay = new JLabel("");
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
		initialize();
	}

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
		for(XYSeries s: graphData.getSeries())
			dataset.addSeries(s);

		JFreeChart chart = ChartFactory.createXYLineChart("Group Data",
				"Time", "Input", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		BufferedImage image = chart.createBufferedImage(500, 300);
		graphDisplay.setIcon(new ImageIcon(image));
		
		this.startTime = startTime;
		this.timeLimit = timeLimit;
		graph = graphData;
		lbTimeTaken.setText(formatTime(timeTaken));
		if (percentCompleat == 100)
			lbPercentageCompleat.setText(String.valueOf(format
					.format(percentCompleat)));
		else
			lbPercentageCompleat.setText(String.valueOf(format
					.format(100 - percentCompleat)));

	}

	private String formatTime(long l){
		String ans = "";
		String what = String.valueOf(l/6000.0);
		String split[] = what.split("\\.");
		ans+=split[0]+":";
		try{
		ans+=String.valueOf(Integer.valueOf(split[1])*60).substring(2);
		}catch(NumberFormatException nf){ //can start with 0

			ans+=String.valueOf(Integer.valueOf(split[1].substring(1, split[1].length()+1))*60).substring(2);
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
				parent.pupilSummery(lblName3.getText(),
						pupils.get(lblName3.getText()),
						graph.getSeries(lblName3.getText()));
			}
		});

		btnDetails4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pupils.clear();
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblPercentageCompleat, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lbPercentageCompleat))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblName2, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(lblName3, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(lblName4, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(lblName5, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addComponent(lblName6, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDetails1)
							.addGap(6)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(graphDisplay, GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
							.addGap(69)
							.addComponent(lblPupils, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDetails6, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDetails5, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDetails4, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDetails3, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDetails2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(1286, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSummary, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTimeTaken, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbTimeTaken)))
					.addGap(153)
					.addComponent(btnRestart)
					.addGap(646)
					.addComponent(lbFormat)
					.addGap(396))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSummary)
						.addComponent(btnRestart)
						.addComponent(lbFormat))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTimeTaken)
						.addComponent(lbTimeTaken))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPercentageCompleat)
						.addComponent(lbPercentageCompleat))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addComponent(lblPupils))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnDetails1)
										.addComponent(lblName1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnDetails2)
										.addComponent(lblName2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnDetails3)
										.addComponent(lblName3))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDetails4)
								.addComponent(lblName4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDetails5)
								.addComponent(lblName5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName6)
								.addComponent(btnDetails6))
							.addGap(282))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(graphDisplay, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addContainerGap())))
		);
		setLayout(groupLayout);
		setVisible(true);
	}

	private double time(long time) {
		return time / 6000.0; // to minuets
	}
}
