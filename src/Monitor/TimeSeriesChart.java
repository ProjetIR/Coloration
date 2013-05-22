package Monitor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Timer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
/**
*Classe représentant un panel destiné à afficher des données temporelles
*/
public class TimeSeriesChart extends Panel
 {
/** Time series for total memory used. */
private TimeSeries total;



public TimeSeriesChart(String title, String xlab,String ylab) {
super(new BorderLayout());
// create two series that automatically discard data more than 30
 // seconds old...
this.total = new TimeSeries(title, Second.class); // déprécier depuis peu ????
TimeSeriesCollection dataset = new TimeSeriesCollection();
dataset.addSeries(this.total);
DateAxis domain = new DateAxis(xlab);
NumberAxis range = new NumberAxis(ylab);
domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
renderer.setSeriesPaint(0, Color.red);
renderer.setSeriesPaint(1, Color.green);
renderer.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT,
BasicStroke.JOIN_BEVEL)); // pareil ????
XYPlot plot = new XYPlot(dataset, domain, range, renderer);
plot.setBackgroundPaint(Color.lightGray);
plot.setDomainGridlinePaint(Color.white);
plot.setRangeGridlinePaint(Color.white);
plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
domain.setAutoRange(true);
domain.setLowerMargin(0.0);
domain.setUpperMargin(0.0);
domain.setTickLabelsVisible(true);
range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
JFreeChart chart = new JFreeChart("",
new Font("SansSerif", Font.BOLD, 24), plot, true);
chart.setBackgroundPaint(Color.white);
ChartPanel chartPanel = new ChartPanel(chart);
chartPanel.setBorder(BorderFactory.createCompoundBorder(
BorderFactory.createEmptyBorder(4, 4, 4, 4),
BorderFactory.createLineBorder(Color.black)));
add(chartPanel);
}
/**
*Ajoute une nouvelle donnée à la série temporelle
*
* @param y the total memory used.
*/
public void addTotalObservation(Number y) {
this.total.addOrUpdate(new Second(), y);
}



}
