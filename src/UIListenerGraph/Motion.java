package UIListenerGraph;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Timer;

import UIGraph.GraphVisualizer;
import UIGraph.UIModel.VertexUI;


/**
 * Classe permettant la gestion des évènements permettant de déplacer des sommets
 * @author KIEFFER
 *
 */
public class Motion implements MouseMotionListener {
	
	
	private GraphVisualizer visu;



	public Motion(GraphVisualizer visu) {
		super();
		this.visu = visu ;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
		if(e.getSource() instanceof VertexUI){
			VertexUI v = (VertexUI)e.getSource();
			Point absP = e.getLocationOnScreen();
			Point posParent = v.getParent().getLocationOnScreen();
			Point pos = new Point(absP.x-posParent.x,absP.y-posParent.y);
			int rayon = v.getVertex().getInfo().getRayon();
			pos.x = Math.max(pos.x, rayon);
			pos.y = Math.max(pos.y, rayon);
			pos.x = Math.min(pos.x, this.visu.getPanel().getWidth()-rayon);
			pos.y = Math.min(pos.y, this.visu.getPanel().getHeight()-rayon);
			v.setLocation(pos);
			this.visu.getPanel().invalidate();
			
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement

	}



}
