package UI.ListenerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.monitor.Monitor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Monitor.MonitorStart;
import UI.Windows;

import statistics.Statistics;
import statistics.IO.StatisticsWriter;

/**
 * Listener permettant de démarre l'enregistrement des statistics
 * @author KIEFFER
 *
 */
public class StartRecordListener implements ActionListener {

	private Statistics stats;
	private Windows win;

	public StartRecordListener(Windows win,Statistics stats) {
		super();
		this.stats = stats;
		this.win = win;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object[] possibilities = {"Save only records", "Show only monitor", "Both"};
		String s = (String)JOptionPane.showInputDialog(
		                    this.win,
		                    "Which options ?",
		                    "Statistics", JOptionPane.QUESTION_MESSAGE,
		                    new ImageIcon(),
		                    possibilities,
		                    "Show only monitor");

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
		   if(s.equals(possibilities[0])){
			   this.save();
		   }else if(s.equals(possibilities[1])){
			   this.monitoring();
		   }else {
			   this.save();
			   this.monitoring();
		   }
		   Windows.log.info("Start statistics. Freq = "+Statistics.TIME+" ms");
		   this.stats.startRecord(Statistics.TIME);
		}
		

	}
	
	public void save(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.txt", "txt");
		    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String path = chooser.getSelectedFile().getPath()+".txt";
	    	Windows.log.info("Enregistement : "+path);
	    	this.stats.addObserver(new StatisticsWriter(path, '\t'));
	    }
	}
	
	public void monitoring(){
		Windows.log.info("Start Monitor");
		MonitorStart monitor = new MonitorStart(this.win, this.stats);
		monitor.showDialog(Statistics.TIME);
	}

}
