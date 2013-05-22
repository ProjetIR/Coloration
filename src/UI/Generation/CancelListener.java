package UI.Generation;


import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Simple listener pour fermer une fenêtre
 * @author KIEFFER
 *
 */
public class CancelListener extends MouseAdapter {

	private Dialog f;

	
	public CancelListener(Dialog f) {
		super();
		this.f = f;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Stub de la méthode généré automatiquement
		f.setVisible(false);
		f.dispose();
	}
	
	

}
