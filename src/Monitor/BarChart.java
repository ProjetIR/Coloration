package Monitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Millisecond;
/**
* Classe permettant de représenter une diagramme en bar
*/
public class BarChart extends Panel {
/**
* Creates a new demo instance.
*
* @param title the frame title.
*/
	
private DefaultCategoryDataset dataset;
private ChartPanel chartPanel;
private JFreeChart chart;
public BarChart(String title) {
super(new BorderLayout());

dataset = new DefaultCategoryDataset();
chart = ChartFactory.createBarChart(
title, // chart title
"Category", // domain axis label
"Value", // range axis label
dataset, // data
PlotOrientation.VERTICAL, // orientation
true, // include legend
true, // tooltips?
false // URLs?
);
chartPanel = new ChartPanel(chart, false);
chartPanel.setPreferredSize(new Dimension(500, 270));
chart.setBackgroundPaint(Color.white);
chartPanel.setBorder(BorderFactory.createCompoundBorder(
BorderFactory.createEmptyBorder(4, 4, 4, 4),
BorderFactory.createLineBorder(Color.black)));
add(chartPanel);
}


public void addTotalObservation(Color[] tab,int[] values) {
	
dataset.addValue(1, "Row 1", "Color 1");	
dataset.addValue(2, "Row 1", "Color 2");
CategoryPlot plot = (CategoryPlot)chart.getPlot();
CustomRenderer renderer = new CustomRenderer(tab);
renderer.setDrawBarOutline(false);
plot.setRenderer(renderer);
this.invalidate();
}


}


