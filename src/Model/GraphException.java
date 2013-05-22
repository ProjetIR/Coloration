package Model;


/**
 * Classe d'exception principale pour la classe Graphe
 * Utilise une énumération pour lister les exceptions possibles
 * @author KIEFFER
 *
 */
public class GraphException extends Exception {


	private static String[] messageException = {"Not existing Vertex","Not existing Edge","Number edges error"};

	public GraphException(Graph_ERROR code) {
		super(messageException[code.ordinal()]);
		// TODO Auto-generated constructor stub
	}
	
}
