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
			v.setLocation((new Point(absP.x-posParent.x,absP.y-posParent.y)));
			this.visu.getPanel().invalidate();
			
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement

	}



}
