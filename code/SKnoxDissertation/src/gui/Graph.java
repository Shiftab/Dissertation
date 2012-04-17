package gui;

import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Graph extends JPanel {

	XYSeries series1 = new XYSeries("Average Size");
	XYSeries series2 = new XYSeries("Average Size2");
	XYSeries series3 = new XYSeries("Average Size3");
	XYSeries series4 = new XYSeries("Average Size4");
	XYSeries series5 = new XYSeries("Average Size5");
	XYSeries series6 = new XYSeries("Average Size6");

	XYSeriesCollection dataset = new XYSeriesCollection();
	JFreeChart chart;
	JLabel lblGraph = new JLabel("New label");

	/**
	 * Create the panel.
	 */
	public Graph() {

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(lblGraph, GroupLayout.PREFERRED_SIZE,
								501, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(37, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addComponent(lblGraph)
						.addContainerGap(27, Short.MAX_VALUE)));
		setLayout(groupLayout);
		series1.add(100, 0.3);
		series1.add(200, 0.5);
		series1.add(300, 0.7);
		
		series2.add(100, 0.7);
		series2.add(200, 0.5);
		series2.add(300, 0.2);
		updateChart(2);
		display();
		
	}

	public void updateSeries(long time, List<Double> input) {
		int count = 1;
		for (Double in : input) {
			switch (count) {
			case 1:
				series1.add(time, in);
				break;
			case 2:
				series2.add(time, in);
				break;
			case 3:
				series3.add(time, in);
				break;
			case 4:
				series4.add(time, in);
				break;
			case 5:
				series5.add(time, in);
				break;
			case 6:
				series6.add(time, in);
				break;
			}
			count++;
		}
	}

	public void clearSeries() {
		series1.clear();
		series2.clear();
		series3.clear();
		series4.clear();
		series5.clear();
		series6.clear();
	}

	public void updateChart(int numPupils) {
		for (int count = 0; count < numPupils; count++) {
			switch (count+1) {
			case 1:
				dataset.addSeries(series1);
				break;
			case 2:
				dataset.addSeries(series2);
				break;
			case 3:
				dataset.addSeries(series3);
				break;
			case 4:
				dataset.addSeries(series4);
				break;
			case 5:
				dataset.addSeries(series5);
				break;
			case 6:
				dataset.addSeries(series6);
				break;
			}
		}
		chart = ChartFactory.createXYLineChart("temp", "X", "Y", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		display();
	}

	public void display() {
		
		BufferedImage image = chart.createBufferedImage(500, 300);
		lblGraph.setIcon(new ImageIcon(image));
		setVisible(true);
	}
}


