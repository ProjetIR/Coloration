package statistics.IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import statistics.Record;

/**
 * Classe permettant de stocker les informations issues des statistics de l'instance Statitics
 * et de les sauvegarder dans un fichier
 * @author KIEFFER
 *
 */
public class StatisticsWriter implements Observer{

	private java.io.FileOutputStream fichier;
	private PrintWriter outStream;
	private boolean hasHeader;
	private char separator;
	
	public StatisticsWriter(String fileName,char separator) {
		super();
		try {
			this.fichier = new FileOutputStream(fileName);
			this.outStream = new PrintWriter(fichier);
			this.hasHeader = false;
			this.separator = separator;
		} catch (FileNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		try {
			if(arg == null) {
				this.outStream.flush();
				this.fichier.close();
			}
			if(arg instanceof Record){
				this.outStream.println(transform((Record)arg));
			}
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	

	public String transform(Record c){
		String s = "";
		if(!hasHeader){
			for(String ch : c.getRecordName()){
				s+=ch+separator;
			}
			s+="\n";
			hasHeader = true;
		}
		for(Number n : c.getValues()){
			s+=n.toString()+separator;
		}
		return s;
	}

}
