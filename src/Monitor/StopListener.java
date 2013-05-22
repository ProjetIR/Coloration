package Monitor;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import statistics.Statistics;


/**
 * Permet de stopper l'affichage des stats sur des graphiques
 * @author KIEFFER
 *
 */
public class StopListener implements WindowListener {

	private MonitorStart start;
	private Statistics stats;
	
	

	public StopListener(MonitorStart start,Statistics stats) {
		super();
		this.start = start;
		this.stats = stats;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.stats.stopRecord();
		this.start.setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
