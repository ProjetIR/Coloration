package Model.Generator;
import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;

/**
 * Interface de génération de graphe
 * @author KIEFFER
 *
 */
public interface Generator {
	
	public void generate(Graphe g,InfoVertex v,InfoEdge u) throws GraphException, CloneNotSupportedException;

}
