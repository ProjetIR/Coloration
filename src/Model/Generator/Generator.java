package Model.Generator;
import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;


public interface Generator {
	
	public void generate(Graphe g,InfoVertex v,InfoEdge u) throws GraphException, CloneNotSupportedException;

}
