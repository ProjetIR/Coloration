package Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import algorithm.Algorithm;

import Plugins.LoadPlugins;

public class TestPlugins {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		// TODO Stub de la méthode généré automatiquement
		ArrayList<Class> listeAlgorithm = new ArrayList<Class>();
		LoadPlugins.startLoadingPlugins(listeAlgorithm);
		try {
			for(Class e : listeAlgorithm){
				Algorithm d = (Algorithm) e.newInstance();
			}
		} catch (InstantiationException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

}
