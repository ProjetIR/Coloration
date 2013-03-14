package Monitor;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Test {

	public static void main(String[] args) {

		Frame frame = new Frame("Memory Usage Demo");
		MemoryUsageDemo panel = new MemoryUsageDemo();
		MemoryUsageDemo panel1 = new MemoryUsageDemo();
		MemoryUsageDemo panel2 = new MemoryUsageDemo();
		BarChart panel3 = new BarChart();
		GridLayout grid = new GridLayout(2,2);
		frame.setLayout(grid);
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.setBounds(200, 120, 600, 280);
		frame.setVisible(true);
		panel.new DataGenerator(100).start();
		panel1.new DataGenerator(100).start();
		panel2.new DataGenerator(100).start();
		panel3.new DataGenerator(100).start();
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		System.exit(0);
		}
		});
	}
}
