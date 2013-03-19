package Plugins.Algorithm;

import java.util.Comparator;

import Model.Vertex;

/**
 * Classe implémentant Comparator pour comparer 
 * les sommets en fonction de leur degré
 * @author emmanuel
 *
 */
public class DegreeComparatorDecroiss implements Comparator<Vertex> {



	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Stub de la méthode généré automatiquement
		
		return (-1)*(o1.getDegree() - o2.getDegree());
	}

	

	

}
