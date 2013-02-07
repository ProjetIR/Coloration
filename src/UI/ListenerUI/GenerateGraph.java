package UI.ListenerUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Graphe;
import UI.GenerateGrapheFrame;

public class GenerateGraph implements ActionListener {

	private Graphe g;
	private Frame f;
	
	
	public GenerateGraph(Frame f,Graphe g) {
		super();
		this.g = g;
		this.f = f;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		GenerateGrapheFrame fen = new GenerateGrapheFrame(f, g);
		fen.showDialog();
	}


}
