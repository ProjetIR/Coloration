package UI;
import java.awt.Frame;
import java.awt.Panel;
import java.util.TimerTask;

/**
 * La tâche appelée périodiquement pour rafraichir le panel de l'application
 * Utilisé pour l'effet intéractif principalement
 * @author KIEFFER
 *
 */
public class Task extends TimerTask {

	private Panel f;
	
	public Task(Panel f) {
		super();
		this.f = f;
	}	
	
	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
			//if(!f.isValid()){
				f.repaint();
				f.validate();
			//}


	}



}
