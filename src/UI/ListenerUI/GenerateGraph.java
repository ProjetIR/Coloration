package UI.ListenerUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Graphe;
import UI.Windows;
import UI.Generation.GenerateGrapheDialog;

/**
 * Listener permettant d'ouvrir la boite de dialogue pour la création d'un graphe aléatoire
 * @author KIEFFER
 *
 */
public class GenerateGraph implements ActionListener {

	private Graphe g;
	private Windows f;
	
	
	public GenerateGraph(Windows f,Graphe g) {
		super();
		this.g = g;
		this.f = f;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		GenerateGrapheDialog fen = new GenerateGrapheDialog(f, g);
		fen.showDialog();
	}


}
