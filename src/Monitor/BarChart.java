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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Millisecond;
/**
* A simple demonstration application showing how to create a bar chart.
*/
public class BarChart extends Panel {
/**
* Creates a new demo instance.
*
* @param title the frame title.
*/
	
private DefaultCategoryDataset dataset;
public BarChart() {
super(new BorderLayout());

dataset = new DefaultCategoryDataset();
dataset.addValue(1.0, "Row 1", "Column 1");
dataset.addValue(5.0, "Row 1", "Column 2");
dataset.addValue(3.0, "Row 1", "Column 3");
dataset.addValue(2.0, "Row 2", "Column 1");
dataset.addValue(3.0, "Row 2", "Column 2");
dataset.addValue(2.0, "Row 2", "Column 3");
JFreeChart chart = ChartFactory.createBarChart(
"Bar Chart Demo", // chart title
"Category", // domain axis label
"Value", // range axis label
dataset, // data
PlotOrientation.VERTICAL, // orientation
true, // include legend
true, // tooltips?
false // URLs?
);
ChartPanel chartPanel = new ChartPanel(chart, true);
chartPanel.setPreferredSize(new Dimension(500, 270));
chart.setBackgroundPaint(Color.white);
chartPanel.setBorder(BorderFactory.createCompoundBorder(
BorderFactory.createEmptyBorder(4, 4, 4, 4),
BorderFactory.createLineBorder(Color.black)));
add(chartPanel);
}


private void addTotalObservation(double y) {
dataset.addValue(y, "Row 1", "Column 1");
this.invalidate();
}
/**
* Adds an observation to the ’free memory’ time series.
*
* @param y the free memory.
*/
//private void addFreeObservation(double y) {
//this.free.add(new Millisecond(), y);
//}
/**
* The data generator.
*/
class DataGenerator extends Timer implements ActionListener {
/**
* Constructor.
*
* @param interval the interval (in milliseconds)
*/
DataGenerator(int interval) {
super(interval, null);
addActionListener(this);
}
/**
* Adds a new free/total memory reading to the dataset.
*
* @param event the action event.
*/
public void actionPerformed(ActionEvent event) {

addTotalObservation(Math.random()*100);
}
}
/**
* Entry point for the sample application.
*
* @param args ignored.
*/

}


