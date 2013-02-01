package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.Graphe;


public class GrapheReader {

	private java.io.FileInputStream fichier;
	private ObjectInputStream inStream;
	
	public GrapheReader (String name){
		
		try {
			fichier = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public Graphe read(){
	
			try {
				inStream = new ObjectInputStream(fichier);
				return (Graphe)inStream.readObject();
			} catch (IOException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			return null;
			
	
	}
}
