package Model;
import java.util.Comparator;

/**
 * Classe implémentant Comparator pour comparer 
 * les sommets en fonction de leur degré
 * @author emmanuel
 *
 */
public class DegreeComparator implements Comparator<Vertex> {



	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Stub de la méthode généré automatiquement
		
		return o1.getDegree() - o2.getDegree();
	}

	

	

}
