package IO;

import java.io.*;

import Model.Graphe;

public class GrapheWriter {

	private java.io.FileOutputStream fichier;
	private ObjectOutputStream outStream;
	
	
	public GrapheWriter (String name){
		
		try {
			fichier = new FileOutputStream(name);
		} catch (FileNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public void write(Graphe g){
		try {
			outStream = new ObjectOutputStream(fichier);
			outStream.writeObject(g);
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
