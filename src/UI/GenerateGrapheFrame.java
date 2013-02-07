package UI;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Model.Graphe;

public class GenerateGrapheFrame extends Dialog implements ComponentListener{
	
	private Graphe graphe;
	
	
	 public GenerateGrapheFrame(Frame parent,Graphe g) throws HeadlessException{
		super(parent);
		// TODO Stub du constructeur généré automatiquement
		this.graphe = graphe;
		
		this.setSize(new Dimension(400, 300));
		this.setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize(); 	// l'objet d contient les dimensions de l'écran
		int he, le,		// hauteur et largeur de l’écran 
		hf,lf;		// hauteur et largeur de la fenêtre
		int Ox, Oy;		//coordonnées du coin haut gauche de la fenêtre

		he=(int)(d.getHeight());		//récupère la hauteur de l'écran
		le=(int)(d.getWidth());		//récupère la largeur de l'écran
		hf = (he/2)+(he/4); lf = (le/2)+(le/4);		// la fenêtre prend 1/4 de l'écran
		Ox = le/2; Oy = he/2;		// elle est placée à 1/3 du coin haut gauche

		setLocation(Ox-lf/2,Oy-hf/2);	//fixe les coordonnées du coin haut gauche du cadre en pixels
		this.setModal(true);
		this.addComponentListener(this);
		
	 }
	 
	 public void showDialog(){
		this.setVisible(true); 
	 }

	 
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Stub de la méthode généré automatiquement
		// Pas de redimensionnement possible
		this.setSize(new Dimension(400, 300));
		this.invalidate();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}
	 

}
