package gui;

import java.awt.BasicStroke;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import control.Control;

import extras.Stats;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

/**
 * class to handel the final pupil summary pages
 * 
 * @author Steven Knox
 * 
 */
@SuppressWarnings("serial")
public class PupilStats extends JPanel {

	JLabel name = new JLabel("pupil"), asked = new JLabel("asked"),
			answers = new JLabel("answer"), input = new JLabel("input"),
			shyTime = new JLabel("shy"),
			distractTime = new JLabel("distracted");
	JButton btnBack = new JButton("Back");
	private JLabel lblGraph = new JLabel("");
	private Control parent;
	long startTime, timeLimit;

	/**
	 * contrutor to set the parent
	 * 
	 * @param p
	 */
	public PupilStats(Control p) {
		parent = p;
		setOpaque(false);
		init();
	}

	/**
	 * method to setup the screens values
	 * 
	 * @param name
	 * @param stats
	 * @param graph
	 * @param startTime
	 * @param endTime
	 */
	public void setUp(String name, Stats stats, XYSeries graph, long startTime,
			long endTime) {
		this.startTime = startTime;
		this.timeLimit = endTime;
		this.name.setText(name);

		asked.setText(String.valueOf(stats.getAsked()));
		answers.setText(String.valueOf(stats.getAnswered()));
		input.setText(String.valueOf((int) (((stats.getAsked() + stats
				.getAnswered()) / ((stats.getQuestions()) * 2.0)) * 100.0)));
		shyTime.setText(String.valueOf(formatTime(stats.getShyMissed())));
		distractTime.setText(formatTime(stats.getDistractions() / 6000));
		createGraph(graph, stats);
	}

	/**
	 * method to convert the long time values into simulation minuets and
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
	 * method to setup the individual user graph
	 * 
	 * @param graph
	 * @param stats
	 */
	private void createGraph(XYSeries graph, Stats stats) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(graph);

		JFreeChart chart = ChartFactory.createXYLineChart(this.name.getText(),
				"Time", "Input", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		// introvert
		XYPlot plot = chart.getXYPlot();
		boolean toggle = true;
		Long lastTime = Long.MIN_VALUE;
		for (Long l : stats.getShyTimes()) {
			Marker currentEnd = new ValueMarker(
					100 - ((((startTime + (timeLimit * 6000)) - l) / 6000.0) / timeLimit) * 100);
			currentEnd.setPaint(Color.yellow);
			currentEnd.setStroke(new BasicStroke(2f));
			if (toggle)
				currentEnd.setLabel("Became Introverted");
			else
				currentEnd.setLabel("Stoped being Introverted");
			currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
			currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
			plot.addDomainMarker(currentEnd);
			toggle = !toggle;
		}

		// Distraction attempt
		toggle = true;
		for (Long l : stats.getDistractTimes()) {
			Marker currentEnd = new ValueMarker(
					100 - ((((startTime + (timeLimit * 6000)) - l) / 6000.0) / timeLimit) * 100);
			if (toggle)
				currentEnd.setLabel("Distract");
			if (l > lastTime + 500) {
				currentEnd.setPaint(Color.blue);
				currentEnd.setStroke(new BasicStroke(2f));
				currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
				currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
				plot.addDomainMarker(currentEnd);
				toggle = !toggle;
			}
			lastTime = l;
		}

		// Focus
		toggle = true;
		Map<String, List<Long>> pupilFocus = stats.getFocus();
		for (String s : pupilFocus.keySet()) {
			for (long l : pupilFocus.get(s)) {
				Marker currentEnd = new ValueMarker(
						100 - ((((startTime + (timeLimit * 6000)) - l) / 6000.0) / timeLimit) * 100);

				currentEnd.setLabel("Focused on " + s);
				currentEnd.setPaint(Color.green);
				currentEnd.setStroke(new BasicStroke(2f));
				currentEnd.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
				currentEnd.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
				plot.addDomainMarker(currentEnd);

				lastTime = l;
			}
		}

		// Distracted
		toggle = true;
		Map<String, List<Long>> distracted = stats.getDistractedTimes();
		for (String s : distracted.keySet()) {
			for (long l : distracted.get(s)) {
				Marker currentEnd = new ValueMarker(
						100 - ((((startTime + (timeLimit * 6000)) - l) / 6000.0) / timeLimit) * 100);
				if (toggle)
					currentEnd.setLabel("Distracted by " + s);
				if (l > lastTime + 500) {
					currentEnd.setPaint(Color.black);
					currentEnd.setStroke(new BasicStroke(2f));
					currentEnd.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
					currentEnd.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
					plot.addDomainMarker(currentEnd);
					toggle = !toggle;
				}

				lastTime = l;
			}
		}

		BufferedImage image = chart.createBufferedImage(500, 300);
		lblGraph.setIcon(new ImageIcon(image));
		setVisible(true);

	}

	/**
	 * Create the panel.
	 */
	public void init() {

		JSeparator separator = new JSeparator();

		JLabel label_1 = new JLabel("Questions Asked: ");

		JLabel label_3 = new JLabel("Questions Answered:");

		JLabel label_5 = new JLabel("Percentage Input:");

		JLabel label_7 = new JLabel("Time wasted due to Shyness:");

		JLabel label_9 = new JLabel("Time wasted due to distractions: ");

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				parent.changeView(Control.SUM);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 1,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(1005, Short.MAX_VALUE))
						.addGroup(
								groupLayout.createSequentialGroup()
										.addContainerGap(469, Short.MAX_VALUE)
										.addComponent(btnBack).addGap(468))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(367)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
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
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																name,
																GroupLayout.PREFERRED_SIZE,
																139,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
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
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.addComponent(
																								label_7,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								182,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				shyTime,
																				GroupLayout.DEFAULT_SIZE,
																				30,
																				Short.MAX_VALUE)
																		.addGap(61)))
										.addGap(366))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(257)
										.addComponent(lblGraph,
												GroupLayout.PREFERRED_SIZE,
												498, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(257, Short.MAX_VALUE)));
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
										.addGap(61)
										.addComponent(lblGraph,
												GroupLayout.PREFERRED_SIZE,
												301, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
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
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								label_1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								asked))
																		.addGap(34)))
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
																distractTime))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnBack).addGap(121)));
		setLayout(groupLayout);
		setVisible(true);
	}
}
