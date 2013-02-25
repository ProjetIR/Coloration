package UI.ListenerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import IO.GrapheWriter;
import Model.Graphe;
import UI.Windows;

public class SaveListener implements ActionListener {

	private Windows win;
	
	
	public SaveListener(Windows win) {
		super();
		this.win = win;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement
		Windows.log.info("Sauvegarde du graphe courant");
		JFileChooser chooser = new JFileChooser();
	    // Note: source for ExampleFileFilter can be found in FileChooserDemo,
	    // under the demo/jfc directory in the JDK.
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.gr", "gr");
		    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String path = chooser.getSelectedFile().getPath()+".gr";
	    	Windows.log.info("Enregistement : "+path);
			GrapheWriter w = new GrapheWriter(path);
			w.write(win.getGraphe());
	    }
	}
	    
	    

}
