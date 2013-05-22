package Monitor;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Simple classe de test pour la bibliothèque JFreeChart
 * @author KIEFFER
 *
 */
public class Test {

	public static void main(String[] args) {

		Frame frame = new Frame("Memory Usage Demo");
		TimeSeriesChart panel = new TimeSeriesChart("x","x","x");
		TimeSeriesChart panel1 = new TimeSeriesChart("x","x","x");
		TimeSeriesChart panel2 = new TimeSeriesChart("x","x","x");
		BarChart panel3 = new BarChart("Colors Distribution");
		GridLayout grid = new GridLayout(2,2);
		frame.setLayout(grid);
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.setBounds(200, 120, 600, 280);
		frame.setVisible(true);
		panel3.addTotalObservation(new Color[]{Color.red, Color.blue},null);
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		System.exit(0);
		}
		});
	}
}
