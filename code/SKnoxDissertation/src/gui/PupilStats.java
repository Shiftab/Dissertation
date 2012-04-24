package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import extras.Stats;

public class PupilStats extends JPanel {

	JLabel name = new JLabel("pupil"), asked = new JLabel("asked"),
			answers = new JLabel("answer"), input = new JLabel("input"),
			shyTime = new JLabel("shy"),
			distractTime = new JLabel("distracted");
	JButton btnBack = new JButton("Back");
	private JLabel lblGraph = new JLabel("");
	
	long startTime, timeLimit;

	public void setUp(String name, Stats stats, XYSeries graph, long startTime, long endTime) {
		this.startTime = startTime;
		this.timeLimit = endTime;
		createGraph(graph, stats);
		this.name.setText(name);

		asked.setText(String.valueOf(stats.getAsked()));
		answers.setText(String.valueOf(stats.getAnswered()));
		input.setText(String.valueOf(((stats.getAsked() + stats.getAnswered()) / ((stats
				.getQuestions() + stats.getAsked()) * 2.0)) * 100.0));
		shyTime.setText(String.valueOf(stats.getShyMissed()));
		distractTime.setText(String.valueOf(stats.getDistractions()));
	}

	private void createGraph(XYSeries graph, Stats stats) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(graph);

		JFreeChart chart = ChartFactory.createXYLineChart(this.name.getText(),
				"Time", "Input", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		XYPlot plot = chart.getXYPlot();
		boolean toggle = true;
		for (Long l : stats.getShyTimes()) {
			Marker currentEnd = new ValueMarker(100-((((startTime + (timeLimit * 6000)) - l) / 6000.0) / timeLimit) * 100);
			currentEnd.setPaint(Color.red);
			if (toggle)
				currentEnd.setLabel("Became Shy");
			else
				currentEnd.setLabel("Stoped being Shy");
			currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
			currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
			plot.addDomainMarker(currentEnd);
			toggle=!toggle;
		}

		BufferedImage image = chart.createBufferedImage(500, 300);
		lblGraph.setIcon(new ImageIcon(image));
		setVisible(true);

	}

	/**
	 * Create the panel.
	 */
	public PupilStats() {

		JSeparator separator = new JSeparator();

		JLabel label_1 = new JLabel("Questions Asked: ");

		JLabel label_3 = new JLabel("Questions Answered:");

		JLabel label_5 = new JLabel("Percentage Input:");

		JLabel label_7 = new JLabel("Time wasted due to Shyness:");

		JLabel label_9 = new JLabel("Time wasted due to distractions: ");

		JButton btnBack = new JButton("Back");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																separator,
																GroupLayout.PREFERRED_SIZE,
																1,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_3,
																				GroupLayout.PREFERRED_SIZE,
																				133,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				answers,
																				GroupLayout.PREFERRED_SIZE,
																				44,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_1,
																				GroupLayout.PREFERRED_SIZE,
																				115,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				asked,
																				GroupLayout.PREFERRED_SIZE,
																				73,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_5,
																				GroupLayout.PREFERRED_SIZE,
																				109,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				input,
																				GroupLayout.PREFERRED_SIZE,
																				33,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_7,
																				GroupLayout.PREFERRED_SIZE,
																				182,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				shyTime,
																				GroupLayout.PREFERRED_SIZE,
																				22,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_9,
																				GroupLayout.PREFERRED_SIZE,
																				210,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				distractTime,
																				GroupLayout.PREFERRED_SIZE,
																				63,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnBack))
														.addComponent(
																name,
																GroupLayout.PREFERRED_SIZE,
																139,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGraph))
										.addContainerGap(72, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 1,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblGraph)
										.addPreferredGap(
												ComponentPlacement.RELATED, 52,
												Short.MAX_VALUE)
										.addComponent(name)
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(34)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								label_3)
																						.addComponent(
																								answers)))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				label_1)
																		.addComponent(
																				asked)))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_5)
														.addComponent(input))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_7)
														.addComponent(shyTime))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_9)
														.addComponent(
																distractTime)
														.addComponent(btnBack))
										.addGap(20)));
		setLayout(groupLayout);

	}
}
