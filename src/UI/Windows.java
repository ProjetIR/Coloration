package UI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;



import Model.Graphe;
import UI.ListenerUI.CloseWindows;
import UIGraph.GraphVisualizer;

public class Windows extends Frame {
	
	private Graphe graphe;
	private GraphVisualizer visualizer;
	private Timer timer;
	private DoubleBuffer doubleBuffer;
	
	
	private MenuBar mbar;
	
	public Windows(String title) throws HeadlessException {
		super(title);
		// TODO Stub du constructeur généré automatiquement
		addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		this.setSize(new Dimension(800, 600));
		
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
		setSize(lf, hf);
		DoubleBuffer p = new DoubleBuffer();
		p.setLayout(null);
		
		InitializeMenus();
		
	}
	
	
	
	private void InitializeMenus()
	{
		mbar = new MenuBar();
		Menu m = new Menu("File");
		m.add(new MenuItem("New"));
		m.add(new MenuItem("Open"));
		m.add(new MenuItem("Save"));
		m.add(new MenuItem("Save As"));
		m.add(new MenuItem("Print"));
		m.addSeparator();
		MenuItem quitter = new MenuItem("Quit");
		quitter.addActionListener(new CloseWindows());
		m.add(quitter);		
		mbar.add(m);
			
		m = new Menu("Help");
		m.add(new MenuItem("Help!!!!"));
		m.addSeparator();
		m.add(new MenuItem("About..."));
		mbar.add(m);

		// Recent change
		setMenuBar(mbar);
	}
	
	

}
