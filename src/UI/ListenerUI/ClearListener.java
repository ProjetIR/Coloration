package UI.ListenerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Graphe;
import UI.Windows;

public class ClearListener implements ActionListener {

	private Windows win;
	
	

	public ClearListener(Windows win) {
		super();
		this.win = win;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Graphe g = new Graphe();
		this.win.setGraphe(g);
		this.win.getVisu().removeAllUIComponents();
		this.win.getVisu().setGraphe(g);
		this.win.getVisu().addUIComponents();
		this.win.getHandler().setGraphe(g);
		this.win.getStatistics().setGraphe(g);
		Windows.log.info("Activation de la gestion dynamique");
		Windows.log.info("Nouveau Graphe |V| = "+this.win.getGraphe().getVertexNumber()+" |E| = "+this.win.getGraphe().getEdgesNumber());
	}

}
