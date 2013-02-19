package UI.Generation;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import algorithm.RandomDisposition;
import Model.GraphException;
import Model.Graphe;
import Model.Generator.Generator;
import Model.Generator.RandomGenerator;
import UI.Windows;

public class GenerationListener extends MouseAdapter {

	private GenerateGrapheDialog dial;
	private Windows win;

	public GenerationListener(GenerateGrapheDialog dial,Windows win) {
		super();
		this.dial = dial;
		this.win = win;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		Windows.log.info("Génération du graphe");
		Graphe g = new Graphe();
		int nbVertex = dial.getVertexNumberField().getValue();
		int nbEdge = dial.getEdgeNumberField().getValue();
		Generator gen = new RandomGenerator(nbVertex,nbEdge);
		try {
			gen.generate(g, win.getDefautInfoVertex() , win.getDefautInfoEdge() );
			RandomDisposition pos = new RandomDisposition(win.getDoubleBuffer(),g);
			pos.compute();
			Windows.log.info("Positionnement des Vertices sur le Layout");
			/*Attention aux références au graphe dans le visualiseur et dans la   classe Windows
			 * Si on modifie l'un, il faut modifier l'autre !!!!
			 * Sinon décalage des références
			 */
			win.setGraphe(g);
			win.getVisu().removeAllUIComponents();
			win.getVisu().setGraphe(g);
			win.getVisu().addUIComponents();
			win.getHandler().setGraphe(g);
			Windows.log.info("Activation de la gestion dynamique");
			Windows.log.info("Nouveau Graphe |V| = "+win.getGraphe().getVertexNumber()+" |E| = "+win.getGraphe().getEdgesNumber());
			dial.setVisible(false);
			dial.dispose();
		} catch (GraphException e1) {
			// TODO Bloc catch généré automatiquement
			JOptionPane.showMessageDialog(dial, e1.getMessage());
		} catch (CloneNotSupportedException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} 
	}
	
	

}
