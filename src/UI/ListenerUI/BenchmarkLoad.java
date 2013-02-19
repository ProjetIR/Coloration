package UI.ListenerUI;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import algorithm.RandomDisposition;
import IO.Benchmark.BenchMarkReader;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import UI.DoubleBuffer;
import UI.Windows;
import UIGraph.GraphVisualizer;

public class BenchmarkLoad implements ActionListener {

	
	public Windows f;

	
	
	public BenchmarkLoad(Windows f) {
		super();
		this.f = f;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		Windows.log.info("Chargement d'un benchmark");
		JFileChooser chooser = new JFileChooser();
	    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
	    // under the demo/jfc directory in the JDK.
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.col", "col");
		    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    		try {
					String filePath = chooser.getSelectedFile().getPath();
					Windows.log.info("Fichier : "+filePath);
					BenchMarkReader ben = new BenchMarkReader(filePath, this.f.getDefautInfoVertex(), this.f.getDefautInfoEdge());
					Graphe g = ben.read();
					Windows.log.info("Génération du graphe");
					RandomDisposition dp = new RandomDisposition(this.f.getDoubleBuffer(),g);
					dp.compute();
					Windows.log.info("Positionnement des Vertices sur le Layout");
					/*Attention aux références au graphe dans le visualiseur et dans la   classe Windows
					 * Si on modifie l'un, il faut modifier l'autre !!!!
					 * Sinon décalage des références
					 */
					this.f.setGraphe(g);
					this.f.getVisu().removeAllUIComponents();
					this.f.getVisu().setGraphe(g);
					this.f.getVisu().addUIComponents();
					this.f.getHandler().setGraphe(g);
					Windows.log.info("Activation de la gestion dynamique");
					Windows.log.info("Nouveau Graphe |V| = "+this.f.getGraphe().getVertexNumber()+" |E| = "+this.f.getGraphe().getEdgesNumber());
	    		} catch (Exception e1) {
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
	    }
		
	}

}
