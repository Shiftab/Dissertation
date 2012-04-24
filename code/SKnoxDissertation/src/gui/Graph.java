package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Graph extends JPanel {

	XYSeries series1 = new XYSeries("aberage size1");
	XYSeries series2 = new XYSeries("Average Size2");
	XYSeries series3 = new XYSeries("Average Size3");
	XYSeries series4 = new XYSeries("Average Size4");
	XYSeries series5 = new XYSeries("Average Size5");
	XYSeries series6 = new XYSeries("Average Size6");

	volatile XYSeriesCollection dataset = new XYSeriesCollection();
	JFreeChart chart;
	JLabel lblGraph = new JLabel("New label");
	List<String> names = new ArrayList<String>();

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
	}

	public void setNames(List<String> names) {
		this.names = names;
		int count = 0;
		for (String n : names) {
			switch (count) {
			case 0:
				series1.setKey(n);
				series1.add(0, 0);
				break;
			case 1:
				series2.setKey(n);
				series2.add(0, 0);
				break;
			case 2:
				series3.setKey(n);
				series3.add(0, 0);
				break;
			case 3:
				series4.setKey(n);
				series4.add(0, 0);
				break;
			case 4:
				series5.setKey(n);
				series5.add(0, 0);
				break;
			case 5:
				series6.setKey(n);
				series6.add(0, 0);
				break;
			}
			count++;
		}

	}

	public synchronized void updateSeries(String name, double time, double input) {
		if (name.equals(names.get(0))) {
			series1.add(time, input);
		} else if (name.equals(names.get(1))) {
			series2.add(time, input);
		} else if (name.equals(names.get(2))) {
			series3.add(time, input);
		} else if (name.equals(names.get(3))) {
			series4.add(time, input);
		} else if (name.equals(names.get(4))) {
			series5.add(time, input);
		} else if (name.equals(names.get(5))) {
			series6.add(time, input);
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

	public synchronized void updateChart() {
		dataset.removeAllSeries();
		for (int count = 0; count < names.size(); count++) {
			try {
				switch (count + 1) {
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
			} catch (Exception ex) {
				System.out.println("Happend again");
			}
		}
		chart = ChartFactory.createXYLineChart("Group Activity", "Time", "Input", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		display();
	}

	public List<XYSeries> getSeries(){
		List<XYSeries> series = new ArrayList<XYSeries>();
		if(!series1.isEmpty())
			series.add(series1);
		
		if(!series2.isEmpty())
			series.add(series2);
		
		if(!series3.isEmpty())
			series.add(series3);
		
		if(!series4.isEmpty())
			series.add(series4);
		
		if(!series5.isEmpty())
			series.add(series5);
		
		if(!series6.isEmpty())
			series.add(series6);
		
		return series;
	}
	
	public XYSeries getSeries(String name){
		XYSeries ans = null;
		
		if(series1.getKey().equals(name))
			ans = series1;
		else if(series2.getKey().equals(name))
			ans = series2;
		else if(series3.getKey().equals(name))
			ans = series3;
		else if(series4.getKey().equals(name))
			ans = series4;
		else if(series5.getKey().equals(name))
			ans = series5;
		else if(series6.getKey().equals(name))
			ans = series6;
		
		return ans;
	}
	
	public synchronized void display() {
		 
		BufferedImage image = chart.createBufferedImage(500, 300);
		lblGraph.setIcon(new ImageIcon(image));
		setVisible(true);
	}
}
