package UI.ListenerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithm.RandomDisposition;

import IO.GrapheReader;
import IO.Benchmark.BenchMarkReader;
import Model.Graphe;
import UI.Windows;

public class LoadListener implements ActionListener {

	private Windows win;
	
	
	public LoadListener(Windows win) {
		super();
		this.win = win;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		Windows.log.info("Chargement d'un graphe sérialisé");
		JFileChooser chooser = new JFileChooser();
	    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
	    // under the demo/jfc directory in the JDK.
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.gr", "gr");
		    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    		try {
					String filePath = chooser.getSelectedFile().getPath();
					Windows.log.info("Fichier : "+filePath);
					GrapheReader r = new GrapheReader(filePath);
					Graphe g = r.read();
					Windows.log.info("Génération du graphe");
					Windows.log.info("Positionnement des Vertices sur le Layout");
					/*Attention aux références au graphe dans le visualiseur et dans la   classe Windows
					 * Si on modifie l'un, il faut modifier l'autre !!!!
					 * Sinon décalage des références
					 */
					this.win.setGraphe(g);
					this.win.getVisu().removeAllUIComponents();
					this.win.getVisu().setGraphe(g);
					this.win.getVisu().addUIComponents();
					this.win.getHandler().setGraphe(g);
					Windows.log.info("Activation de la gestion dynamique");
					Windows.log.info("Nouveau Graphe |V| = "+this.win.getGraphe().getVertexNumber()+" |E| = "+this.win.getGraphe().getEdgesNumber());
	    		} catch (Exception e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
	    }
		
	}

}
