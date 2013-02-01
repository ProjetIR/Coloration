package Model;



public class GraphException extends Exception {


	private static String[] messageException = {"Not existing Vertex","Not existing Edge","Number edges error"};

	public GraphException(Graph_ERROR code) {
		super(messageException[code.ordinal()]);
		// TODO Auto-generated constructor stub
	}
	
}
