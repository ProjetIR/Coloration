package Monitor;


import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import statistics.Record;
import statistics.Statistics;





import Model.Graphe;
import UI.Windows;

/**
 * Classe permettant d'afficher les informations sur l'évolutions de certains critères du graphes
 * sous forme de graphiques
 * @author KIEFFER
 *
 */
public class MonitorStart extends Dialog implements Observer {
	
	private Windows parent;
	private Statistics stats;
 	private TimeSeriesChart panel; // représente des séries temporelles
	private TimeSeriesChart panel1; 
	private TimeSeriesChart panel2;

	
	
	 public MonitorStart(Windows parent,Statistics stats) throws HeadlessException{
		super(parent);
		// TODO Stub du constructeur généré automatiquement
		this.parent= parent;
		this.stats = stats;
		this.stats.addObserver(this);
		initializeFrame();
		initializeComponent();
		this.setModal(false);

	 }
	 
	 public void showDialog(int ms){
		 
		this.setVisible(true); 
	 }

	 
	
	private void initializeFrame(){
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
	 }
	 
	 private void initializeComponent(){
		 	panel = new TimeSeriesChart("Evolution of Conflicts number","Time","Conflicts number");
			panel1 = new TimeSeriesChart("Evolution of Colors number","Time","Colors number");
			panel2 = new TimeSeriesChart("Evolution of Active Threads number","Time","Active Threads number");
			GridLayout grid = new GridLayout(1,3);
			this.setLayout(grid);
			this.add(panel);
			this.add(panel1);
			this.add(panel2);
			this.setBounds(200, 120, 600, 280);
			this.setVisible(true);
			this.addWindowListener(new StopListener(this,this.stats));
			

	 }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	
		if(arg1 instanceof Record){
				Record rec = (Record)arg1;
				Number conflicts = rec.getData("Number of conflicts");
				panel.addTotalObservation(conflicts);
				Number nbColors = rec.getData("Number of different Colors");
				panel1.addTotalObservation(nbColors);
				Number threads = rec.getData("Nombre de Threads Actifs");
				panel2.addTotalObservation(threads);
		}
	
	}
	 

	 

}
