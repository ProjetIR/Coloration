package UI.ListenerUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.Windows;

public class AlgorithmListener implements ActionListener {

	
	private Windows win;
	private Class algo;
	
	
	public AlgorithmListener(Windows win, Class algo) {
		super();
		this.win = win;
		this.algo = algo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		win.getHandler().addAlgorithm(algo);
	}
	
	
}
